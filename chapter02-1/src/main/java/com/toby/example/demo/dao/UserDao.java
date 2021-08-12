package com.toby.example.demo.dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {


    /**
     * 1.7.3 의존관계 검색과 주입 p.118
     * UserDao 생서자를 만들었다
     * 이렇게 해도 UserDao는 여전히 자신이 어떤 ConnnectionMaker 오브젝트를 사용할지 미리 알지 못한다.
     * 여전히 코드 대상을 COnnectionMaker 인터페이스 뿐이다.
     * 런타임시에 DaoFackotry가 만들어서 돌려주는 오브젝트와 다이내믹하게 런타임 의존관계를 맺는다.
     * 따라서 IoC 개념을 잘 따르고 있으며 그 혜택을 받고 있는 코드다.
     *
     * public UserDao(){
     *     DaoFactory daoFactory = new DaoFactory();
     *     this.connectionMaker = daoFactory.connectionMaker();
     * }
     *
     * 하지만 적용방법은 외부로부터 주입이아니라 스스로 IoC컨테이너인 DaoFactory에게 요청하는 것이다.
     * DaoFactory의 경우라면 미리 준비된 메소드를 호출하면 되니깐 단순히 요청으로 보이겠지만
     * 이런 작업을 일반화한 스프링의 애플리케이션 컨텍스트라면 미리 정해놓은 이름을 전달해서 그 이름에 해당하는
     * 오브젝트를 찾게 된다. 따라서 이를 일종의 검색 이라고 볼수 있다.
     * 또한 그 때상이 런타임 의존관계를 가질 오브젝트이므로 의존관계 검색 이라고 부르는 것이다.
     */
    public UserDao(){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DaoFactory.class);
        this.connectionMaker = context.getBean("connectionMaker",ConnectionMaker.class);
    }





    /**
     * 초기에 설정하면 사용중에는 바뀌지 않는 읽기 전용 인스턴스 변수
     */
    private ConnectionMaker connectionMaker;

    /**
     * 1.6
     * 멀티스레드 환경에서
     * 매번 새로운 값으로 바뀌는 정보를 담은 인스턴스 변수, 심각한 문제가 발생한다.
     *
     */
    private Connection c;
    private User user;

    /**
     * 클래스 밖에서는 생성하지 못하도록 private
     * 싱글톤 오브젝트를 저장할 수 있는 자신과 같은 타입의 스태틱 필드를 정의
     */
    private static UserDao INSTANCE;


    /**
     * 1.7.2 p.116
     * 이렇게 DI컨테이너에 의해 런타임 시에 의존 오브젝트를 사용할 수 있도록 그 래퍼런스를 전달받는 과정이
     * 마치 메소드(생성자)를 통해 DI컨테이너가 UserDao에게 주입해 주는 것과 같다고 해서 이를 의존관계 주입이라고 부른다.
     *
     */
    UserDao(ConnectionMaker connectionMaker){

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
