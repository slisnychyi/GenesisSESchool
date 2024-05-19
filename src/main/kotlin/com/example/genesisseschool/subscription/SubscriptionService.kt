package com.example.genesisseschool.subscription

import com.example.genesisseschool.subscription.persistance.SubscriptionRepository
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import java.time.LocalDateTime

@Service
class SubscriptionService(val subscriptionRepository: SubscriptionRepository) {

  fun subscribeUser(email: String) {
    val subscription = subscriptionRepository.findByEmail(email)
    if (subscription != null) {
      print("Subscription already exists.")
    } else {
      val subscriber = Subscriber(email = email, createdAt = LocalDateTime.now(), status = SubscriptionStatus.activated)
      subscriptionRepository.save(subscriber)
    }
  }

  fun updateSubscription(email: String, status: SubscriptionStatus) {
    subscriptionRepository.findByEmail(email)?.let {
      subscriptionRepository.save(Subscriber(id = it.id, email = it.email, status = status, createdAt = it.createdAt))
    } ?: {
      val message = "No such user with email = $email"
      print(message)
      throw IllegalArgumentException(message)
    }
  }

  fun getSubscribedUsers(): List<Subscriber> {
    return subscriptionRepository.findAll()
  }

}
