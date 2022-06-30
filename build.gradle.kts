import com.github.gradle.node.npm.task.NpmTask
import com.github.gradle.node.npm.task.NpxTask
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
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<BootRun> {
    println("Building Jar")
    dependsOn(":run-gulp-build")
}

tasks.withType<Jar> {
    println("Building Jar")
    dependsOn(":run-gulp-build")
}
//tasks.register<Copy>("copyReport") {
//    from(layout.buildDirectory.dir("reports/my-report.pdf"))
//    into(layout.buildDirectory.dir("toArchive"))
//}

tasks.withType<ProcessResources> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
//    exclude("**/*.html")
    exclude("**/*.css")
    exclude("**/*.js")
//    exclude { details: FileTreeElement ->
//        details.file.name.endsWith(".html") &&
//                details.file.readText().contains("DRAFT")
//    }
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
