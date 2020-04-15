import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.2.6.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"

	kotlin("jvm") version "1.3.61"
	kotlin("plugin.spring") version "1.3.61"
	kotlin("plugin.jpa") version "1.3.61"
}

group = "com.jissay"
version = "0.0.1-SNAPSHOT"

val developmentOnly by configurations.creating
configurations {
	runtimeClasspath {
		extendsFrom(developmentOnly)
	}
}

repositories { mavenCentral() }

dependencies {

	val junitVersion = "5.2.0"

	/* Spring standard libraries */
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-mustache")
	implementation("org.springframework.boot:spring-boot-starter-web")

	/* Spring dependencies */
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.security.oauth:spring-security-oauth2:2.4.0.RELEASE")
	implementation("org.springframework.boot:spring-boot-starter-actuator")

	/* Database libraries */
	implementation("org.postgresql:postgresql")
	runtimeOnly("com.h2database:h2")

	/* Kotlin libraries */
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlin:kotlin-allopen")

	/* Tools */
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	/* Testing libarries */
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks.withType<Test> {
	useJUnitPlatform()
	systemProperty("spring.profiles.active", "test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
