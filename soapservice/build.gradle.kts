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
	implementation("org.springframework.ws:spring-ws-core:3.1.8")
	implementation("org.springframework.boot:spring-boot-starter-web-services:3.3.5")
	implementation("wsdl4j:wsdl4j")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

	/**
	 * XSLT
	 */
	implementation("org.springframework:spring-oxm")

	/**
	 * Tests
	 */
	testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.2")
	testImplementation("org.assertj:assertj-core:3.24.2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
