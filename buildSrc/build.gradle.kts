plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    jcenter()
}


apply {
    from("${rootDir.parentFile}/properties.gradle.kts")
}

val kotlinVersion: String by extra
val detektVersion: String by extra

dependencies {
    implementation(kotlin("stdlib-jdk8", version = kotlinVersion))
    implementation(kotlin("gradle-plugin", version = kotlinVersion))
    implementation(kotlin("reflect", version = kotlinVersion))
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$detektVersion")
}
