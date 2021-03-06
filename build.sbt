name := "Javelin"

version := "1.4"

scalaVersion := "2.10.3"

organization := "ivan"

resolvers += "litef nightlies" at "https://raw.githubusercontent.com/ivan-cukic/litef-maven-repository/master/snapshots/"

// mainClass in (Compile, packageBin) := Some("org.mi.litef.Main")

// mainClass in (Compile, run) := Some("org.mi.litef.Main")

// packageOptions in (Compile, packageBin) +=
//     Package.ManifestAttributes( java.util.jar.Attributes.Name.CLASS_PATH ->
//         "lib/scala-library.jar lib/postgresql-9.0-801.jdbc4.jar" )

libraryDependencies ++= Seq (
    "org.apache.jena"    % "jena-arq" % "2.11.1" exclude("org.slf4j", "slf4j-log4j12")
  , "ivan" %% "javelin-ontologies" % "1.3"
  , "ivan" %% "scala-utils" % "1.2"
)

mainClass := Some("javelin.Main")

javacOptions ++= Seq("-Xlint:unchecked")

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

publishTo := Some(Resolver.file("file", new File("../maven-repository/snapshots/")))
