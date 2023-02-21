package org.geekbang.thinking.in.spring.bean.factory;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;


/**
 * @author guxinxin
 */
public interface UserFactory {

    default User createUser(){
        return User.createUser();
    }

}
