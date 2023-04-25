val kotlinVersion: String by project
val seleniumVersion: String by project

// Apply the Kotlin JVM plugin and specify its version
plugins {
    kotlin("jvm") version "1.8.20" // or the version you are using
    // Other plugins if needed
}

// Set the group ID and version number for your project
group = "com.example"
version = "1.0-SNAPSHOT"

// Define repositories where dependencies will be fetched from
repositories {
    mavenCentral() // Use Maven Central repository to resolve dependencies
}

// Define the project dependencies
dependencies {
    // Include Kotlin standard library
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    // Include Selenium dependency for web scraping
    implementation("org.seleniumhq.selenium:selenium-java:$seleniumVersion")

    //Include FreeMarker for HTML Template
    implementation("org.freemarker:freemarker:2.3.32")

    // Include Apache POI dependency for working with Microsoft Office files
    implementation("org.apache.poi:poi:5.1.0")

    // Include Apache POI OOXML dependency for working with Excel files
    implementation("org.apache.poi:poi-ooxml:5.1.0")

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
