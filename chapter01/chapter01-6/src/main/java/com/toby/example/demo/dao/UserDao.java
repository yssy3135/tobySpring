package com.toby.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    /**
     * 초기에 설정하면 사용중에는 바뀌지 않는 읽기 전용 인스턴스 변수
     */
    private ConnectionMaker connectionMaker;

    /**
     * 멀티스레드 환경에서
     * 매번 새로운 값으로 바뀌는 정보를 담은 인스턴스 변수, 심각한 문제가 발생한다.
     */
    private Connection c;
    private User user;

    /**
     * 클래스 밖에서는 생성하지 못하도록 private
     * 싱글톤 오브젝트를 저장할 수 있는 자신과 같은 타입의 스태틱 필드를 정의
     */
    private static UserDao INSTANCE;

    private UserDao(ConnectionMaker connectionMaker){
        this.connectionMaker = connectionMaker;
    }

    //한번 오브젝트(싱글톤)가 만들어지고 난 후에는 getInstance()메소드를 통해 이미 만들어져 스태틱필드에 저장해둔 오브젝트를 넘겨준다.
    public static synchronized UserDao getInstance(){
        if(INSTANCE == null) INSTANCE = new UserDao(new DConnectionMaker());

        return INSTANCE;
    }


    /******************************************************/


    public void add(User user) throws ClassNotFoundException, SQLException {

        this.c = connectionMaker.makeConnection();



        PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values (?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }


    public User get(String id) throws ClassNotFoundException, SQLException {

        this.c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();

        this.user = new User();

        this.user.setId(rs.getString("id"));
        this.user.setName(rs.getString("name"));
        this.user.setPassword(rs.getString("password"));




        rs.close();
        ps.close();
        c.close();

        return user;
    }

}
