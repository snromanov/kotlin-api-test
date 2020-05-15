import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage

plugins {
    `common-library`
    id("com.bmuschko.docker-remote-api") version "6.4.0"
    id("io.qameta.allure") version "2.8.1"
}

group = "com.integration.testing"
version = "0.0.1"

tasks.create("buildMyAppImage", DockerBuildImage::class) {
    inputDir.set(file(projectDir))
    images.add("integra:latest")
}

apply(plugin = "io.qameta.allure")

allure {
    version = "2.13.2"
    aspectjweaver = true
    autoconfigure = true
    clean = true
    reportDir = file("$projectDir/build/allure-report")
    resultsDir = file("$projectDir/build/allure-results")
}

tasks {
    withType<Test>{
        if (System.getenv("CI").isNullOrBlank()) {
            environment("API_LOGIN", "admin")
            environment("API_PASSWORD", "password")
            environment("ENV_ID", "qa")
        }
    }
}