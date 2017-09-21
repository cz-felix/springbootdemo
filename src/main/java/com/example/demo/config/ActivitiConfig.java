package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


/**
 * activiti配置文件
 * AbstractProcessEngineAutoConfiguration在activiti-spring-boot-starter-basic下
 *
 * Created by chenzhi on 2017/9/8.
 */
@Configuration
@ImportResource("classpath:activiti-cfg.xml")
public class ActivitiConfig {}
