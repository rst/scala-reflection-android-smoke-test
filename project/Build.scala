import sbt._

import Keys._
import AndroidKeys._

object General {
  val settings = Defaults.defaultSettings ++ Seq (
    name := "Scala Reflection Smoke Test",
    version := "0.1",
    versionCode := 0,
    scalaVersion := "2.10.0-RC1",
    platformName in Android := "android-10"
  )

  lazy val fullAndroidSettings =
    General.settings ++
    AndroidProject.androidSettings ++
    TypedResources.settings ++
    AndroidManifestGenerator.settings ++
    AndroidMarketPublish.settings ++ 
    Seq (
      keyalias in Android := "change-me",
      proguardOption in Android := """
        -keep class scala.collection.SeqLike { public protected *; }
        -keepclassmembers class * {
           ** item;
           ** bytes();
        }
        -keep class scala.AnyVal
        -keep class scala.Array
        -keep class scala.Boolean
        -keep class scala.Byte
        -keep class scala.Char
        -keep class scala.Double
        -keep class scala.Float
        -keep class scala.Int
        -keep class scala.Long
        -keep class scala.Short
        -keep class scala.Unit
        -keep class scala.reflect.ScalaSignature
        -keep class scala.reflect.api.Mirror
      """,
      libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.10.0-RC1"
    )
}

object AndroidBuild extends Build {
  lazy val main = Project (
    "smoketest",
    file("."),
    settings = General.fullAndroidSettings
  )
}
