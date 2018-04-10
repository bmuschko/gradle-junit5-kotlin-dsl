plugins {
    kotlin("jvm") version "1.2.31"
}

group = "com.bmuschko"
version = "0.1"

repositories {
    jcenter()
}

val kotlinVersion = "1.2.31"
val junitPlatformVersion = "1.1.0"
val spekVersion = "1.1.5"

dependencies {
    testImplementation(kotlin("reflect", kotlinVersion))
    testImplementation(kotlin("test", kotlinVersion))
    
    testImplementation("org.jetbrains.spek:spek-api:$spekVersion") {
        exclude(group = "org.jetbrains.kotlin")
    }

    testRuntimeOnly("org.jetbrains.spek:spek-junit-platform-engine:$spekVersion") {
        exclude(group = "org.jetbrains.kotlin")
        exclude(group = "org.junit.platform")
    }

    testRuntimeOnly("org.junit.platform:junit-platform-launcher:$junitPlatformVersion") {
        because("Needed to run tests IDEs that bundle an older version")
    }
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform {
        includeEngines("spek")
    }
}