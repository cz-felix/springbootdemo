package com.example.demo.config;


/**
 * 使用prop.properties   需要使用这个类
 *
 * 扫描mybatis的接口
 * Created by chenzhi on 2017/9/7.
 */
//@Configuration
// 因为这个对象的扫描，需要在MyBatisConfig的后面注入，所以加上下面的注解
//@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisMapperScannerConfig {
    /*@Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        //获取之前注入的beanName为sqlSessionFactory的对象
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        //指定xml配置文件的路径
        mapperScannerConfigurer.setBasePackage("com.example.demo.dao");
        return mapperScannerConfigurer;
    }*/
}
