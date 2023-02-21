package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 实例化bean
 */
public class BeanInstantiationDemo {
    public static void main(String[] args) {
        BeanFactory factory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");
        // 方式一：静态方法创建Bean
        User user = factory.getBean("user-by-static-method", User.class);
        User user2 = factory.getBean("user-by-instance-method", User.class);
        User user3 = factory.getBean("user-by-factory-method", User.class);

        System.out.println(user);
        System.out.println(user2);
        System.out.println(user3);

    }

}
