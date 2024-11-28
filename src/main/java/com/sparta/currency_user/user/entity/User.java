package com.sparta.currency_user.user.entity;

import com.sparta.currency_user.common.entity.TimeBaseEntity;
import com.sparta.currency_user.exchange.entity.UserCurrency;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Entity
@Getter
public class User extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    /*
    UserCurrency 엔티티와 연관관계 설정, CascadeType.ALL 을 이용해 유저 데이터가 삭제되면 영속성 전이를
    이용해 해당 유저가 수행한 모든 환전요청도 같이 삭제된다.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<UserCurrency> currencies = new ArrayList<>();

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User() {
    }
}