package com.example.demo.config;

/**
 * Created by chenzhi on 2017/9/7.
 */

//使用prop.properties   需要使用这个类
//@Configuration
//加上这个注解，使得支持事务
//@EnableTransactionManagement
public class MyBatisConfig /*implements TransactionManagementConfigurer*/ {
    /*@Autowired
    private DataSource dataSource;

    @Override
    @Bean(name = "transactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.example.demo.model");//每一张表对应的实体类

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath:mapper*//*.xml"));//每张表对应的xml文件
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }*/
}
