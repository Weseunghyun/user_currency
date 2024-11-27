package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.ExchangeRequestDto;
import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchange-requests")
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;

    @PostMapping
    public ResponseEntity<ExchangeResponseDto> createExchangeRequest(
        @RequestBody ExchangeRequestDto requestDto
    ){

        ExchangeResponseDto responseDto = exchangeService.createExchangeRequest(
            requestDto.getUserId(),
            requestDto.getCurrencyId(),
            requestDto.getAmountInKrw()
        );
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
