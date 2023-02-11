package com.example.urlshortener.service

import com.example.urlshortener.contract.Service
import com.example.urlshortener.dto.UrlDto
import zio.schema.codec.{DecodeError, JsonCodec}
import zio.schema.{DeriveSchema, Schema}

trait ActualUrlService extends Service[ActualUrlService.Body, UrlDto]

object ActualUrlService {

  case class Body(id: String)

  val schema: Schema[Body] = DeriveSchema.gen

  implicit def fromJson(jsonString: String): Either[DecodeError, Body] =
    JsonCodec.JsonDecoder.decode[Body](schema, jsonString)

}
