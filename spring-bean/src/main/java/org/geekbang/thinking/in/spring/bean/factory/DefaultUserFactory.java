package org.geekbang.thinking.in.spring.bean.factory;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author guxinxin
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    /**
     * 方式一：基于PostConstruct
     */
    @PostConstruct
    public void init(){
        System.out.println("PostConstruct : UserFactory初始化...");
    }

    /**
     * 方式二：自定义
     */
    public void initUserFactory(){
        System.out.println("initUserFactory : UserFactory初始化...");
    }

    /**
     * 方式三：InitializingBean
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet : UserFactory初始化...");
    }
    @PreDestroy
    public void preDestroy() {
        System.out.println("preDestroy : UserFactory 销毁中...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy : UserFactory 销毁中...");
    }
    public void doDestroy() {
        System.out.println("自定义销毁...");
    }
}
