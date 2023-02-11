package com.example.urlshortener.service

import com.example.urlshortener.contract.Service
import com.example.urlshortener.dto.HitRateDto
import zio.schema.codec.{DecodeError, JsonCodec}
import zio.schema.{DeriveSchema, Schema}

trait HitRateService extends Service[HitRateService.Body, HitRateDto]

object HitRateService {

  case class Body(url: String)

  val schema: Schema[Body] = DeriveSchema.gen[Body]

  implicit def fromJson(jsonString: String): Either[DecodeError, Body] =
    JsonCodec.JsonDecoder.decode[Body](schema, jsonString)

}
