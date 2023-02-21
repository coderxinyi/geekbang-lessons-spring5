package org.geekbang.org.geekbang.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StringUtils;

/**
 * @author guxinxin
 * @version 1.0.0
 * @Description TODO
 * @createTime 2023年01月02日 21:54:00
 */
public class HierarchicalDependencyLookupDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(HierarchicalDependencyLookupDemo.class);
        ConfigurableListableBeanFactory beanFactory =applicationContext.getBeanFactory();
        System.out.println("beanFactory : " + beanFactory.getParentBeanFactory());
        // 设置 Parent BeanFactory
        beanFactory.setParentBeanFactory(createBeanFactory());
        System.out.println("beanFactory : " + beanFactory.getParentBeanFactory());
        applicationContext.refresh();
        // 依赖查找对象类型
        applicationContext.close();

    }
    private static BeanFactory createBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 加载资源
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // 加载配置
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);

        return beanFactory;
    }
}
