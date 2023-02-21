package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

/**
 * 注解BeanDefinition 方式
 * @author guxinxin
 */
// 3.通过@import
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration class( 配置类)
        applicationContext.register(AnnotationBeanDefinitionDemo.class);
        // 通过beanDefinition 注册api方式
        // 1。命名bean方式
        registerUserDefinitionBeans(applicationContext,"命名bean");
        // 2。非命名bean注册方式
        registerUserDefinitionBeans(applicationContext,"");


        // 启动应用的上下文
        applicationContext.refresh();

        //依赖查找
        Map<String, Config> configBeans = applicationContext.getBeansOfType(Config.class);
        Map<String, User> userBeans = applicationContext.getBeansOfType(User.class);
        System.out.println(configBeans);
        System.out.println(userBeans);


        // 显示的关闭上下文
        applicationContext.close();
    }

    /**
     * 命名bean的注册方式
     * @param registry
     * @param beanName
     */
    public static void registerUserDefinitionBeans(BeanDefinitionRegistry registry,String beanName){
        BeanDefinitionBuilder beanDefinitionBuilder = genericBeanDefinition(User.class);
        beanDefinitionBuilder
                .addPropertyValue("id",3L)
                .addPropertyValue("name","张三");
        if (StringUtils.hasText(beanName)){
            registry.registerBeanDefinition(beanName,beanDefinitionBuilder.getBeanDefinition());
        }else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(),registry);
        }


    }

    /**
     * 非命名的注册方式
     * @param registry
     */
    public static void registerUserDefinitionBeans(BeanDefinitionRegistry registry){
        registerUserDefinitionBeans(registry,null);

    }
    // 2.第二种方式@Component
    @Component // 定义当前类为spring Bean(组件)
    public static class Config{
        /**
         * 通过java注解的方式定义bean
         * @return
         */
        // 1.第一种方式通过@bean注解方式
        @Bean(name = "user")
        public User getUser() {
            User user = new User();
            user.setId(2L);
            user.setName("annotation");
            return user;
        }
    }
}
