package org.geekbang.thinking.in.spring.ioc.overview.container;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * 通过BeanFactory 注解方式获取bean
 */
public class IoCContainerDemo {
    public static void main(String[] args) {
        // 创建bean容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 加载资源
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // 加载配置
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        int beanCount = reader.loadBeanDefinitions(location);
        System.out.println("bean加载的数量" + beanCount );
        lookupCollectionByType(beanFactory);
    }
    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找所以user集合对象 " + users);

        }
    }
}
