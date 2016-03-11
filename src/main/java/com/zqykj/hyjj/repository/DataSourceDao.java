package com.zqykj.hyjj.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.zqykj.hyjj.entity.DataSource;

public interface DataSourceDao
        extends PagingAndSortingRepository<DataSource, Long>, JpaSpecificationExecutor<DataSource> {
    DataSource findDataSourceByName(String name);
}
