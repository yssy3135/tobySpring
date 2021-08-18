package com.toby.example.demo.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {

    //어떤 클래스로 만들어졌든지 상관없이 makeConnection() 메소드를 호출하기만 하면 Connection 타입의 오브젝트를 만들어서 돌려줄 것이라고 기대할 수 있다.
    public Connection makeConnection() throws ClassNotFoundException, SQLException;

}
