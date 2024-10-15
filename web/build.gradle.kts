import org.gradle.kotlin.dsl.java
import org.openapitools.generator.gradle.plugin.tasks.GenerateTask
import org.springframework.cloud.contract.verifier.config.TestMode

plugins {
    id("java-library")
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
    id("io.freefair.lombok") version "8.10.2"
    id("org.springframework.cloud.contract") version "4.1.4"
    id("org.openapi.generator") version "7.9.0"
}

repositories {
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2023.0.3")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework:spring-tx")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-api:2.6.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-verifier")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

contracts {
    testMode = TestMode.EXPLICIT
    packageWithBaseClasses = "com.example.demo.adapter.input"
}

val buildDirectoryAsPath = layout.buildDirectory.get().asFile.path
java.sourceSets["main"].java.srcDir("$buildDirectoryAsPath/generated/openapi/src/main/java")

tasks.register<GenerateTask>("openApiGenerate-custom") {
    group = "open-api"
    description = "Generate java code from the openAPI document"

    generatorName.set("spring")
    library.set("spring-boot")
    inputSpec.set("$rootDir/web/src/main/resources/api.yml")
    outputDir.set("$buildDirectoryAsPath/generated/openapi")
    apiPackage.set("com.example.generated.api")
    modelPackage.set("com.example.generated.model")
    configFile.set("$rootDir/web/src/main/resources/config.yml")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.withType<JavaCompile>().configureEach {
    dependsOn("openApiGenerate-custom")
    options.compilerArgs.add("-parameters")
}
