package com.example.urlshortener.contract

import zio._

trait Service[In, Out] {
  def call(body: In): Task[Out]

}
