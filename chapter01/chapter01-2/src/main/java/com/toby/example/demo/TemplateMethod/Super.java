package com.toby.example.demo.TemplateMethod;

public abstract class Super {

    //기본 알고리즘 골격을 담은 메소드를 템플릿 메소드라 부른다.
//    템플릿 메소드는 서브클래스에서 오버라이드하거나 구현할 메서드를 사용한다.
    public void templateMehotd(){
        hookMethod();
        abstractMethod();
    }

    //선택적으로 오버라이드 가능한 훅메소드
    protected void hookMethod(){};
    //서브클래스에서 반드시 구현해야하는 추상 메소드
    public abstract void abstractMethod();



    //슈퍼클래스의 메소드를 오버라이드하거나 구현해서 기능을 확장한다.
    //다양한 확장 클래스를 만들 수 있다.
    public class Sub1 extends Super{


        @Override
        public void abstractMethod() {

        }
    }

}

