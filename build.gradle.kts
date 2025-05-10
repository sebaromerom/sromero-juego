
    plugins {
        alias(libs.plugins.kotlin.jvm)
        alias(libs.plugins.ktor)
        id("org.jetbrains.kotlin.plugin.serialization") version "2.1.20"
    }

    group = "com.example"
    version = "0.0.1"

    application {
        mainClass = "io.ktor.server.netty.EngineMain"
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation(libs.ktor.server.core)
        implementation(libs.ktor.server.netty)
        implementation(libs.logback.classic)
        implementation(libs.ktor.server.core)
        implementation(libs.ktor.server.config.yaml)
        implementation("io.ktor:ktor-server-content-negotiation:2.3.10")
        implementation("io.ktor:ktor-server-core:2.3.10")
        testImplementation(libs.ktor.server.test.host)
        testImplementation(libs.kotlin.test.junit)
        implementation("io.ktor:ktor-serialization-kotlinx-json")
    }
