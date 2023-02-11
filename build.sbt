ThisBuild / organization := "com.example"
ThisBuild / version := "1.0.0"
val ZioVersion       = "2.0.0"
val ZHTTPVersion     = "2.0.0-RC11"
val ZioSchemaVersion = "0.3.1"

lazy val root = (project in file("."))
  .settings(
    name := "urlshortener",
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework"),
    libraryDependencies ++= Seq(
      "dev.zio"       %% "zio-schema"            % ZioSchemaVersion,
      "dev.zio"       %% "zio-schema-derivation" % ZioSchemaVersion,
      "dev.zio"       %% "zio-schema-json"       % ZioSchemaVersion,
      "dev.zio"       %% "zio-test"              % ZioVersion % Test,
      "dev.zio"       %% "zio-test-sbt"          % ZioVersion % Test,
      "io.d11"        %% "zhttp"                 % ZHTTPVersion,
      "io.d11"        %% "zhttp"                 % ZHTTPVersion % Test,
      "net.debasishg" %% "redisclient"           % "3.41",
      "org.hashids"   % "hashids"                % "1.0.3"
      //  "org.scala-lang" % "scala-reflect" % scalaVersion.toString() % "provided"),
    )
  )
