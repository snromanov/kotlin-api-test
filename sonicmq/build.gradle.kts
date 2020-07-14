import Build_gradle.Version.brokerVersion
import Build_gradle.Version.sonicVersion
import Build_gradle.Version.springVersion

plugins {
    `common-library`
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    id("org.springframework.boot") version "2.3.1.RELEASE"
}

group = "com.integration.testing"
version = "0.0.1"

repositories {
    maven {
        url = uri("https://mvnrepo.cantara.no/content/repositories/releases/")
    }
}

object Version {
    const val sonicVersion = "7.5"
    const val brokerVersion = "5.16.0"
    const val springVersion = "5.2.7.RELEASE"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-activemq:2.3.1.RELEASE")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.apache.activemq:activemq-broker:$brokerVersion")
    implementation("progress.message:sonic_Client:$sonicVersion")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation ("javax.validation:validation-api:2.0.1.Final")
    implementation ("org.apache.cxf:cxf-rt-frontend-jaxws:3.3.7")
    implementation ("org.springframework:spring-oxm:$springVersion")
    implementation ("javax.jms:javax.jms-api:2.0.1")
    implementation ("org.springframework:spring-jms:$springVersion")
    implementation ("progress.message:sonic_Client:$sonicVersion")
    testImplementation ("org.apache.activemq:activemq-core:5.7.0")

}