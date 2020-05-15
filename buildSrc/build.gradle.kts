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
    compile(kotlin("stdlib-jdk8", version = kotlinVersion))
    compile(kotlin("gradle-plugin", version = kotlinVersion))
    compile(kotlin("reflect", version = kotlinVersion))
    compile("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$detektVersion")
}
