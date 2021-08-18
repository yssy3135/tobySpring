package com.toby.example.demo;

import com.toby.example.demo.dao.DaoFactory;
import com.toby.example.demo.dao.User;
import com.toby.example.demo.dao.UserDao;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


@SpringBootApplication
public class UserDaoTest {


    @Test
    //JUnit 테스트 메소드는 반드시 public으로 선언돼야 한다.
    public void addAndGet() throws SQLException, ClassNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDao dao = context.getBean("userDao",UserDao.class);

        User user = new User();
        user.setId("gyumee");
        user.setName("박성철");
        user.setPassword("springno1");

        dao.add(user);

        User user2 = dao.get(user.getId());

        assertThat(user2.getName(),is(user.getName()));
        assertThat(user2.getPassword(),is(user.getPassword()));



    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        JUnitCore.main("com.toby.example.demo.dao.UserDaoTestTests");




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

        /**
         * 변경전
         */
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());
        System.out.println(user2.getId()+ " 조회 성공");

        /**
         * 변경후
         */
        if(!user.getName().equals(user2.getName())){
            System.out.println("테스트 실패");
        }
        else if(!user.getPassword().equals(user2.getPassword())){
            System.out.println("테스트 실패 (password)");
        }
        else{
            System.out.println("조회 테스트 성공");
        }




    }




}
