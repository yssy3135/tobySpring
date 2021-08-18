package com.toby.example.demo.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CountingDaoFactory {

    @Bean
    public UserDao userDao(){
        //모든 DAO는 여전히 connectionMaker에서 만들어지는 오브젝트를 DI받는다.
        return new UserDao(connectionMaker());
    }


    @Bean ConnectionMaker connectionMaker(){
        return new CountingConnectionMaker(realConnectionMaker());
    }

    @Bean
    public ConnectionMaker realConnectionMaker(){
        return new DConnectionMaker();
    }

}
