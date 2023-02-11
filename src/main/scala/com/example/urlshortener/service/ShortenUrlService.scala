package com.example.urlshortener.service

import com.example.urlshortener.contract.Service
import com.example.urlshortener.dto.UrlDto
import zio.schema.codec.{DecodeError, JsonCodec}
import zio.schema.{DeriveSchema, Schema}

trait ShortenUrlService extends Service[ShortenUrlService.Body, UrlDto]

object ShortenUrlService {

  case class Body(url: String)

  val schema: Schema[Body] = DeriveSchema.gen

  implicit def fromJson(jsonString: String): Either[DecodeError, Body] =
    JsonCodec.JsonDecoder.decode[Body](schema, jsonString)

}
