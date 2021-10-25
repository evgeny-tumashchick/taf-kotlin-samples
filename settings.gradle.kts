rootProject.name = "taf-kotlin-samples"

pluginManagement {
  repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
  }
}

include(
  ":core",
  ":ui_selenide"
)