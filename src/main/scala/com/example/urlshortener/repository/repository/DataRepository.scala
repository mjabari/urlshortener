package com.example.urlshortener.repository.repository

import com.example.urlshortener.contract.RedisModule
import com.example.urlshortener.contract.RedisModule.redisClient
import com.example.urlshortener.repository.callback.DataCallback
import zio.{Task, ZIO}

class DataRepository extends DataCallback with RedisModule {
  override def set(key: String, data: Map[String, Any], ttl: Option[Int]): Task[Unit] = {
    val result = redisClient.withClient { client =>
      val set    = client.hmset(key, data)
      val expire = ttl.exists(client.expire(key, _))
      set && (ttl.isEmpty || expire)
    }
    ZIO.cond(result, (), new RuntimeException("Saving Data in the database was not successful"))
  }

  override def get(key: String, field: String): Task[String] =
    redisClient.withClient { client =>
      client.hget(key, field)
    }.map(ZIO.succeed(_)).getOrElse(ZIO.fail(new RuntimeException("Fetching data from Database failed")))

  override def increment(key: String, field: String, value: Long): Task[Unit] = ZIO.succeed {
    redisClient.withClient { client =>
      client.hincrby(key, field, value)
    }
  }

  override def size: Task[Option[Long]] = {
    val result = redisClient.withClient { client =>
      client.dbsize
    }
    ZIO.cond(result.nonEmpty, result, new RuntimeException("Fetching data from Database failed"))
  }
}
