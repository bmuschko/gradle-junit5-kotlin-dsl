import org.gradle.testkit.runner.GradleRunner
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import java.io.File
import kotlin.test.assertTrue

class MyFunctionalSpec : Spek({
    describe("A functional test") {
        it("uses TestKit") {
            val tmpDir = createTempDir("junit5", null, null)
            val buildFile = File(tmpDir, "build.gradle")
            buildFile.writeText("""
                task helloWorld {
                    doLast {
                        println "Hello world!"
                    }
                }
            """.trimIndent())
            println(buildFile.readText())
            val result = GradleRunner.create()
                    .withProjectDir(buildFile.parentFile)
                    .withArguments("helloWorld")
                    .forwardOutput()
                    .build()
            assertTrue(result.output.contains("Hello world!"))
        }
    }
})