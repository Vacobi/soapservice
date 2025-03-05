plugins {
	java
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "axiomatika.converter"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	dependencies {
		implementation("org.springframework.ws:spring-ws-core")
		implementation("org.springframework.boot:spring-boot-starter-web-services")
		annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

		/**
		 * Tests
		 */
		testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.2")
		testImplementation("org.assertj:assertj-core:3.24.2")
		testImplementation("org.springframework.boot:spring-boot-starter-test")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
