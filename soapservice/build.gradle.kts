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
		implementation("org.springframework.ws:spring-ws-core") {
			exclude(group ="javax.xml.soap", module = "javax.xml.soap-api")
		}
		implementation("org.springframework.boot:spring-boot-starter-web-services") {
			exclude(group = "javax.xml.soap", module = "javax.xml.soap-api")
		}
		implementation("wsdl4j:wsdl4j")
		implementation("jakarta.xml.soap:jakarta.xml.soap-api:3.0.2")
		implementation("com.sun.xml.messaging.saaj:saaj-impl:3.0.4")
		implementation("org.springframework:spring-oxm")
		annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
		testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.2")
		testImplementation("org.assertj:assertj-core:3.24.2")
		testImplementation("org.springframework.boot:spring-boot-starter-test")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
