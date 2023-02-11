package com.example.urlshortener

object config {
  val host              = s"localhost:${Application.port}"
  val baseAlphabet      = "abcdefghijklmnopqrstuvwxyz"
  val maxLengthTemplate = 999999L
  val urlFieldName      = "url"
  val counterFieldName  = "counter"
  val hashLength        = 6
}
