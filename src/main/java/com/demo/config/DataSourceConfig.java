package com.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by chenzhi on 2017/9/7.
 */
@Configuration
@MapperScan("com.demo.dao")
public class DataSourceConfig {
    @Value("${druid.jdbc.driveClassName}")
    private String driveClassName;

    @Value("${druid.jdbc.jdbcUrl}")
    private String jdbcUrl;

    @Value("${druid.jdbc.jdbcUsername}")
    private String jdbcUsername;

    @Value("${druid.jdbc.jdbcPassword}")
    private String jdbcPassword;

    @Value("${druid.jdbc.filters}")
    private String filters;

    @Value("${druid.jdbc.maxActive}")
    private int maxActive;

    @Value("${druid.jdbc.initialSize}")
    private int initialSize;

    @Value("${druid.jdbc.maxWait}")
    private int maxWait;

    @Value("${druid.jdbc.minIdle}")
    private int minIdle;

    @Value("${druid.jdbc.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;

    @Value("${druid.jdbc.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${druid.jdbc.validationQuery}")
    private String validationQuery;

    @Value("${druid.jdbc.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${druid.jdbc.testOnBrowwon}")
    private boolean testOnBrowwon;

    @Value("${druid.jdbc.testOnReturn}")
    private boolean testOnReturn;

    @Bean(name = "dataSource")
    public DataSource dataSource() {

        DruidDataSource datasource = new DruidDataSource();
        datasource.setDriverClassName(this.driveClassName);
        datasource.setUrl(this.jdbcUrl);
        datasource.setUsername(this.jdbcUsername);
        datasource.setPassword(this.jdbcPassword);
        datasource.setMaxActive(this.maxActive);
        datasource.setInitialSize(this.initialSize);
        datasource.setMaxWait(this.maxWait);
        datasource.setMinIdle(this.minIdle);
        datasource.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis);
        datasource.setValidationQuery(this.validationQuery);
        datasource.setTestWhileIdle(this.testWhileIdle);
        datasource.setTestOnBorrow(this.testOnBrowwon);
        datasource.setTestOnReturn(this.testOnReturn);

        try {
            datasource.setFilters("stat,wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datasource;
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager() throws SQLException {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        p.setProperty("dialect", "mysql");
        p.setProperty("supportMethodsArguments", "false");
        p.setProperty("pageSizeZero", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }
}
