plugins {
    kotlin("jvm") version "2.0.20"
}

group = "com.nospawnn"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    val flatlaf = "3.5.1"
    implementation("com.formdev:flatlaf:${flatlaf}")
    implementation("com.formdev:flatlaf:${flatlaf}.:linux-x86_64@so")
    implementation("com.formdev:flatlaf:${flatlaf}:windows-x86_64@dll")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}