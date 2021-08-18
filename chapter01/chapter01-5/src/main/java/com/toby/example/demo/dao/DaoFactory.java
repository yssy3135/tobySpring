package com.toby.example.demo.dao;


import com.mysql.cj.protocol.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //애플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정정보라는 표시
public class DaoFactory {

    @Bean // -> 오브젝트 생성을 담당하는 IoC용 메소드라는 표시
    public UserDao userDao(){

        return new UserDao(connectionMaker());
    }


    public ConnectionMaker connectionMaker(){

        //분리해서 중복을 제거한 ConnectionMaker 타입 오브젝트 생성코드
        return new DConnectionMaker();
    }




    public AccountDao accountDao(){
        return new AccountDao(connectionMaker());
    }

    public MessageDao messageDao(){
        return new MessageDao(connectionMaker());
    }


}
