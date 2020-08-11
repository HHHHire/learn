package com.ddh.learn.order;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/7 23:32
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

        // 清空表数据，对表结构不影响
        jdbcTemplate.update("TRUNCATE TABLE order_tbl");
        return jdbcTemplate;
    }

}
