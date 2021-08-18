package com.toby.example.demo.dao;


import com.mysql.cj.protocol.Message;

/**
 * 팩토리
 * 이 클래스의 역할은 객체의 생성 방법을 결정하고 그렇게 만들어진 오브젝트를 돌려주는 것.
 * 이런 일을 하는 오브젝트를 흔히 팩토리라고 부른다.
 * 단지 오브젝트를 생성하는 쪽과 생성된 오브젝트를 사용하는 쪽의 역할과 책임을 깔끔하게 분리하려는 목적으로 사용하는 것이다.
 */
public class DaoFactory {

    public ConnectionMaker connectionMaker(){

        //분리해서 중복을 제거한 ConnectionMaker 타입 오브젝트 생성코드
        return new DConnectionMaker();
    }


    public UserDao userDao(){

        return new UserDao(connectionMaker());
    }


    public AccountDao accountDao(){
        return new AccountDao(connectionMaker());
    }

    public MessageDao messageDao(){
        return new MessageDao(connectionMaker());
    }


}
