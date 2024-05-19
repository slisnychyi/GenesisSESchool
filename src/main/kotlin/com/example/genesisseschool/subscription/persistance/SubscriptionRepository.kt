package com.example.genesisseschool.subscription.persistance

import com.example.genesisseschool.subscription.Subscriber
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SubscriptionRepository : JpaRepository<Subscriber, Long> {

  fun findByEmail(email: String): Subscriber?

}
