package org.geekbang.thinking.in.spring.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author guxinxin
 * @version 1.0.0
 * @Description 基于 构造器 方式注入
 * @createTime 2023年02月19日 11:53:00
 */
public class XmlDependencyConstructorInjectionDemo {

    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String xmlResource = "classpath:/META-INF/dependency-constructor-injection.xml";
        // 加载xml资源，解析生成beanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResource);

        UserHolder bean = beanFactory.getBean(UserHolder.class);
        System.out.println(bean);

    }
}
