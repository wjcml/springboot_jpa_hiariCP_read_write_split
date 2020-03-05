package com.split.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource.slave")	// 数据源
public class SlaveConfig extends HikariConfig {

	@Bean
	public DataSource slaveDataSource() {
		final HikariDataSource slaveDataSource = new HikariDataSource(this);

		return slaveDataSource;
	}

}
