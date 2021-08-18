package com.toby.example.demo.domain;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * 상속 관계도 아닌 완전히 독립적인 클래스로 분리
 * 하지만 자유로운 확장이 가능하게 하려면 두 가지 문제를 해결해야한다.
 * makeNewConnection()을 사용해 DB커넥션을 가져오게 했는데
 * 만약 D 사에서만든 DB 커넥션 제공 클래스는 openConnection()이라는 이름을 사용했다면
 * UserDao 내에 있는 add(),get() 메소드의 커넥션을 가져오는 코드를 일일이 변경해야 한다.
 *
 * DB커넥션을 제공하는 클래스가 어떤 것인지를 UserDao가 구체적으로 알고 있어야 한다는 점이다.
 * UserDao에 SimpleConnectionMaker라는 클래스타입의 인스턴스 변수까지 정의해놓고 있으니 N사에서
 * 다른 클래스를 구현하면 어쩔 수 없이 UserDao자체를 다시 수정해야한다.
 *
 * 이런 문제의 원인은 UserDao가 바뀔 수 있는 정보 즉 DB커넥션을 가져오는 클래스에 대해 너무 많이 알고 있기 때문
 * 어떤 클래스가 쓰일지 그 클래스에서 커넥션을 가져오는 메소드는 이름이 뭔지까지 일일이 알고 있어야 한다.
 * 따라서 UserDao는 DB 커넥션을 가져오는 구체적인 방법에 종속되어 버린다.
 *
 * 지금은 UserDao가 SimpleConnectionMaker라는 특정 클래스와 그 코드에 종속적이기 때문에 앞으로 납품후에 고객에 DB 커넥션을 가져오는
 * 방법을 자유롭게 확장하기가 힘들어졌다.
 *
 * -> 클래스를 분리하면서도 이런문제를 해결책은 두개의 클래스가 서로 긴밀하게 연결되어 있지 않도록 중간에 추상적인 느슨한 연결고리를 만들어주는 것.
 * 자바가 추상화를 위해 제공하는 가장 유용한 도구는 바로 인터페이스스
*
 *
 */


//더이상 상속을 이용한 확장 방식을 사용할 필요가 없으니 추상 클래스로 만들 필요가 없다.
public class SimpleConnectionMaker {
    public Connection makNewConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/toby?characterEncoding=utf8", "root", "qwer");

        return c;

    }
}
