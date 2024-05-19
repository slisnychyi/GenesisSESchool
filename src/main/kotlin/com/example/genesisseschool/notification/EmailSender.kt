package com.example.genesisseschool.notification

interface EmailSender {

  fun senEmail(email: String, body: String)

}
