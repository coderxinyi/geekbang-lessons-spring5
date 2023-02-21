package org.geekbang.thinking.in.spring.dependency.injection;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author guxinxin
 * @version 1.0.0
 * @Description 基于 api 资源依赖setter 方式注入
 * @createTime 2023年02月19日 11:53:00
 */
public class AutoWiringDependencySetterInjectionDemo {

    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String xmlResource = "classpath:/META-INF/autowiring-dependency-setter-injection.xml";
        // 加载xml资源，解析生成beanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResource);

        UserHolder bean = beanFactory.getBean(UserHolder.class);
        System.out.println(bean);

    }

}
