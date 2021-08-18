package com.toby.example.demo;

import com.toby.example.demo.domain.ConnectionMaker;
import com.toby.example.demo.domain.DConnectionMaker;
import com.toby.example.demo.domain.UserDao_관계설정책임분리;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        //UserDao가 사용할 ConnectionMaker구현 클래스를 결정하고 오브젝트를 만든다.
        ConnectionMaker connectionMaker = new DConnectionMaker();

        //1. UserDao생성
        //2. 사용할 ConnectionMaker 타입의 오브젝트 제공 결국 두 오브젝트 사이의 의존관계 설정 효과
        UserDao_관계설정책임분리 dao = new UserDao_관계설정책임분리(connectionMaker);


    }

}
