package org.geekbang.thinking.in.spring.dependency.injection;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author guxinxin
 * @version 1.0.0
 * @Description 基于 api 资源依赖Constructor 方式注入
 * @createTime 2023年02月19日 11:53:00
 */
public class ApiDependencyConstructorInjectionDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 生成
        BeanDefinition userHolderBeanDefinition = createUserHolderBeanDefinition();
        // 注册
        applicationContext.registerBeanDefinition("userHolder",userHolderBeanDefinition);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResource = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载xml资源，解析生成beanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResource);

        // 启动应用的上下文
        applicationContext.refresh();;

        UserHolder userHolder = applicationContext.getBean(UserHolder.class);

        System.out.println(userHolder);
        applicationContext.close();

    }

    private static BeanDefinition createUserHolderBeanDefinition() {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);

        beanDefinitionBuilder.addConstructorArgReference("superUser");
        return beanDefinitionBuilder.getBeanDefinition();
    }

//    @Bean
//    public UserHolder userHolder(User user) {
//        UserHolder userHolder = new UserHolder(user);
//        userHolder.setUser(user);
//        return userHolder;
//    }
}
