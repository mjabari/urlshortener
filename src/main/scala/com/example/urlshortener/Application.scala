package com.example.urlshortener

import com.example.urlshortener.contract.ServiceModules.{actualUrlService, hitRateService, shortenUrlService}
import com.example.urlshortener.service.{ActualUrlService, HitRateService}
import com.example.urlshortener.utils.HttpUtils._
import zhttp.http._
import zhttp.service.Server
import zio._

object Application extends ZIOAppDefault {

  val app: HttpApp[Any, Throwable] = Http.collectZIO[Request] {
    case request @ Method.POST -> !! / "url-shortener" / "hit-rates" => handle(request, Status.Ok)(hitRateService.call)
    case request @ Method.POST -> !! / "url-shortener" => handle(request, Status.Created)(shortenUrlService.call)
    case Method.GET -> !! / id => handle(Status.Ok)(actualUrlService.call(ActualUrlService.Body(id)))
  }

  val port: Int = 8090

  override def run: Task[Unit] = {
    println("Starting server on 8090 port...")
    Server
      .start(port, app)
      .provide()
  }
}
