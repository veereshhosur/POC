plugins {
	java
	id("org.springframework.boot") version "3.2.3"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.lbg"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")


	implementation("org.springframework.boot:spring-boot-starter-mail")

	implementation("org.xerial:sqlite-jdbc:3.43.2.0")


	implementation("org.hibernate.orm:hibernate-community-dialects:6.4.4.Final")

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

// https://mvnrepository.com/artifact/org.hibernate/hibernate-entitymanager
	implementation("org.hibernate:hibernate-entitymanager:5.6.15.Final")

	compileOnly ("org.projectlombok:lombok:1.18.32")
	annotationProcessor ("org.projectlombok:lombok:1.18.32")

	testCompileOnly ("org.projectlombok:lombok:1.18.32")
	testAnnotationProcessor ("org.projectlombok:lombok:1.18.32")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
