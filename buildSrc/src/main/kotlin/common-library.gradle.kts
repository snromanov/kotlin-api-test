import io.gitlab.arturbosch.detekt.Detekt
import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    kotlin("jvm")
    id("io.gitlab.arturbosch.detekt")
}

repositories {
    google()
    mavenCentral()
    jcenter()
}

apply {
    from("${rootProject.rootDir}/properties.gradle.kts")
}
val kotlinVersion: String by extra
val fuelVersion: String by extra
val jacksonVersion: String by extra
val junitVersion: String by extra
val logbackVersion: String by extra
val allureVersion: String by extra
val assertkVersion: String by extra
val hamcrestVersion: String by extra
val detektVersion: String by extra
val apacheVersion: String by extra
val cfg4jVersion: String by extra
val konfigVersion: String by extra
val aspectjVersion: String by extra
val awaitVersion: String by extra
val kotestVersion: String by extra

dependencies {
    api(kotlin("stdlib-jdk8", kotlinVersion))
    api(kotlin("stdlib", kotlinVersion))
    api(kotlin("reflect", kotlinVersion))
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:$detektVersion")
    api("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("com.github.kittinunf.fuel:fuel:$fuelVersion")
    implementation("com.github.kittinunf.fuel:fuel-jackson:$fuelVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("org.cfg4j:cfg4j-core:$cfg4jVersion")
    implementation("com.natpryce:konfig:$konfigVersion")
    implementation("org.aspectj:aspectjweaver:$aspectjVersion")
    testImplementation("io.qameta.allure:allure-junit5:$allureVersion")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:$assertkVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testImplementation("org.apache.commons:commons-lang3:$apacheVersion")
    testImplementation("com.natpryce:hamkrest:$hamcrestVersion")
    implementation("io.qameta.allure:allure-attachments:$allureVersion")
    implementation("io.qameta.allure:allure-generator:$allureVersion")
    implementation("org.awaitility:awaitility:$awaitVersion")
    implementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")
    implementation ("org.jvnet.jaxb2_commons:jaxb2-basics-runtime:1.11.1")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_11.toString()
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-java-parameters",
                "-Xjsr305=strict",
                "-progressive"
            )
        }
    }

    withType<Detekt> {
        this.jvmTarget = JavaVersion.VERSION_11.toString()
    }

    withType<Test> {
        useJUnitPlatform()
        systemProperty("allure.results.directory", "$projectDir/build/allure-results")
        testLogging {
            events = setOf(
                TestLogEvent.FAILED,
                TestLogEvent.PASSED,
                TestLogEvent.SKIPPED,
                TestLogEvent.STANDARD_OUT
            )
            exceptionFormat = FULL
            showExceptions = true
            showCauses = true
            showStackTraces = true
        }
    }
}

detekt {
    autoCorrect = true
    toolVersion = detektVersion
    input = files("src/main/kotlin", "src/test/kotlin")
}