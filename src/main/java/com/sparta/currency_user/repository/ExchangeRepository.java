package com.sparta.currency_user.repository;

import com.sparta.currency_user.entity.UserCurrency;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepository extends JpaRepository<UserCurrency, Long> {

    List<UserCurrency> findAllByUserId(Long userId);
}
