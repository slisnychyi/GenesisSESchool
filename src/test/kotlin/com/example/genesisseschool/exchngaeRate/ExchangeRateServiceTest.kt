package com.example.genesisseschool.exchngaeRate

import com.example.genesisseschool.exchngaeRate.persistance.ExchangeRateData
import com.example.genesisseschool.exchngaeRate.persistance.ExchangeRateRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class ExchangeRateServiceTest {
  @Mock
  lateinit var exchangeRateRepository: ExchangeRateRepository

  @Mock
  lateinit var exchangeRateClient: ExchangeRateClient

  @InjectMocks
  lateinit var exchangeRateService: ExchangeRateService

  @Test
  fun `getExchangeRate with existing rate in repository`() {
    val currentDate = LocalDate.now()
    val mockExchangeRateData = ExchangeRateData(1, currentDate, "1", "2")
    `when`(exchangeRateRepository.findByDate(currentDate)).thenReturn(mockExchangeRateData)

    val exchangeRate = exchangeRateService.getExchangeRate()

    assert(exchangeRate.date == currentDate)
    assert(exchangeRate.buyRate == "1")
    assert(exchangeRate.sellRate == "2")
  }

  @Test
  fun `getExchangeRate with missing rate in repository`() {
    val currentDate = LocalDate.now()
    `when`(exchangeRateRepository.findByDate(currentDate)).thenReturn(null)
    val mockExchangeRate = ExchangeRateResponse(buying = "37.1", selling = "39.1", currentDate)
    `when`(exchangeRateClient.fetchExchangeRates(currentDate)).thenReturn(listOf(mockExchangeRate))

    val exchangeRate = exchangeRateService.getExchangeRate()

    assertThat(exchangeRate.buyRate).isEqualTo("37.1")
    assertThat(exchangeRate.sellRate).isEqualTo("39.1")
    assertThat(exchangeRate.date).isEqualTo(currentDate)
  }

}
