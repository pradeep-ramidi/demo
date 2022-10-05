package au.edu.open

import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.restassured.specification.RequestSpecification
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@MicronautTest
class HelloTest {

    @Test
    fun testHelloEndpoint(spec: RequestSpecification) {
        spec
            .`when`()
            .get("/hello")
            .then()
            .statusCode(200)
            .body(`is`("Hello World"))
    }
}