package com.split.config;

import com.split.pojo.DataSourceKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Primary
@Component
public class DynamicDataSource extends AbstractRoutingDataSource {
	
	@Autowired
	MasterConfig masterConfig;
	
	@Autowired
	SlaveConfig slaveConfig;
		
	@Override
	protected Object determineCurrentLookupKey() {
		return DynamicDataSourceHolder.getDataSource();

	}

	@PostConstruct
	public void init(){
		Map<Object, Object> targetDataSources = new HashMap<>();

		targetDataSources.put(DataSourceKey.MASTER, masterConfig.masterDataSource());
		targetDataSources.put(DataSourceKey.SLAVE, slaveConfig.slaveDataSource());

		this.setTargetDataSources(targetDataSources);	// 配置数据源
		this.setDefaultTargetDataSource(masterConfig.masterDataSource());	// 默认为主库用于写数据
	}
}
