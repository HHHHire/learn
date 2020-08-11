package com.ddh.learn.account;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 数据库配置类
 *
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/7 17:27
 */
@Configuration
public class DataBaseConfiguration {
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

        jdbcTemplate.update("delete from account_tbl where user_id = 'U100001'");
        jdbcTemplate.update("insert into account_tbl(user_id, money) values ('U100001', 1000)");
        return jdbcTemplate;
    }
}
