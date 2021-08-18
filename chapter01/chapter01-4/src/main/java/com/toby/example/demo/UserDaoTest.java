package com.toby.example.demo;

import com.toby.example.demo.dao.DaoFactory;
import com.toby.example.demo.dao.UserDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;


@SpringBootApplication
public class UserDaoTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        UserDao dao = new DaoFactory().userDao();

        System.out.println(dao.get("testname").getName());



    }

}
