package edu.pasudo123.board.core.config.profile;

/**
 * Created by pasudo123 on 2019-09-01
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public class ProfileService {

    private String name;

    public ProfileService(String name){
        this.name = name;
    }

    public String getProfile(){
        return "profile " + this.name;
    }
}
