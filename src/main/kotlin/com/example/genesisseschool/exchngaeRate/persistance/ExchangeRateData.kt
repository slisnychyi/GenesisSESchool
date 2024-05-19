package com.example.genesisseschool.exchngaeRate.persistance

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "exchange_rates")
data class ExchangeRateData(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0,

  @Column(nullable = false)
  val date: LocalDate,

  @Column(name = "buying_rate", nullable = false)
  val buyingRate: String,

  @Column(name = "selling_rate", nullable = false)
  val sellingRate: String
)
