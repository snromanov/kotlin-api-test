import Build_gradle.Versions.javaxwsVersion
import Build_gradle.Versions.jaxb2AnnotateVersion
import Build_gradle.Versions.jaxb2CommonsVersion
import Build_gradle.Versions.jaxbCoreVersion
import Build_gradle.Versions.jaxbImplVersion
import Build_gradle.Versions.jaxbRuntimeVerison
import Build_gradle.Versions.jsr181Version
import no.nils.wsdl2java.Wsdl2JavaTask

plugins {
    `common-library`
    id("no.nils.wsdl2java") version "0.8"
}

group = "com.integration.testing"
version = "0.0.1"

object Versions {
    const val jaxb2CommonsVersion = "1.11.1"
    const val jaxb2AnnotateVersion = "1.0.4"
    const val javaxwsVersion = "2.3.1"
    const val jaxbRuntimeVerison = "2.3.0.1"
    const val jsr181Version = "1.0-MR1"
    const val jaxbImplVersion = "2.3.1"
    const val jaxbCoreVersion = "2.3.0.1"
}

dependencies() {
    // enable extension support for wsdl2java
    wsdl2java("org.jvnet.jaxb2_commons:jaxb2-basics:$jaxb2CommonsVersion")
    wsdl2java("org.jvnet.jaxb2_commons:jaxb2-basics-ant:$jaxb2CommonsVersion")
    wsdl2java("org.jvnet.jaxb2_commons:jaxb2-basics-annotate:${jaxb2AnnotateVersion}")
    wsdl2java("com.sun.xml.bind:jaxb-impl:$jaxbImplVersion")
    wsdl2java("com.sun.xml.bind:jaxb-core:$jaxbCoreVersion")
    wsdl2java("com.sun.xml.bind:jaxb-xjc:$jaxbCoreVersion")
    wsdl2java("org.glassfish.jaxb:jaxb-runtime:$jaxbRuntimeVerison")
    wsdl2java("javax.xml.ws:jaxws-api:$javaxwsVersion")
    wsdl2java("javax.jws:jsr181-api:$jsr181Version")
    implementation ("org.jvnet.jaxb2_commons:jaxb2-basics-runtime:$jaxb2CommonsVersion")
}

tasks {
    withType<Wsdl2JavaTask> {
        wsdlsToGenerate = arrayListOf(
            arrayListOf(
                "-xjc-Xequals",
                "-xjc-XhashCode",
                "-xjc-XtoString",
                "$projectDir/src/main/resources/import.wsdl"
            )
        )
    }
}
val wsdl2java: Wsdl2JavaTask by tasks
sourceSets {
    getByName("main") {
        java.setSrcDirs(java.srcDirs + wsdl2java.generatedWsdlDir)
    }
}
afterEvaluate {
    tasks.forEach {
        if (it.name == "kaptKotlin" || it.name == "compileKotlin")
            it.dependsOn(wsdl2java)
    }
}