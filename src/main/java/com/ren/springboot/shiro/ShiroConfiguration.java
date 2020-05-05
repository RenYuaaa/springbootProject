package com.ren.springboot.shiro;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置文件
 */
@Configuration
public class ShiroConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

    //从配置文件里面读取是否需要启动登录认证的开关，默认true
    @Value("${jwt.auth}")
    private boolean auth;

//     SecurityManager 安全管理器；Shiro的核心
    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        return securityManager;
    }

    //配置拦截器
    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager manager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置securityManager
        shiroFilterFactoryBean.setSecurityManager(manager);
        //启用认证
        String openAuth = auth ? "auth" : "anon";

        //自定义过滤器链
        Map<String, Filter> filters = new HashMap<>();
        //指定拦截器处理
        filters.put("auth", new AuthFilter());
        shiroFilterFactoryBean.setFilters(filters);
        Map<String, String> filterMap = new LinkedHashMap<>();

        //登录请求不拦截
        filterMap.put("/user/login", "anon");
        //登录页面需要用到的接口，不拦截
        filterMap.put("/user/fetchCurrentUser", "anon");

        //swagger页面也放行
        filterMap.put("/swagger-ui.html","anon");
        filterMap.put("/swagger/**","anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/swagger-resources/**","anon");
        filterMap.put("/v2/**","anon");
        filterMap.put("/static/**", "anon");



        //拦截所有接口请求，做权限判断
        filterMap.put("/**", openAuth);

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        logger.info("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }


    //自定义身份认证realm
    @Bean
    public AuthRealm userRealm() {
        return new AuthRealm();
    }

    @Bean("lifecycleBeanPostProcessor")
    //管理shiro生命周期
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    //Shiro注解支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager manager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(manager);
        return authorizationAttributeSourceAdvisor;
    }
}