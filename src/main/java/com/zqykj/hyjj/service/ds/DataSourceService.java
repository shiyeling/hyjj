package com.zqykj.hyjj.service.ds;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.persistence.SearchFilter.Operator;

import com.zqykj.hyjj.entity.DataSource;
import com.zqykj.hyjj.repository.DataSourceDao;

//Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class DataSourceService {
    private DataSourceDao dataSourceDao;

    public DataSource getDataSource(Long id) {
        return dataSourceDao.findOne(id);
    }

    public void saveDataSource(DataSource ds) {
        dataSourceDao.save(ds);
    }
    
    public void deleteDataSource(Long id){
        dataSourceDao.delete(id);
    }

    public Page<DataSource> getUserDataSource(Long userId, Map<String, Object> searchParams, int pageNumber,
            int pageSize, String sortType) {
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
        Specification<DataSource> spec = buildSpecification(userId, searchParams);

        return dataSourceDao.findAll(spec, pageRequest);
    }

    /**
     * 创建分页请求.
     */
    private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
        Sort sort = null;
        if ("auto".equals(sortType)) {
            sort = new Sort(Direction.DESC, "id");
        } else if ("name".equals(sortType)) {
            sort = new Sort(Direction.ASC, "name");
        }

        return new PageRequest(pageNumber - 1, pagzSize, sort);
    }

    /**
     * 创建动态查询条件组合.
     */
    private Specification<DataSource> buildSpecification(Long userId, Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
        Specification<DataSource> spec = DynamicSpecifications.bySearchFilter(filters.values(), DataSource.class);
        return spec;
    }

    public DataSourceDao getDataSourceDao() {
        return dataSourceDao;
    }

    @Autowired
    public void setDataSourceDao(DataSourceDao dataSourceDao) {
        this.dataSourceDao = dataSourceDao;
    }
}
