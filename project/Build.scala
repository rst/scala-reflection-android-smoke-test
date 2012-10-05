import sbt._

import Keys._
import AndroidKeys._

object General {
  val settings = Defaults.defaultSettings ++ Seq (
    name := "Scala Reflection Smoke Test",
    version := "0.1",
    versionCode := 0,
    scalaVersion := "2.10.0-M7",
    platformName in Android := "android-10"
  )

  val proguardSettings = Seq (
    useProguard in Android := true
  )

  lazy val fullAndroidSettings =
    General.settings ++
    AndroidProject.androidSettings ++
    TypedResources.settings ++
    proguardSettings ++
    AndroidManifestGenerator.settings ++
    AndroidMarketPublish.settings ++ Seq (
      keyalias in Android := "change-me"
    )
}

object AndroidBuild extends Build {
  lazy val main = Project (
    "smoketest",
    file("."),
    settings = General.fullAndroidSettings
  )

  lazy val tests = Project (
    "tests",
    file("tests"),
    settings = General.settings ++
               AndroidTest.androidSettings ++
               General.proguardSettings ++ Seq (
      name := "Scala Reflection Smoke TestTests"
    )
  ) dependsOn main
}
