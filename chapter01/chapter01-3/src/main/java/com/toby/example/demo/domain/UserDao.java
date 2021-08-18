package com.toby.example.demo.domain;

import java.sql.*;

public class UserDao {
    private SimpleConnectionMaker simpleConnectionMaker;
    private ConnectionMaker connectionMaker;

    public UserDao(){
        //UerDao가 SimpleConnectionMaker라는 특정 클래스와 그 코드에 종속적

        connectionMaker = new DConnectionMaker();

    }


    public void add(Users user) throws ClassNotFoundException, SQLException {

        // 인터페이스에 정의된 메소드를 사용하므로 클래스가 바뀐다고 해도 메소드 이름이 변경될 걱정은 없다.
        Connection c = simpleConnectionMaker.makNewConnection();



        PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values (?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public Users get(String id) throws ClassNotFoundException, SQLException {

        // 인터페이스에 정의된 메소드를 사용하므로 클래스가 바뀐다고 해도 메소드 이름이 변경될 걱정은 없다.
        Connection c = simpleConnectionMaker.makNewConnection();

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
