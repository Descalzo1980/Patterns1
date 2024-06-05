plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "dev.stas"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.9.24")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.5.31")
    implementation("com.google.code.gson:gson:2.11.0")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}