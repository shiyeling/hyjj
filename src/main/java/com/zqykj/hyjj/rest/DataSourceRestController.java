/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.zqykj.hyjj.rest;

import java.util.List;

import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springside.modules.web.MediaTypes;

import com.zqykj.hyjj.entity.DataSource;
import com.zqykj.hyjj.service.ds.DataSourceService;

/**
 * Task的Restful API的Controller.
 * 
 * @author calvin
 */
@RestController
@RequestMapping(value = "/api/v1/ds")
public class DataSourceRestController {

	private static Logger logger = LoggerFactory.getLogger(DataSourceRestController.class);

	@Autowired
	private DataSourceService dataSourceService;

	@Autowired
	private Validator validator;

//	@RequestMapping(method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
//	public List<DataSource> list() {
//		return dataSourceService.getAllDataSource();
//	}

//	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
//	public Task get(@PathVariable("id") Long id) {
//		Task task = dataSourceService.getTask(id);
//		if (task == null) {
//			String message = "任务不存在(id:" + id + ")";
//			logger.warn(message);
//			throw new RestException(HttpStatus.NOT_FOUND, message);
//		}
//		return task;
//	}
//
//	@RequestMapping(method = RequestMethod.POST, consumes = MediaTypes.JSON)
//	public ResponseEntity<?> create(@RequestBody Task task, UriComponentsBuilder uriBuilder) {
//		// 调用JSR303 Bean Validator进行校验, 异常将由RestExceptionHandler统一处理.
//		BeanValidators.validateWithException(validator, task);
//
//		// 保存任务
//		dataSourceService.saveTask(task);
//
//		// 按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
//		Long id = task.getId();
//		URI uri = uriBuilder.path("/api/v1/task/" + id).build().toUri();
//		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(uri);
//
//		return new ResponseEntity(headers, HttpStatus.CREATED);
//	}
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaTypes.JSON)
//	// 按Restful风格约定，返回204状态码, 无内容. 也可以返回200状态码.
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void update(@RequestBody Task task) {
//		// 调用JSR303 Bean Validator进行校验, 异常将由RestExceptionHandler统一处理.
//		BeanValidators.validateWithException(validator, task);
//
//		// 保存任务
//		dataSourceService.saveTask(task);
//	}
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void delete(@PathVariable("id") Long id) {
//		dataSourceService.deleteTask(id);
//	}
}
