package com.example.urlshortener.contract

import com.example.urlshortener.repository.callback.DataCallback
import com.example.urlshortener.repository.repository.DataRepository
import com.example.urlshortener.service.{ ActualUrlService, HitRateService, ShortenUrlService }
import com.example.urlshortener.usecase.{ ActualUrlUseCase, HitRateUseCase, ShortenUrlUseCase }

object ServiceModules {
  val dataCallback: DataCallback           = new DataRepository
  val shortenUrlService: ShortenUrlService = new ShortenUrlUseCase(dataCallback)
  val actualUrlService: ActualUrlService   = new ActualUrlUseCase(dataCallback)
  val hitRateService: HitRateService       = new HitRateUseCase(dataCallback)
}
