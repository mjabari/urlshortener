import sbt._

object Dependencies {
  val ZioVersion       = "2.0.0"
  val ZHTTPVersion     = "2.0.0-RC11"
  val ZioSchemaVersion = "0.3.1"

  val `zio-http`      = "io.d11" %% "zhttp" % ZHTTPVersion
  val `zio-http-test` = "io.d11" %% "zhttp" % ZHTTPVersion % Test

  val `zio-test`     = "dev.zio" %% "zio-test"     % ZioVersion % Test
  val `zio-test-sbt` = "dev.zio" %% "zio-test-sbt" % ZioVersion % Test

//  val `zio-json`                                   = "dev.zio" %% "zio-json" % "0.3.0-RC10"

  val `zio-schema`                       = "dev.zio"        %% "zio-schema"            % ZioSchemaVersion
  val `zio-schema-derivation`            = "dev.zio"        %% "zio-schema-derivation" % ZioSchemaVersion
  val `zio-schema-json`                  = "dev.zio"        %% "zio-schema-json"       % ZioSchemaVersion
  def scalaReflect(scalaVersion: String) = "org.scala-lang" % "scala-reflect"          % scalaVersion % "provided"
}
