val selenideVersion: String by project
val slfjLoggerBindingVersion: String by project

dependencies {
  implementation(project(":core"))
  implementation("com.codeborne:selenide:$selenideVersion")
  //used by Selenide logger
  implementation("org.slf4j:slf4j-log4j12:$slfjLoggerBindingVersion")
}