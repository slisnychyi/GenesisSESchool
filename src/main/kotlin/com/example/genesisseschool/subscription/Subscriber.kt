package com.example.genesisseschool.subscription

import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "subscribers")
data class Subscriber(
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0,

  @Column(nullable = false)
  val email: String,

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  val status: SubscriptionStatus,

  @Column(name = "created_at", nullable = false, updatable = false)
  val createdAt: LocalDateTime = LocalDateTime.now()
)
