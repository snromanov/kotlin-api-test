import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.detekt
import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    kotlin("jvm")
    id("io.gitlab.arturbosch.detekt")
}

repositories {
    mavenCentral()
    jcenter()
}

apply {
    from("${rootProject.rootDir}/properties.gradle.kts")
}

val ktlint: Configuration = configurations.create("ktlint")
val kotlinVersion: String by extra
val ktlintVersion: String by extra
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

dependencies {
    ktlint("com.github.shyiko:ktlint:$ktlintVersion")
    api(kotlin("stdlib-jdk8", kotlinVersion))
    api(kotlin("stdlib", kotlinVersion))
    api(kotlin("reflect", kotlinVersion))
    api("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("com.github.kittinunf.fuel:fuel:$fuelVersion")
    implementation("com.github.kittinunf.fuel:fuel-jackson:$fuelVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("org.cfg4j:cfg4j-core:$cfg4jVersion")
    testImplementation("io.qameta.allure:allure-junit5:$allureVersion")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:$assertkVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testImplementation("org.apache.commons:commons-lang3:$apacheVersion")
    testImplementation("com.natpryce:hamkrest:$hamcrestVersion")
    testImplementation("io.qameta.allure:allure-attachments:$allureVersion")
    testImplementation("io.qameta.allure:allure-generator:$allureVersion")
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

    val ktlint by creating(JavaExec::class) {
        group = "verification"
        description = "Check Kotlin code style."
        classpath = configurations["ktlint"]
        main = "com.github.shyiko.ktlint.Main"
        args = listOf("src/**/*.kt")
    }

    "check" {
        dependsOn(ktlint)
    }

    create("ktlintFormat", JavaExec::class) {
        group = "formatting"
        description = "Fix Kotlin code style deviations."
        classpath = configurations["ktlint"]
        main = "com.github.shyiko.ktlint.Main"
        args = listOf("-F", "src/**/*.kt")
    }

    withType<Test> {
        useJUnitPlatform()
        systemProperty("allure.results.directory", "$projectDir/build/allure-results")
        ignoreFailures = true
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
    toolVersion = detektVersion
    input = files("src/main/kotlin", "src/test/kotlin")
}