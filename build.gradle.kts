import org.gradle.plugins.ide.idea.model.IdeaLanguageLevel
import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val projectJdkVersion: String = JavaVersion.VERSION_17.toString()
val projectJavaLanguageVersion: JavaLanguageVersion = JavaLanguageVersion.of(projectJdkVersion)

group = "org.tumasha"
version = "1.0-SNAPSHOT"

plugins {
  kotlin("jvm") version "2.0.20" apply false
  idea
}

idea {
  project {
    jdkName = projectJdkVersion
    languageLevel = IdeaLanguageLevel(projectJdkVersion)
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
    withType<KotlinCompile> { compilerOptions { jvmTarget.set(JVM_17) } }
  }

  tasks.withType<Test> {
    useJUnitPlatform()
    systemProperties.putAll(project.gradle.startParameter.systemPropertiesArgs)
  }

  tasks.register<Test>("internalTest") {
    include("org/tumasha/**")
    reports {
      junitXml.required.set(false)
      html.required.set(true)
    }
  }
  
}