package org.geekbang.thinking.in.spring.ioc.overview.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.overview.annotation.Super;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.geekbang.thinking.in.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.lang.Nullable;

import java.util.Map;

/**
 * 依赖查找实例
 * 1.通过名称查找
 * @author guxinxin
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

        // bean来源一：自定义的bean
        UserRepository userRepository = (UserRepository) beanFactory.getBean("userRepository",UserRepository.class);
        // System.out.println(userRepository.getUsers());

        // bean来源二：依赖注入(内建的依赖)
        System.out.println(userRepository.getBeanFactory());
        //System.out.println(userRepository.getBeanFactory() == beanFactory);
        // 依赖查找(错误)
        // System.out.println(beanFactory.getBean(BeanFactory.class));

        ObjectFactory<User> objectFactory = userRepository.getObjectFactory();
        ObjectFactory<ApplicationContext> applicationContextObjectFactory = userRepository.getApplicationContextObjectFactory();

        System.out.println(applicationContextObjectFactory.getObject());
        System.out.println(applicationContextObjectFactory.getObject() == beanFactory);
        System.out.println(objectFactory.getObject());

        // bean来源三：容器内建bean
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("environment: " + environment);

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        UserRepository userRepository1 = (UserRepository) beanFactory.getBean("userRepository",UserRepository.class);
        whoIsIoc(userRepository1,applicationContext);


    }
    private static void whoIsIoc(UserRepository userRepository, ApplicationContext applicationContext){
        System.out.println("-----");
        // 为啥不相等 查看 AbstractRefreshableApplicationContext 里面依赖了
        // @Nullable
        // private DefaultListableBeanFactory beanFactory;
        System.out.println(userRepository.getBeanFactory() == applicationContext);
    }

}
