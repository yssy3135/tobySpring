package com.toby.example.demo.domain;

import java.sql.*;

public abstract class UserDao_상속 {


    /**
     * 구현 코드는 제거되고 추상 메소드로 바뀌었다 메소드의 구현은 서브클래스가 담당한다.
     */
    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;



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




    public class NUserDao extends UserDao_상속 {
        /**
         * 상속을 통해 확장된 getConnection()메소드
         */
        @Override
        public Connection getConnection() throws ClassNotFoundException, SQLException {
            // N사 DB connection 생성코드
            return null;
        }
    }

    public class DUserDao extends UserDao_상속 {


        /**
         * 상속을 통해 확장된 getConnection()메소드
         */
        @Override
        public Connection getConnection() throws ClassNotFoundException, SQLException {
            // D사 DB connection 생성코드
            return null;
        }



    }



}


