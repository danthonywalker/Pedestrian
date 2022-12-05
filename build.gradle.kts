plugins {
    java
    id("org.springframework.boot") version "3.0.0"
    id("io.spring.dependency-management") version "1.1.0"

    id("com.google.cloud.tools.jib") version "3.3.1"
}

group = "dev.neontech"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_19

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-web")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

jib {
    var imageVersion = version.toString()
    if (imageVersion.contains("SNAPSHOT")) imageVersion = "latest"

    to.image = "rg.nl-ams.scw.cloud/dreamexposure/pedestrian:$imageVersion"
    from.image = "eclipse-temurin:19-jre-alpine"
}

tasks.withType<Test> {
    useJUnitPlatform()
}
