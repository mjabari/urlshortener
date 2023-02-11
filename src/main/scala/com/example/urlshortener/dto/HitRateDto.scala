package com.example.urlshortener.dto

import zio.schema.codec.JsonCodec
import zio.schema.{DeriveSchema, Schema}

case class HitRateDto(hitRate: Long)

object HitRateDto {

  val schema: Schema[HitRateDto] = DeriveSchema.gen[HitRateDto]

  implicit def toJson(outDto: HitRateDto): String = new String(JsonCodec.JsonEncoder.encode(schema, outDto).toArray)

}
