package com.example.urlshortener.usecase

import com.example.urlshortener.config._
import com.example.urlshortener.dto.HitRateDto
import com.example.urlshortener.repository.callback.DataCallback
import com.example.urlshortener.service.HitRateService
import zio.Task

import java.net.URL

class HitRateUseCase(dataCallback: DataCallback) extends HitRateService {

  override def call(body: HitRateService.Body): Task[HitRateDto] = {
    val id = new URL(body.url).getPath.replaceAll("/", "")
    dataCallback.get(id, counterFieldName).map(x => HitRateDto(x.toLong))
  }

}
