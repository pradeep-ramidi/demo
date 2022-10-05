package au.edu.open.controller

import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.*

/**
 * Simple class to route text based responses and demonstrate different parsing methods.
 */

@Controller(value = "/demo")
class DemoController {

    @Get(value = "/hello", produces = [MediaType.APPLICATION_JSON])
    fun hello(): MutableHttpResponse<String>? {
       return HttpResponse.ok("Hello visitor")
    }

    @Get(value= "/hello/{name}", produces = [MediaType.APPLICATION_JSON])
    fun helloName(@PathVariable name: String):MutableHttpResponse<String>? {
        return HttpResponse.ok("Hello ${name}")
    }

    @Post(processes = [MediaType.APPLICATION_JSON])
    fun demoPost(@Body body: String): HttpResponse<Any> {
        return HttpResponse.status<Any?>(HttpStatus.CREATED).body(body)
    }
}