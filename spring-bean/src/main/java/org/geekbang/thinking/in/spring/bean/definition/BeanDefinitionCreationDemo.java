package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * {@link BeanDefinition}
 * @author guxinxin
 */
public class BeanDefinitionCreationDemo {

    public static void main(String[] args) {
        // 1通过 BeanDefinitionBuilder 构建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // 通过熟悉设置
        beanDefinitionBuilder.addPropertyValue("id",12);
        beanDefinitionBuilder.addPropertyValue("name","哈哈");
        // 获取beanDefinition 实例，获取beanDefinition并不是最终状态，可以自定义修改
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        //2 通过 AbstractBeanDefinition
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        mutablePropertyValues.add("id",2).add("name","嘿嘿");
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);

    }
}
