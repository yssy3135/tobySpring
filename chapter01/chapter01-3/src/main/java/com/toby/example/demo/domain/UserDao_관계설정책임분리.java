package com.toby.example.demo.domain;

import java.sql.*;

public class UserDao_관계설정책임분리 {

    private ConnectionMaker connectionMaker;

    /**
     * DB커넥션을 가져오는 방법을 어떻게 변경하든 UserDao코드는 아무런 영향을 받지 않는다.
     *
     * @param connectionMaker
     */
    public UserDao_관계설정책임분리(ConnectionMaker connectionMaker){

        this.connectionMaker = connectionMaker;

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
