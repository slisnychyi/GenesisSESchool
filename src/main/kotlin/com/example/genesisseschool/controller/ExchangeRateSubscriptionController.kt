package com.example.genesisseschool.controller

import com.example.genesisseschool.subscription.SubscriptionService
import com.example.genesisseschool.subscription.SubscriptionStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/exchange-rate/subscription")
class ExchangeRateSubscriptionController(val subscriptionService: SubscriptionService) {

  @PostMapping
  fun subscribe(@RequestBody request: SubscribeRequest): ResponseEntity<String> {
    println("received request from ${request.email} to subscribe for exchange rate")
    subscriptionService.subscribeUser(request.email)
    return ResponseEntity.ok().body("User with ${request.email} was subscribed successfully")
  }

  @PutMapping
  fun updateSubscription(@RequestBody request: SubscribeRequest): ResponseEntity<String> {
    return try {
      val subscriptionStatus = request.status ?: SubscriptionStatus.deactivated
      subscriptionService.updateSubscription(request.email, subscriptionStatus)
      ResponseEntity.ok("Subscription updated successfully: ${request.email} - $subscriptionStatus")
    } catch (ex: IllegalArgumentException) {
      ResponseEntity.status(400).body(ex.message)
    }

  }

  data class SubscribeRequest(val email: String, val status: SubscriptionStatus?)

}
