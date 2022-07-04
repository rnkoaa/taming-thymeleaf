import com.github.gradle.node.npm.task.NpmTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    id("org.springframework.boot") version "2.7.1"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    id("com.github.node-gradle.node") version "3.4.0"
}

group = "org.richard.thymeleaf"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

//    implementation("jakarta.inject:jakarta.inject-api:2.0.1")
    implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect:3.1.0")

    implementation("com.github.javafaker:javafaker:1.0.2") {
        exclude(group = "org.yaml", module = "snakeyaml")
    }
    implementation("org.yaml:snakeyaml:1.30") {
        exclude(group = "org.yaml", module = "snakeyaml")
    }

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.assertj:assertj-core:3.23.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<BootRun> {
    dependsOn(":run-gulp-build")
}

tasks.withType<Jar> {
    dependsOn(":run-gulp-build")
}
//tasks.register<Copy>("copyReport") {
//    from(layout.buildDirectory.dir("reports/my-report.pdf"))
//    into(layout.buildDirectory.dir("toArchive"))
//}

tasks.withType<ProcessResources> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    exclude("**/*.css")
    exclude("**/*.js")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val npmInstall by tasks.npmInstall

tasks.register<NpmTask>("run-gulp-build") {
    args.addAll("run", "build")
}

tasks.register<NpmTask>("run-gulp-build-prod") {
    args.addAll("run", "build-prod")
}
