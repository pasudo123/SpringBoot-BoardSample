package edu.pasudo123.board.core.common;

/**
 * Created by pasudo123 on 2019-07-29
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/

import lombok.Getter;
import org.springframework.data.domain.Sort;

/**
 * reference : https://github.com/cheese10yun/spring-jpa-best-practices/blob/master/doc/step-12.md
 */
@Getter
public class PageRequest {

    private int page;
    private int size;
    private Sort.Direction direction;
    private String standard;

    public void setPage(int page){
        this.page = (page <= 0) ? 1 : page;
    }

    public void setSize(int size){
        final int DEFEAULT_SIZE = 10;
        final int MAX_SIZE = 50;
        this.size = (size > MAX_SIZE) ? DEFEAULT_SIZE : size;
    }

    public void setDirection(Sort.Direction direction){
        this.direction = direction;
    }

    public void setStandard(String standard){
        this.standard = standard;
    }

    public org.springframework.data.domain.PageRequest of(){
        return org.springframework.data.domain.PageRequest.of(page-1, size, direction, standard);
    }
}
