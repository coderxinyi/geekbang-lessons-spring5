package org.geekbang.org.geekbang.thinking.in.spring.dependency.lookup;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author guxinxin
 * @version 1.0.0
 * @Description TODO
 * @createTime 2023年02月15日 21:33:00
 */
public class TypeSafetyDependencyLookUpDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(TypeSafetyDependencyLookUpDemo.class);
        applicationContext.refresh();
        // 演示BeanFactory#getBean 方法安全性 不安全
        displayBeanFactory(applicationContext);
        // ObjectFactory#getBeanProvider 方法安全性 不安全
        displayObjectFactoryGetObject(applicationContext);
        // ObjectProvider# getIfAvailable 方法安全性 安全
        displayObjectProviderIfAvailable(applicationContext);
        // ListableBeanFactory#getBeansOfType 方法安全性 安全
        displayListableBeanFactoryGetBeanOfTypes(applicationContext);
        // ObjectProvider#stream 方法安全性 安全
        displayObjectProviderStreams(applicationContext);
        applicationContext.close();

    }

    private static void displayObjectProviderStreams(AnnotationConfigApplicationContext applicationContext) {
        // ObjectFactory is ObjectProvider
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderStreams", () -> beanProvider.stream().forEach(System.out::println));

    }

    private static void displayListableBeanFactoryGetBeanOfTypes(ListableBeanFactory applicationContext) {
        printBeansException("displayListableBeanFactoryGetBeanOfTypes", () -> applicationContext.getBeansOfType(User.class));
    }


    private static void displayObjectProviderIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        // ObjectFactory is ObjectProvider
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderIfAvailable", () -> beanProvider.getIfAvailable());

    }
    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
        // ObjectFactory is ObjectProvider
        ObjectFactory<User> beanProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectFactoryGetObject", () -> beanProvider.getObject());

    }

    public static void displayBeanFactory(BeanFactory beanFactory) {

        printBeansException("displayBeanFactory", () ->beanFactory.getBean(User.class));
    }

    private static void printBeansException(String source, Runnable runnable) {
        System.out.println("source from : " + source);
        try {
            runnable.run();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
