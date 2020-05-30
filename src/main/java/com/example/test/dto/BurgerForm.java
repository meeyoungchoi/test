package com.example.test.dto;

import com.example.test.vo.Burger;
import lombok.Data;

//질문 게터/세터 는 @Data에 의해서 만들어지는가?
@Data
public class BurgerForm {

    private Integer id;
    private String name;
    private Integer price;

    public Burger toEntity() {
        return new Burger(id, name, price);//질문 이떄 id에 저장된 값은 기존에 버거리파지토리에서 가져온 버거의 원래 id값과 같은가?
    }

}
