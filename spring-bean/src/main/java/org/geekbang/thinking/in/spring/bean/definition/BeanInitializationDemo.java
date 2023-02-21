package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.geekbang.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @author guxinxin
 * @version 1.0.0
 * @Description Bean 初始化
 * @createTime 2022年12月27日 22:16:00
 */
@Configuration
public class BeanInitializationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class(配置类)
        applicationContext.register(BeanInitializationDemo.class);
        // 启动上下文
        applicationContext.refresh();
        // 非延迟初始化，在Spring应用上下文启动时，被初始化
        System.out.println("Spring 应用上下文启动...");
        // 依赖查找
        UserFactory bean = applicationContext.getBean(UserFactory.class);
        System.out.println(bean);

        System.out.println("Spring 应用上下文准备关闭...");
        // 关闭上下文
        applicationContext.close();
        System.out.println("Spring 应用上下文已关闭...");
    }
    @Bean(initMethod = "initUserFactory",destroyMethod = "doDestroy")
    @Lazy(value = true)
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }

}
