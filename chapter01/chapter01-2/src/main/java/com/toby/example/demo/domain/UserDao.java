package com.toby.example.demo.domain;

import java.sql.*;

public class UserDao {

    /**
     * DB 연결 메소드를 추출해서 중복을 제거
     * DB 연결 기능이 필요하면 getConnection메소드를 이용하게 한다.
     * @return Connection
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/toby?characterEncoding=utf8", "root", "qwer");

        return c;
    }


    public void add(Users user) throws ClassNotFoundException, SQLException {

        Connection c = getConnection();

        PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values (?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public Users get(String id) throws ClassNotFoundException, SQLException {

        Connection c = getConnection();

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
