package com.example.genesisseschool.exchngaeRate

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.time.LocalDate

@Component
class ExchangeRateClient(
  private val restTemplate: RestTemplate,
  @Value("\${exchange.rate.api.url}") private val apiUrl: String
) {
  fun fetchExchangeRates(requestDate: LocalDate): List<ExchangeRateResponse> {
    val requestMonth = toMonth(requestDate)
    val url = "$apiUrl?exchanger_id=9&currency=usd&from=$requestMonth&to=$requestMonth"
    try {
      val response = restTemplate.getForObject(url, Array<ExchangeRateResponse>::class.java)
      return response?.toList() ?: emptyList()
    } catch (ex: Exception) {
      val message = "Unable to fetch exchange rate for $requestDate. details = ${ex.message}"
      println(message)
      throw IllegalStateException(message, ex)
    }
  }

  private fun toMonth(requestDate: LocalDate): String {
    val monthValue = if (requestDate.monthValue < 10) "-0${requestDate.monthValue}" else "-${requestDate.monthValue}"
    return "${requestDate.year}$monthValue"
  }

}

data class ExchangeRateResponse(
  val buying: String,
  val selling: String,
  val date: LocalDate
)
