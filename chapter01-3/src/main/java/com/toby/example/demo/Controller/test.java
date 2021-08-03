package com.toby.example.demo.Controller;


import com.toby.example.demo.domain.UserDao;
import com.toby.example.demo.domain.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;


@Controller
public class test {

    @GetMapping("/get")
    public void hello() throws SQLException, ClassNotFoundException {
        Users user = new Users();
        user.setId("testname");
        user.setName("홍길동");
        user.setPassword("123");

        UserDao dao = new UserDao();
        dao.add(user);

    }


}
