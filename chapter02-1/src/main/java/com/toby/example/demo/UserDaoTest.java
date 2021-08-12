package com.toby.example.demo;

import com.toby.example.demo.dao.CountingConnectionMaker;
import com.toby.example.demo.dao.DaoFactory;
import com.toby.example.demo.dao.User;
import com.toby.example.demo.dao.UserDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractRefreshableApplicationContext;

import java.sql.SQLException;


@SpringBootApplication
public class UserDaoTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {



        //@Configuration이 붙은 자바 코드를 설정정보로 사용려면 AnnotationConfigApplicationContext를 이용하면 된다.
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

        //ApplicationContext의 getBean()이라는 메소드를 이용해 UserDao의 오브젝트를 가져올 수 있다.
        UserDao dao = context.getBean("userDao",UserDao.class);



        User user = new User();
        user.setId("user");
        user.setName("백기선");
        user.setPassword("married");

        dao.add(user);

        System.out.println(user.getId() + "등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId()+ " 조회 성공");




    }

}
