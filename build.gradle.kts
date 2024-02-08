val kotlin_version: String by project
val ktor_version: String by project
val selenium_version: String by project
val logback_version: String by project
val freemarker_version: String by project

// Apply the Kotlin JVM plugin and specify its version
plugins {
    kotlin("jvm") version "1.9.22" // or the version you are using

    id("io.ktor.plugin") version "2.3.0"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.22"
}

// Set the group ID and version number for your project
group = "com.example"
version = "1.0-SNAPSHOT"

//This code block is used for projects that are standalone applications. It sets up the main class and some JVM arguments for the application.
application {
    mainClass.set("com.example.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

// Define repositories where dependencies will be fetched from
repositories {
    mavenCentral() // Use Maven Central repository to resolve dependencies
    maven { url = uri("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-js-wrappers") }
}

// Define the project dependencies
dependencies {
    // Include Kotlin standard library
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    //Include Ktor
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-server-freemarker-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-html-builder-jvm:$ktor_version")
    implementation("org.jetbrains:kotlin-css-jvm:1.0.0-pre.129-kotlin-1.4.20")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-gson-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-call-logging-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-default-headers-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-compression-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-host-common-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-status-pages-jvm:$ktor_version")
    // Include Logback for logging
    implementation("ch.qos.logback:logback-classic:$logback_version")

    // Include Selenium dependency for web scraping
    implementation("org.seleniumhq.selenium:selenium-java:$selenium_version")

    //Include FreeMarker for HTML Template
    //implementation("org.freemarker:freemarker:$freemarker_version")

    // Include Apache POI dependency for working with Microsoft Office files
    //implementation("org.apache.poi:poi:5.1.0")

    // Include Apache POI OOXML dependency for working with Excel files
    //implementation("org.apache.poi:poi-ooxml:5.1.0")

    // Include Kotlin test dependencies for testing
    testImplementation(kotlin("test"))

    // Include JUnit 5 dependencies for testing
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    //Include Mustache HTML Templates library
    implementation("com.github.spullara.mustache.java:compiler:0.9.10")
}

// Configure the test task to use JUnit Platform (JUnit 5)
tasks.test {
    useJUnitPlatform()
}

// Set the JVM target for Kotlin compilation
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
