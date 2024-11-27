package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.ExchangeGroupResponseDto;
import com.sparta.currency_user.dto.ExchangeRequestDto;
import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.service.ExchangeService;
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

    @PostMapping
    public ResponseEntity<ExchangeResponseDto> createExchangeRequest(
        @Validated @RequestBody ExchangeRequestDto requestDto
    ){

        ExchangeResponseDto responseDto = exchangeService.createExchangeRequest(
            requestDto.getUserId(),
            requestDto.getCurrencyId(),
            requestDto.getAmountInKrw()
        );
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ExchangeResponseDto>> findExchangeRequests(
        @PathVariable Long id
    ) {
        List<ExchangeResponseDto> responseDtos = exchangeService.findExchangeRequests(id);

        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<ExchangeGroupResponseDto> findGroupExchangeRequest(
        @PathVariable Long id
    ){
           return new ResponseEntity<>(exchangeService.findGroupExchangeRequest(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ExchangeResponseDto> updateExchangeRequest(
        @PathVariable Long id
    ){
        return new ResponseEntity<>(exchangeService.updateExchangeRequest(id), HttpStatus.OK);
    }
}
