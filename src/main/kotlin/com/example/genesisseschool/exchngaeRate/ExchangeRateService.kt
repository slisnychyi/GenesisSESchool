package com.example.genesisseschool.exchngaeRate

import com.example.genesisseschool.exchngaeRate.persistance.ExchangeRateData
import com.example.genesisseschool.exchngaeRate.persistance.ExchangeRateRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ExchangeRateService(
  val exchangeRateRepository: ExchangeRateRepository,
  val exchangeRateClient: ExchangeRateClient
) {

  fun getExchangeRate(date: LocalDate = LocalDate.now()): ExchangeRate {
    val rateData = exchangeRateRepository.findByDate(date)
    if (rateData != null) {
      return ExchangeRate(rateData.date, rateData.buyingRate, rateData.sellingRate)
    }
    val result = fetchExchangeRateFromClient(date)
    saveExchangeRate(result)
    return result
  }

  fun saveExchangeRate(exchangeRate: ExchangeRate) {
    val exchangeRateData = ExchangeRateData(
      date = exchangeRate.date, buyingRate = exchangeRate.buyRate, sellingRate = exchangeRate.sellRate)
    exchangeRateRepository.save(exchangeRateData)
  }

  private fun fetchExchangeRateFromClient(date: LocalDate): ExchangeRate {
    val exchangeRates = exchangeRateClient.fetchExchangeRates(date)
    if (exchangeRates.isNotEmpty()) {
      val fetchExchangeRates = exchangeRates.last()
      return ExchangeRate(fetchExchangeRates.date, fetchExchangeRates.buying, fetchExchangeRates.selling)
    } else {
      val message = "No exchange rate found for date: $date"
      println(message)
      throw IllegalStateException(message)
    }
  }

}
