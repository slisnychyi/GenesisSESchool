package com.example.genesisseschool.controller

import com.example.genesisseschool.exchngaeRate.ExchangeRate
import com.example.genesisseschool.exchngaeRate.ExchangeRateService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate


@RestController
@RequestMapping("/api/v1/exchange-rate")
class ExchangeRateController(val exchangeRateService: ExchangeRateService) {

  @GetMapping
  fun getExchangeRate(@RequestParam(required = false) date: LocalDate?): ResponseEntity<ExchangeRate> {
    println("received request to get exchange rate for $date")
    val requestDate = date ?: LocalDate.now()
    val exchangeRate = exchangeRateService.getExchangeRate(requestDate)
    return ResponseEntity.ok(exchangeRate);
  }

}
