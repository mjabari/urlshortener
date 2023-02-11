package com.example.urlshortener.repository.callback

import zio.Task

trait DataCallback {

  def set(key: String, data: Map[String, Any], ttl: Option[Int] = None): Task[Unit]

  def get(key: String, field: String): Task[String]

  def increment(key: String, field: String, value: Long): Task[Unit]

  def size: Task[Option[Long]]

}
