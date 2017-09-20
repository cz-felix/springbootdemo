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
public class ActivitiConfig /*extends AbstractProcessEngineAutoConfiguration*/ {

    /*@Bean(name = "uuidGenerator")
    public UUIDGenerator uuidGenerator() {
        return new UUIDGenerator();
    }

    //注入数据源和事务管理器
    @Bean(name = "processEngine")
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(
            @Qualifier("dataSource") DataSource dataSource,
            @Qualifier("transactionManager") PlatformTransactionManager transactionManager,
            @Qualifier("uuidGenerator") UUIDGenerator uuidGenerator,
            SpringAsyncExecutor springAsyncExecutor) throws IOException {
        SpringProcessEngineConfiguration processEngineConfiguration=  this.baseSpringProcessEngineConfiguration(dataSource, transactionManager, springAsyncExecutor);
        processEngineConfiguration.setDatabaseSchemaUpdate("fasle");
        processEngineConfiguration.setIdGenerator(uuidGenerator);
        processEngineConfiguration.setActivityFontName("宋体");
        processEngineConfiguration.setLabelFontName("宋体");
        return processEngineConfiguration;
    }

    @Bean(name="repositoryService")
    public RepositoryService repositoryService(@Qualifier("processEngine") SpringProcessEngineConfiguration processEngine) {
        return processEngine.getRepositoryService();
    }
    @Bean(name="runtimeService")
    public RuntimeService runtimeService(@Qualifier("processEngine") SpringProcessEngineConfiguration processEngine) {
        return processEngine.getRuntimeService();
    }
    @Bean(name="formService")
    public FormService formService(@Qualifier("processEngine") SpringProcessEngineConfiguration processEngine) {
        return processEngine.getFormService();
    }
    @Bean(name="identityService")
    public IdentityService identityService(@Qualifier("processEngine") SpringProcessEngineConfiguration processEngine) {
        return processEngine.getIdentityService();
    }
    @Bean(name="taskService")
    public TaskService taskService(@Qualifier("processEngine") SpringProcessEngineConfiguration processEngine) {
        return processEngine.getTaskService();
    }
    @Bean(name="historyService")
    public HistoryService historyService(@Qualifier("processEngine") SpringProcessEngineConfiguration processEngine) {
        return processEngine.getHistoryService();
    }
    @Bean(name="managementService")
    public ManagementService managementService(@Qualifier("processEngine") SpringProcessEngineConfiguration processEngine) {
        return processEngine.getManagementService();
    }*/


}
