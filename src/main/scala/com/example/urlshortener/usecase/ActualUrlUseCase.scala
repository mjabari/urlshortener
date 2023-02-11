package com.example.urlshortener.usecase

import com.example.urlshortener.config._
import com.example.urlshortener.dto.UrlDto
import com.example.urlshortener.repository.callback.DataCallback
import com.example.urlshortener.service.ActualUrlService
import zio.{Task, ZIO}

class ActualUrlUseCase(dataCallback: DataCallback) extends ActualUrlService {
  override def call(body: ActualUrlService.Body): Task[UrlDto] =
    for {
      result <- dataCallback.get(body.id, urlFieldName)
      _ <- if (result.nonEmpty) dataCallback.increment(body.id, counterFieldName, 1) else ZIO.unit
    } yield UrlDto(result)
}
