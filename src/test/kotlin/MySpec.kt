import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertTrue

class MySpec : Spek({
    describe("A simple test") {
        it("just works") {
            assertTrue(true)
        }
    }
})