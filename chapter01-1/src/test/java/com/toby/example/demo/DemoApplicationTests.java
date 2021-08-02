package com.toby.example.demo;

import com.toby.example.demo.domain.UserDao;
import com.toby.example.demo.domain.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() throws SQLException, ClassNotFoundException {

        Users user = new Users();
        user.setId("testname");
        user.setPassword("123");
        user.setName("홍길동");

        UserDao dao = new UserDao();
        Users getUser = dao.get("testname");

        Assertions.assertEquals(user.getName(),getUser.getName());

    }

}
