package org.geekbang.thinking.in.spring.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * @author guxinxin
 * @version 1.0.0
 * @Description 基于 java 注解 字段注入 方式注入
 * @createTime 2023年02月19日 11:53:00
 */
public class AnnotationDependencyFieldInjectionDemo {

    //     @Autowired 会忽略掉静态字段 （static）
    @Autowired
    private UserHolder userHolder;

    @Resource
    private UserHolder userHolder2;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration class( 配置类)
        applicationContext.register(AnnotationDependencyFieldInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResource = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载xml资源，解析生成beanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResource);

        // 启动应用的上下文
        applicationContext.refresh();;

        AnnotationDependencyFieldInjectionDemo demo =
                applicationContext.getBean(AnnotationDependencyFieldInjectionDemo.class);

        UserHolder userHolder = demo.userHolder;
        UserHolder userHolder2 = demo.userHolder2;

        System.out.println(userHolder);
        System.out.println(userHolder2);

        applicationContext.close();

    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
