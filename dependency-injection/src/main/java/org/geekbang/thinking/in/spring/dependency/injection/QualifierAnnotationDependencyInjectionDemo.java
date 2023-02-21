package org.geekbang.thinking.in.spring.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author guxinxin
 * @version 1.0.0
 * @Description TODO
 * @createTime 2023年02月20日 22:13:00
 */
public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired
    private User user;

    @Autowired
    @Qualifier("user")
    private User nameUser;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration class( 配置类)
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResource = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载xml资源，解析生成beanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResource);


        // 启动应用的上下文
        applicationContext.refresh();;

        QualifierAnnotationDependencyInjectionDemo bean =
                applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);


        System.out.println(bean.user);
        System.out.println(bean.nameUser);

        applicationContext.close();
    }
}
