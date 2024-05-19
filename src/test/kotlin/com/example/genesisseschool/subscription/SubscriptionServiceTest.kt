package com.example.genesisseschool.subscription

import com.example.genesisseschool.subscription.persistance.SubscriptionRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.ArgumentMatchers.argThat
import org.mockito.Mockito.*
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
class SubscriptionServiceTest {

  @Mock
  lateinit var subscriptionRepository: SubscriptionRepository

  @InjectMocks
  lateinit var subscriptionService: SubscriptionService

  @Test
  fun `subscribeUser when subscription does not exist`() {
    val email = "test@example.com"
    val existingSubscription = null
    `when`(subscriptionRepository.findByEmail(email)).thenReturn(existingSubscription)

    subscriptionService.subscribeUser(email)

    verify(subscriptionRepository).save(
      argThat { it.email == "test@example.com" && it.status == SubscriptionStatus.activated }
    )
  }

  @Test
  fun `subscribeUser when subscription already exists`() {
    val email = "test@example.com"
    val existingSubscription = Subscriber(email = email, createdAt = LocalDateTime.now(), status = SubscriptionStatus.activated)
    `when`(subscriptionRepository.findByEmail(email)).thenReturn(existingSubscription)

    subscriptionService.subscribeUser(email)

    verify(subscriptionRepository, never()).save(any())
  }

  @Test
  fun `updateSubscription when user exists`() {
    val email = "test@example.com"
    val existingSubscription = Subscriber(email = email, createdAt = LocalDateTime.now(), status = SubscriptionStatus.activated)
    val newStatus = SubscriptionStatus.deactivated
    `when`(subscriptionRepository.findByEmail(email)).thenReturn(existingSubscription)

    subscriptionService.updateSubscription(email, newStatus)

    verify(subscriptionRepository).save(
      argThat { it.status == newStatus }
    )
  }

}
