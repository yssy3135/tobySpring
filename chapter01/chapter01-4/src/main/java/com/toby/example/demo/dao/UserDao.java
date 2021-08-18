package com.toby.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private SimpleConnectionMaker simpleConnectionMaker;
    private ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker){
        //UerDao가 SimpleConnectionMaker라는 특정 클래스와 그 코드에 종속적
        simpleConnectionMaker = new SimpleConnectionMaker();


        this.connectionMaker = new DConnectionMaker();

    }


    public void add(Users user) throws ClassNotFoundException, SQLException {

        Connection c = connectionMaker.makeConnection();



        PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values (?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public Users get(String id) throws ClassNotFoundException, SQLException {

        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();

        Users user = new Users();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }

}
