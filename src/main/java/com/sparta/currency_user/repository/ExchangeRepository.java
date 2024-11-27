package com.sparta.currency_user.repository;

import com.sparta.currency_user.dto.ExchangeGroupResponseDto;
import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.entity.UserCurrency;
import com.sparta.currency_user.entity.UserCurrencyStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface ExchangeRepository extends JpaRepository<UserCurrency, Long> {

    List<UserCurrency> findAllByUserIdAndStatus(Long userId, UserCurrencyStatus status);

    default UserCurrency findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "request not exist")
        );
    }

    @Query("select new com.sparta.currency_user.dto.ExchangeGroupResponseDto(count(u), sum(u.amountInKrw))"
    +" from UserCurrency u where u.user.id = :id and u.status = :status")
    ExchangeGroupResponseDto findGroupExchangeRequestByUserId(Long id, UserCurrencyStatus status);
}
