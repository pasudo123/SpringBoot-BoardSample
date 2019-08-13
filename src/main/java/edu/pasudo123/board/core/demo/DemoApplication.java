package edu.pasudo123.board.core.demo;

import sun.net.idn.StringPrep;

import java.util.Optional;

/**
 * Created by pasudo123 on 2019-08-10
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public class DemoApplication {

    public static void main(String[]args){

        String username = "홍길동";

        String testName = Optional.ofNullable(username).orElse(returningName("orElse"));

        String testName2 = Optional.ofNullable(username).orElseGet(() -> returningName("orElseGet"));

        System.out.println(username);
        System.out.println(testName);
        System.out.println(testName2);
    }

    public static String returningName(String methodName){
        System.out.println(methodName + ", 호출 :: returningName");

        return "고길동";
    }
}


