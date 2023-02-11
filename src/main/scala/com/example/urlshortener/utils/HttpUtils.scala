package com.example.urlshortener.utils

import zhttp.http.{HttpError, Request, Response, Status}
import zio.ZIO

object HttpUtils {

  def handle[R, I, O](request: Request, successStatus: Status)(
    f: I => ZIO[R, Throwable, O]
  )(implicit fromJson: String => Either[Throwable, I], toJson: O => String): ZIO[R, Throwable, Response] =
    request.body.asString.map(fromJson).flatMap {
      case Left(_)  => ZIO.succeed(Response.fromHttpError(HttpError.BadRequest()))
      case Right(t) => f(t).map(r => Response.json(toJson(r)).setStatus(successStatus))
    }

  def handle[R, O](
    successStatus: Status
  )(f: => ZIO[R, Throwable, O])(implicit toJson: O => String): ZIO[R, Throwable, Response] =
    f.map(r => Response.json(toJson(r)).setStatus(successStatus))
}
