package com.example.urlshortener.contract

import com.redis.RedisClientPool

trait RedisModule

object RedisModule {

  private val init: RedisClientPool =
    new RedisClientPool(
      "localhost",
      6379,
      database = 1,
      timeout = 20000
    )

  val redisClient: RedisClientPool = init

}
