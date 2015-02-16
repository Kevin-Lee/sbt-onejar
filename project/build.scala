import sbt._
import Keys._

object build extends Build {

//  val sbtXjc = Project(
//    id = "sbt-onejar",
//    base = file("."),
//    settings = Defaults.defaultSettings ++ ScriptedPlugin.scriptedSettings ++ Seq[Project.Setting[_]](
//      organization := "org.scala-sbt.plugins",
//      version := "0.9-SNAPSHOT",
//      sbtPlugin := true,
//      scalacOptions in Compile ++= Seq("-deprecation"),
//      publishTo := Some(Resolver.url("sbt-plugin-releases", new URL("http://repo.scala-sbt.org/scalasbt/sbt-plugin-releases/"))(Resolver.ivyStylePatterns)),
//      publishMavenStyle := false
//    )
//  )

  val nexus = "http://nexus.lckymn.com/"

  val sbtPublicDeploy = Project(
    id = "sbt-onejar-ex",
    base = file("."),
    settings = Defaults.defaultSettings ++ ScriptedPlugin.scriptedSettings ++ Seq[Project.Setting[_]](
      organization := "org.scala-sbt.plugins",
      version := "0.8.1",
      sbtPlugin := true,
      scalacOptions in Compile ++= Seq("-deprecation"),
      publishTo <<= version { (v: String) =>
        if (v.trim.endsWith("SNAPSHOT"))
          Some("snapshots" at nexus + "content/repositories/kevin-public-snapshots")
        else
          Some("releases"  at nexus + "content/repositories/kevin-public-releases")
      },
      publishMavenStyle := true,
      credentials += Credentials(Path.userHome / ".ivy2" / ".nexus-deploy-credentials"),
      pomIncludeRepository := { _ => false },
      pomExtra := (
        <url>https://github.com/Kevin-Lee/sbt-onejar</url>
          <licenses>
            <license>
              <name>The MIT License</name>
              <url>https://raw.githubusercontent.com/Kevin-Lee/sbt-onejar/master/LICENSE</url>
            </license>
          </licenses>
          <scm>
            <url>git@github.com:Kevin-Lee/sbt-onejar.git</url>
            <connection>scm:git:git@github.com:Kevin-Lee/sbt-onejar.git</connection>
          </scm>)
    )
  )
}