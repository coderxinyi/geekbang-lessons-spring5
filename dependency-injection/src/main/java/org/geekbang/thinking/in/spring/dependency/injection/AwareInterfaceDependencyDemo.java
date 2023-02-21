package org.geekbang.thinking.in.spring.dependency.injection;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author guxinxin
 * @version 1.0.0
 * @Description 基于 {@link Aware} 接口回掉方式注入
 * @createTime 2023年02月19日 11:53:00
 */
public class AwareInterfaceDependencyDemo implements BeanFactoryAware, ApplicationContextAware {

    private static BeanFactory beanFactory;

    private static ApplicationContext context;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration class( 配置类)
        applicationContext.register(AwareInterfaceDependencyDemo.class);


        // 启动应用的上下文
        applicationContext.refresh();;

        System.out.println(beanFactory == applicationContext.getBeanFactory());
        System.out.println(context == applicationContext);

        applicationContext.close();

    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        AwareInterfaceDependencyDemo.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AwareInterfaceDependencyDemo.context = applicationContext;
    }
}
