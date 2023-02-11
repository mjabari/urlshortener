package com.example.urlshortener.usecase

import com.example.urlshortener.config._
import com.example.urlshortener.dto.UrlDto
import com.example.urlshortener.repository.callback.DataCallback
import com.example.urlshortener.service.ShortenUrlService
import org.hashids.Hashids
import zio.Task

class ShortenUrlUseCase(shortenUrlCallback: DataCallback) extends ShortenUrlService {

  override def call(body: ShortenUrlService.Body): Task[UrlDto] =
    for {
      maxId  <- shortenUrlCallback.size
      hashId = new Hashids(body.url, hashLength, baseAlphabet).encode((maxId.getOrElse(0L) + 1L) % maxLengthTemplate)
      _      <- shortenUrlCallback.set(hashId, Map(urlFieldName -> body.url, counterFieldName -> 0))
    } yield UrlDto(s"http://${host}/$hashId")

}
