package com.example.genesisseschool.notification

import org.springframework.stereotype.Service

@Service
class MockEmailSender : EmailSender {

  override fun senEmail(email: String, body: String) {
    println("Sending email $email with $body")
  }

}
