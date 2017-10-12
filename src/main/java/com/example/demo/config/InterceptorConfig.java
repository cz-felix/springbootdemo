package com.example.demo.config;

import com.example.demo.utils.Interceptor.UserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by chenzhi on 2017/10/10 0010.
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    //拦截器加载的时间点在springcontext之前，所以在拦截器中注入自然为null
    //使用bean注解提前加载，即可成功。
    @Bean
    public HandlerInterceptor getMyInterceptor(){
        return new UserInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(getMyInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
