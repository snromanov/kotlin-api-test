import no.nils.wsdl2java.Wsdl2JavaTask

plugins {
    `common-library`
    id("no.nils.wsdl2java") version "0.8"
}

group = "com.integration.testing"
version = "0.0.1"


dependencies() {
    // enable extension support for wsdl2java
    wsdl2java("org.jvnet.jaxb2_commons:jaxb2-basics:1.11.1")
    wsdl2java("org.jvnet.jaxb2_commons:jaxb2-basics-ant:1.11.1")
    wsdl2java("org.jvnet.jaxb2_commons:jaxb2-basics-annotate:1.0.4")
    wsdl2java("com.sun.xml.bind:jaxb-impl:2.3.1")
    wsdl2java("com.sun.xml.bind:jaxb-core:2.3.0.1")
    wsdl2java("com.sun.xml.bind:jaxb-xjc:2.3.0.1")
    wsdl2java("org.glassfish.jaxb:jaxb-runtime:2.3.0.1")
    wsdl2java("javax.xml.ws:jaxws-api:2.3.1")
    wsdl2java("javax.jws:jsr181-api:1.0-MR1")
}

tasks {
    withType<Wsdl2JavaTask> {
        wsdlsToGenerate = arrayListOf(
            arrayListOf(
                "-xjc-Xequals",
                "-xjc-XhashCode",
                "-xjc-XtoString",
                "$projectDir/src/main/resources/input.wsdl"
            )
        )
    }
}

val wsdl2java: Wsdl2JavaTask by tasks
description = "Creates Java classes and resources from WSDL schema."
sourceSets {
    getByName("main") {
        java.setSrcDirs(java.srcDirs + wsdl2java.generatedWsdlDir)
    }
}
