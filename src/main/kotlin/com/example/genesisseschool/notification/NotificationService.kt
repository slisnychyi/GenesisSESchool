package com.example.genesisseschool.notification

import org.springframework.stereotype.Service

@Service
class NotificationService(val emailSender: EmailSender) {

  fun sendEmailNotification(email: String, body: String) {
    emailSender.senEmail(email, body)
  }

}
