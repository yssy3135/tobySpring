package com.toby.example.demo;

import com.toby.example.demo.dao.DaoFactory;
import com.toby.example.demo.dao.UserDao;
import org.apache.catalina.User;
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

        /**
         * 팩토리를 사용해서 오브젝트 생성 (서로 다른 오브젝트)
         */
        DaoFactory factory = new DaoFactory();
        UserDao dao1 = factory.userDao();
        UserDao dao2 = factory.userDao();

        System.out.println(dao1);
        System.out.println(dao2);

        /**
         * getBean을 통해 호출 (동일한 오브젝트)
         */

        UserDao dao3 = context.getBean("userDao",UserDao.class);
        UserDao dao4 = context.getBean("userDao",UserDao.class);
        System.out.println(dao3);
        System.out.println(dao4);



    }

}
