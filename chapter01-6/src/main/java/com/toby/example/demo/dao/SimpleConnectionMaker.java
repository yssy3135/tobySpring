package com.toby.example.demo.dao;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



//더이상 상속을 이용한 확장 방식을 사용할 필요가 없으니 추상 클래스로 만들 필요가 없다.
public class SimpleConnectionMaker {
    public Connection makNewConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/toby?characterEncoding=utf8", "root", "qwer");

        return c;

    }
}
