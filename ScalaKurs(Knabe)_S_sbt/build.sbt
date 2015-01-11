lazy val root = (project in file(".")).
  settings(
    name := "ScalaKurs(Knabe)_S_sbt",
    version := "1.0",
    scalaVersion := "2.11.4",
    scalacOptions ++= Seq("-encoding", "UTF-8"),
    libraryDependencies ++= Seq(
      //vendor % artifact % version % scope
      "junit" % "junit" % "4.8.1" % "test",
      //vendor %% scalaVersionDependentArtifact % ownVersion % scope
      "org.scalatest" %% "scalatest" % "2.2.1" % "test",
      // Akka
      "com.typesafe.akka" %% "akka-actor" % "2.3.5"
      //"com.typesafe.akka" %% "akka-remote" % "2.3.5",
      //"com.typesafe.akka" %% "akka-testkit" % "2.3.5"
    ),
    EclipseKeys.withSource := true
  )