package com.laituo.cmsFile.config;

import com.laituo.cmsFile.shiro.JwtFilter;
import com.laituo.cmsFile.shiro.MyCredentialsMatcher;
import com.laituo.cmsFile.shiro.MyRealm;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @PackageName:com.ye.shiro.config
 * @ClassName:ShrioConfig
 * @Description:
 * @author:何进业
 * @date:2021/4/29 23:09
 */
@Configuration
public class ShrioConfig {


    @Autowired
    private MyRealm myRealm;

    @Autowired
    private MyCredentialsMatcher myCredentialsMatcher;



    //创建shiro安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultSecurityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //配置realm
        myRealm.setCredentialsMatcher(myCredentialsMatcher);
        securityManager.setRealm(myRealm);
        return securityManager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> tokenFilterMap = new HashMap<>();
        tokenFilterMap.put("jwt", new JwtFilter());
        filterFactoryBean.setFilters(tokenFilterMap);
        //anon jwt自定义过滤器无需登录
        Map<String,String> filterMap = new LinkedHashMap<>();
//        filterMap.put("/test/**", "jwt");//需要token
//        filterMap.put("/user/**","jwt");

        filterMap.put("/login","anon");
        filterMap.put("/logout","anon");
        filterMap.put("/register","anon");
        filterMap.put("/index.html","anon");
        filterMap.put("/upload/**","anon");
        filterMap.put("/**","jwt");

        filterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return filterFactoryBean;
    }


    // 开启注解代理
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
}
