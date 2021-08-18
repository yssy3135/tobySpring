package com.toby.example.demo.dao;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * DAO가 DB를 얼마나 ㅁ낳이 연결해서 사용하는지 파악
 */

public class CountingConnectionMaker implements ConnectionMaker{

    int counter = 0;
    private ConnectionMaker realconnectionMaker;


    public CountingConnectionMaker(ConnectionMaker realconnectionMaker) {
        this.realconnectionMaker = realconnectionMaker;
    }

    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        this.counter++;

        return realconnectionMaker.makeConnection();
    }

    public int getCounter(){
        return this.counter;
    }

}
