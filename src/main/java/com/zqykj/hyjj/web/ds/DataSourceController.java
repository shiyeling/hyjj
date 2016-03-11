/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.zqykj.hyjj.web.ds;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.apache.ddlutils.model.Database;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.google.common.collect.Maps;
import com.zqykj.hyjj.entity.DataSource;
import com.zqykj.hyjj.entity.User;
import com.zqykj.hyjj.service.account.ShiroDbRealm.ShiroUser;
import com.zqykj.hyjj.service.ds.DataSourceService;

/**
 * Task管理的Controller, 使用Restful风格的Urls:
 * 
 * <code>
 * List page : GET /ds/ 
 * Create page : GET /ds/create 
 * Create action : POST/ds/create 
 * Update page : GET /ds/update/{id} 
 * Update action : POST /ds/update
 * Delete action : GET /ds/delete/{id}
 * </code>
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/ds")
public class DataSourceController {
    private static Logger logger = LoggerFactory.getLogger(DataSourceController.class);
    private static final String PAGE_SIZE = "5";

    private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
    static {
        sortTypes.put("auto", "自动");
        sortTypes.put("name", "标题");
    }

    @Autowired
    private DataSourceService dataSourceService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
            @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
            @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
            ServletRequest request) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        Long userId = getCurrentUserId();

        Page<DataSource> dataSources = dataSourceService.getUserDataSource(userId, searchParams, pageNumber, pageSize,
                sortType);

        model.addAttribute("dataSources", dataSources);
        model.addAttribute("sortType", sortType);
        model.addAttribute("sortTypes", sortTypes);
        // 将搜索条件编码成字符串，用于排序，分页的URL
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));

        return "ds/dsList";
    }

    @RequestMapping(value = "model/{id}", method = RequestMethod.GET)
    public String getDatabaseModel(@PathVariable("id") Long id, Model model) {
        DataSource ds = dataSourceService.getDataSource(id);
        model.addAttribute("ds", ds);
        Database dbModel = dataSourceService.getDataModel(ds);
        model.addAttribute("dbModel", dbModel);
        return "ds/dsModel";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model) {
        model.addAttribute("ds", new DataSource());
        model.addAttribute("action", "create");
        return "ds/dsForm";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@Valid DataSource newDs, RedirectAttributes redirectAttributes) {
        User user = new User(getCurrentUserId());
        newDs.setUser(user);

        dataSourceService.saveDataSource(newDs);
        redirectAttributes.addFlashAttribute("message", "创建数据源成功");
        return "redirect:/ds/";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("ds", dataSourceService.getDataSource(id));
        model.addAttribute("action", "update");
        return "ds/dsForm";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("ds") DataSource ds, RedirectAttributes redirectAttributes) {
        dataSourceService.saveDataSource(ds);
        redirectAttributes.addFlashAttribute("message", "更新数据源成功");
        return "redirect:/ds/";
    }

    @RequestMapping(value = "test", method = RequestMethod.POST)
    public String test(@Valid @ModelAttribute("ds") DataSource ds, RedirectAttributes redirectAttributes) {
        String msg = "";
        try {
            boolean success = dataSourceService.testDataSourceConnection(ds);
            ds.setValid(success);
            if (success) {
                msg = "数据源测试连接成功";
            } else {
                msg = "数据源测试连接失败";
            }
        } catch (SQLException e) {
            ds.setValid(false);
            msg = "数据源测试连接失败,原因：" + e.getMessage() + ", 错误代码:" + e.getErrorCode();
            logger.info("User data source setting test failed.", e);
        }
        redirectAttributes.addFlashAttribute("success", ds.isValid());
        redirectAttributes.addFlashAttribute("message", msg);
        return "redirect:/ds/update/" + ds.getId();
    }

    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        dataSourceService.deleteDataSource(id);
        redirectAttributes.addFlashAttribute("message", "删除数据源成功");
        return "redirect:/ds/";
    }

    /**
     * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2
     * Preparable二次部分绑定的效果,先根据form的id从数据库查出Task对象,再把Form提交的内容绑定到该对象上。
     * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
     */
    @ModelAttribute
    public void getDataSource(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("ds", dataSourceService.getDataSource(id));
        }
    }

    /**
     * 取出Shiro中的当前用户Id.
     */
    private Long getCurrentUserId() {
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        return user.id;
    }
}
