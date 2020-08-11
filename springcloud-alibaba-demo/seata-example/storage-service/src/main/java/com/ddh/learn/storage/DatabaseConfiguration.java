package com.ddh.learn.storage;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/8 0:33
 */
@Configuration
public class DatabaseConfiguration {
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource getDataSource() {
        return new DruidDataSource();
    }

    @Bean
    public DataSourceProxy dataSourceProxy(DruidDataSource dataSource) {
        return new DataSourceProxy(dataSource);
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(DataSourceProxy dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        // 重新初始化商品数量
        jdbcTemplate.update("delete from storage_tbl where commodity_code = 'C00321'");
        jdbcTemplate.update("insert into storage_tbl(commodity_code, counts) values('C00321', 100)");
        return jdbcTemplate;
    }
}
