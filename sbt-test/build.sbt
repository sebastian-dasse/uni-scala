lazy val root = (project in file(".")).
  settings(
    name := "HelloSbt",
    version := "1.0",
    scalaVersion := "2.11.4",
    scalacOptions ++= Seq("-encoding", "UTF-8"),
    libraryDependencies ++= Seq(
       //vendor % artifact % version % scope
       "junit" % "junit" % "4.8.1" % "test",
       //vendor %% scalaVersionDependentArtifact % ownVersion % scope
       "org.scalatest" %% "scalatest" % "2.2.1" % "test"
    ),
    EclipseKeys.withSource := true
  )