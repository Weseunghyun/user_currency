package com.sparta.currency_user.exchange.controller;

import com.sparta.currency_user.exchange.dto.ExchangeGroupResponseDto;
import com.sparta.currency_user.exchange.dto.ExchangeRequestDto;
import com.sparta.currency_user.exchange.dto.ExchangeResponseDto;
import com.sparta.currency_user.exchange.service.ExchangeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchange-requests")
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;

    /**
     * 환전 요청 생성 메서드
     * @param requestDto 환전을 요청하는 userId, 통화 Id, 요청금액을 담고있음
     * @return 환전 요청 응답 DTO 와 OK 코드 반환
     */
    @PostMapping
    public ResponseEntity<ExchangeResponseDto> createExchangeRequest(
        @Validated @RequestBody ExchangeRequestDto requestDto
    ) {
        ExchangeResponseDto responseDto = exchangeService.createExchangeRequest(
            requestDto.getUserId(),
            requestDto.getCurrencyId(),
            requestDto.getAmountInKrw()
        );
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * 특정 사용자의 환전 요청 기록을 전부 조회하는 메서드
     * @param id 조회할 사용자의 ID
     * @return 환전 요청 응답 DTO List 와 OK 코드 반환
     */
    @GetMapping("/{id}")
    public ResponseEntity<List<ExchangeResponseDto>> findExchangeRequests(
        @PathVariable Long id
    ) {
        List<ExchangeResponseDto> responseDtos = exchangeService.findExchangeRequests(id);

        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    /**
     * 특정 사용자의 환전 요청을 그룹화하여 조회하는 메서드
     * @param id 조회할 사용자의 ID
     * @return 환전 요청 그룹화 응답 DTO 와 OK 코드 반환
     */
    @GetMapping("/group/{id}")
    public ResponseEntity<ExchangeGroupResponseDto> findGroupExchangeRequest(
        @PathVariable Long id
    ) {
        return new ResponseEntity<>(exchangeService.findGroupExchangeRequest(id), HttpStatus.OK);
    }

    /**
     * 요청 상태를 CANCELED 로 업데이트 하는 메서드
     * @param id 업데이트할 환전 요청 기록의 ID
     * @return 환전 요청 기록 업데이트 응답 DTO 와 OK 코드 반환
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ExchangeResponseDto> updateExchangeRequest(
        @PathVariable Long id
    ) {
        return new ResponseEntity<>(exchangeService.updateExchangeRequest(id), HttpStatus.OK);
    }
}
