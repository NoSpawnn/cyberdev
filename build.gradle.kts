plugins {
    kotlin("jvm") version "2.0.20"
    id("io.github.file5.guidesigner") version "1.0.2"
}

group = "com.nospawnn"
version = "1.0-SNAPSHOT"

repositories {
    maven {
        url = uri("https://cache-redirector.jetbrains.com/intellij-dependencies")
    }
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    runtimeOnly("com.jetbrains.intellij.java:java-gui-forms-rt:242.22855.106")

    implementation(kotlin("reflect"))

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