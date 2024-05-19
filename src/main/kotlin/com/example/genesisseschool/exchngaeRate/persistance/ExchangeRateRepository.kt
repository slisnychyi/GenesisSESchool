package com.example.genesisseschool.exchngaeRate.persistance

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface ExchangeRateRepository : JpaRepository<ExchangeRateData, Long> {

  fun findByDate(date: LocalDate): ExchangeRateData?

}
