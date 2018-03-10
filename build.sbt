import java.nio.file.Paths

import com.typesafe.sbt.web.PathMapping
import com.typesafe.sbt.web.pipeline.Pipeline

enablePlugins(ScalaJSPlugin)
enablePlugins(JSDependenciesPlugin)
enablePlugins(SbtWeb)
enablePlugins(WorkbenchPlugin)

name := "SwayDB.io"
version := "0.1.0"
scalaVersion := "2.12.3"

scalaJSUseMainModuleInitializer := true
mainClass in Compile := Some("swaydb.io.Main")


libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.3",
  "com.lihaoyi" %%% "scalatags" % "0.6.7",
  "org.scala-lang.modules" %% "scala-xml" % "1.1.0"
)

libraryDependencies ++=
  Seq(
    "com.github.japgolly.scalajs-react" %%% "core" % "1.1.1",
    "com.github.japgolly.scalajs-react" %%% "extra" % "1.1.1",
    "com.github.karasiq" %%% "scalajs-marked" % "1.0.2",
    "org.webjars.bower" % "highlightjs" % "9.12.0"
  )

jsDependencies ++=
  Seq(
    "org.webjars" % "jquery" % "2.1.4" / "jquery.js" minified "jquery.min.js",
    "org.webjars" % "bootstrap" % "3.3.7" / "bootstrap.js" minified "bootstrap.min.js" dependsOn "jquery.js",
    "org.webjars.bower" % "react" % "15.6.1" / "react-with-addons.js" minified "react-with-addons.min.js" commonJSName "React",
    "org.webjars.bower" % "react" % "15.6.1" / "react-dom.js" minified "react-dom.min.js" dependsOn "react-with-addons.js" commonJSName "ReactDOM",
    "org.webjars.bower" % "react" % "15.6.1" / "react-dom-server.js" minified "react-dom-server.min.js" dependsOn "react-dom.js" commonJSName "ReactDOMServer",
    "org.webjars" % "google-analytics-snippet" % "20161204" / "20161204/analytics-snippet.js"
  )

LessKeys.compress in Assets := true


val copyCSSPipeline = taskKey[Pipeline.Stage]("Copy CSS pipeline task")
copyCSSPipeline := identity
pipelineStages := Seq(copyCSSPipeline)

copyCSSPipeline := { mappings: Seq[PathMapping] =>
  println("Running Copy CSS pipeline task")
  //Hacky way to copy CSS files. Need to find a better solution.
  mappings foreach {
    case (file, fileId) if file.getPath.contains("target/web/less/main/stylesheets") && !fileId.contains("main.min.css.map") =>
      println(s"Copying css: $fileId")
      val paths = file.getPath.split("/")
      val indexOfTarget = paths.indexOf("target")
      val (beforeTarget, afterTarget) = paths.splitAt(indexOfTarget)
      val targetPath = Paths.get(beforeTarget.mkString("/") + "/docs/css/" + afterTarget.last)
      IO.copyFile(file, targetPath.toFile)

    case (file, fileId) if file.getPath.contains("glyphicons") =>
      println(s"Copying fonts: $fileId")
      val paths = file.getPath.split("/")
      val indexOfTarget = paths.indexOf("target")
      val (beforeTarget, afterTarget) = paths.splitAt(indexOfTarget)
      val targetPath = Paths.get(beforeTarget.mkString("/") + "/docs/fonts/" + afterTarget.last)
      IO.copyFile(file, targetPath.toFile)

    case _ =>
  }
  mappings
}

pipelineStages in Assets := Seq(copyCSSPipeline)

artifactPath in(Compile, fastOptJS) := baseDirectory.value / "docs" / "sjs" / "opt.js"
artifactPath in(Compile, fullOptJS) := baseDirectory.value / "docs" / "sjs" / "opt.js"
artifactPath in(Compile, packageMinifiedJSDependencies) := baseDirectory.value / "docs" / "sjs" / "libs.js"
artifactPath in(Compile, packageJSDependencies) := baseDirectory.value / "docs" / "sjs" / "libs.js"

emitSourceMaps in fullOptJS := false