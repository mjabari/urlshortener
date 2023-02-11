package com.example.urlshortener.dto

import zio.schema.codec.JsonCodec
import zio.schema.{DeriveSchema, Schema}

case class UrlDto(url: String)

object UrlDto {

  val schema: Schema[UrlDto] = DeriveSchema.gen

  implicit def toJson(outDto: UrlDto): String = new String(JsonCodec.JsonEncoder.encode(schema, outDto).toArray)

}
