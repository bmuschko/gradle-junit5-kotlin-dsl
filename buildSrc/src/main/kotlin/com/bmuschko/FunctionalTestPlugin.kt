package com.bmuschko

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginConvention
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

class FunctionalTestPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        val sourceSets = project.the<JavaPluginConvention>().sourceSets
        val testRuntimeClasspath by configurations

        val functionalTestSourceSet by sourceSets.creating {
            withConvention(KotlinSourceSet::class) {
                kotlin.srcDir("src/functTest/kotlin")
            }
            resources.srcDir("src/functTest/resources")
            compileClasspath += sourceSets["main"]!!.output + testRuntimeClasspath
            runtimeClasspath += output + compileClasspath
        }

        val functionalTest by tasks.creating(Test::class) {
            description = "Runs the functional tests"
            group = "verification"
            testClassesDirs = functionalTestSourceSet.output.classesDirs
            classpath = functionalTestSourceSet.runtimeClasspath
            mustRunAfter("test")
            reports {
                html.destination = file("${html.destination}/functional")
                junitXml.destination = file("${junitXml.destination}/functional")
            }
        }

        tasks.getByName("check").dependsOn(functionalTest)
    }
}