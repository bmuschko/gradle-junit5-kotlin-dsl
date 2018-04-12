plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("gradle-plugin"))
}

gradlePlugin {
    (plugins) {
        "functional-test-plugin" {
            id = "com.bmuschko.functional-test"
            implementationClass = "com.bmuschko.FunctionalTestPlugin"
        }
    }
}