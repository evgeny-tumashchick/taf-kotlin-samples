import org.gradle.plugins.ide.idea.model.IdeaLanguageLevel
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "org.tumasha"
version = "1.0-SNAPSHOT"

plugins {
  kotlin("jvm") version "1.5.31" apply false
  idea
}

idea {
  project {
    jdkName = "1.8"
    languageLevel = IdeaLanguageLevel("1.8")
  }
  module.name = "taf-kotlin-samples"
}

allprojects {
  repositories {
    mavenLocal()
    mavenCentral()
  }
}

subprojects {
  val junitJupiterVersion: String by project
  val kotlinVersion: String by project

  apply(plugin = "org.jetbrains.kotlin.jvm")

  dependencies {
    val implementation by configurations
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    implementation("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
    implementation("org.junit.jupiter:junit-jupiter-params:$junitJupiterVersion")
  }

  tasks {
    withType<KotlinCompile> {
      kotlinOptions.jvmTarget = "1.8"
    }
  }

  tasks.withType<Test> {
    useJUnitPlatform()
    systemProperties.putAll(project.gradle.startParameter.systemPropertiesArgs)
  }

  tasks.register<Test>("internalTest") {
    include("org/tumasha/**")
    reports {
      junitXml.isEnabled = false
      html.isEnabled = true
    }
  }
}