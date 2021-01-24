plugins {
    kotlin("jvm") version "1.3.71"
    id("java-gradle-plugin")
    id("maven-publish")
    id("com.gradle.plugin-publish") version "0.12.0"
}

group = "dev.quiescence"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    testImplementation(group = "org.assertj", name = "assertj-core", version = "3.17.2")
    testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-api", version = "5.7.0")
    testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-params", version = "5.7.0")
    testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-engine", version = "5.7.0")
}

tasks {
    test {
        useJUnitPlatform()
    }
}

gradlePlugin {
    plugins {
        create("versioning") {
            id = "dev.quiescence.plugins.versioning"
            displayName = "Semantic Versioning Plugin"
            description = "Semantic Versioning plugin to increment versions using gradle tasks"
            implementationClass = "dev.quiescence.plugins.versioning.VersioningPlugin"
        }
    }
}
