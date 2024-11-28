package com.sparta.currency_user.repository;

import com.sparta.currency_user.dto.ExchangeGroupResponseDto;
import com.sparta.currency_user.entity.UserCurrency;
import com.sparta.currency_user.entity.UserCurrencyStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface ExchangeRepository extends JpaRepository<UserCurrency, Long> {

    /**
     * userId에 해당되는 유저 + status 가 NORMAL 인 환전 요청 기록을 조회
     * @param id where 조건에 포함되는 userId
     * @param status where 조건에 포함되는 status
     * @return UserCurrency 객체 List
     */
    List<UserCurrency> findAllByUserIdAndStatus(Long id, UserCurrencyStatus status);

    default UserCurrency findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "request not exist")
        );
    }

    /**
     * userId에 해당되는 유저 + status 가 NORMAL 인 환전 요청 기록의 그룹 정보 조회
     * @param id where 조건에 포함되는 userId
     * @param status where 조건에 포함되는 status
     * @return ExchangeGroupResponseDto 총 요청 수, 총 요청 금액이 담겨있음.
     */
    @Query(
        "select new com.sparta.currency_user.dto.ExchangeGroupResponseDto(count(u), sum(u.amountInKrw))"
            + " from UserCurrency u where u.user.id = :id and u.status = :status")
    ExchangeGroupResponseDto findGroupExchangeRequestByUserId(Long id, UserCurrencyStatus status);
}
