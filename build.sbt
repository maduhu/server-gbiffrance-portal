name := "server-gbiffrance-portal"

version := "1.0-SNAPSHOT"

resolvers += Resolver.url("play-plugin-snapshots", new URL("http://repo.scala-sbt.org/scalasbt/sbt-plugin-snapshots/"))(Resolver.ivyStylePatterns)

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "com.clever-age" % "play2-elasticsearch" % "0.8-SNAPSHOT" exclude("org.elasticsearch","elasticsearch"),
  "org.elasticsearch" % "elasticsearch" % "0.90.7"
)     

play.Project.playJavaSettings
