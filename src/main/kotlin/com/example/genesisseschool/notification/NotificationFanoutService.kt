package com.example.genesisseschool.notification

import com.example.genesisseschool.exchngaeRate.ExchangeRateService
import com.example.genesisseschool.subscription.SubscriptionService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class NotificationFanoutService(
  val exchangeRateService: ExchangeRateService,
  val subscriptionService: SubscriptionService,
  val notificationService: NotificationService
) {

  @Scheduled(cron = "0 0 0 * * ?") // Runs at midnight every day
  fun fanoutSubscriptions() {
    println("Start sending echange rates for subscribed users")
    val exchangeRate = exchangeRateService.getExchangeRate()
    val message = "exchange rate from USD -> UAH (sell=${exchangeRate.sellRate}, " +
      "buy=${exchangeRate.buyRate}, date =${exchangeRate.date}"
    subscriptionService.getSubscribedUsers().forEach {
      notificationService.sendEmailNotification(it.email, message)
    }
  }


}
