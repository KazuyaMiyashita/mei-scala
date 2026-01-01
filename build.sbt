val scala3Version = "3.7.4"

lazy val commonSettings = Seq(
  scalaVersion := scala3Version,
  scalacOptions ++= Seq(
    "-deprecation",
    "-feature",
    "-Wvalue-discard",
    "-Wunused:all",
  ),
  libraryDependencies += "org.scalatest"          %% "scalatest-funsuite" % "3.2.19" % Test,
  libraryDependencies += "org.scala-lang.modules" %% "scala-xml"          % "2.4.0",
  Compile / console / scalacOptions ~= { _.filterNot(_ == "-Wunused:all") },
  scalafmtOnCompile := true,
)

lazy val root = project
  .in(file("."))
  .settings(
    name    := "mei-scala",
    version := "0.1.0-SNAPSHOT",
    commonSettings,
  )

lazy val generator = project
  .in(file("generator"))
  .settings(
    name         := "mei-generator",
    scalaVersion := scala3Version,
    commonSettings,
  )

lazy val meiBasic = project
  .in(file("mei-basic"))
  .settings(
    name := "mei-basic",
    commonSettings,
    scalafmtOnCompile := false,
  )

lazy val meiCmn = project
  .in(file("mei-cmn"))
  .settings(
    name := "mei-cmn",
    commonSettings,
    scalafmtOnCompile := false,
  )

lazy val meiAll = project
  .in(file("mei-all"))
  .settings(
    name := "mei-all",
    commonSettings,
    scalafmtOnCompile := false,
  )
