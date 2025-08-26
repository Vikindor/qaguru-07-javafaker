plugins {
    id("java")
}

group = "qa.vikindor"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("com.codeborne:selenide:7.9.4")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("com.github.javafaker:javafaker:1.0.2")
    // JUnit launcher
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    // SLF4J SimpleLogger
    testRuntimeOnly("org.slf4j:slf4j-simple:2.0.13")
}

tasks.test {
    useJUnitPlatform()
    // Redirect SLF4J SimpleLogger output from stderr (default, shown in red in IntelliJ)
    // to stdout so that log messages appear in the normal "gray" console color.
    jvmArgs("-Dorg.slf4j.simpleLogger.logFile=System.out")
    // Disable Selenium Manager telemetry (statistics sent to plausible.io)
    environment("SE_AVOID_STATS", "true")
}