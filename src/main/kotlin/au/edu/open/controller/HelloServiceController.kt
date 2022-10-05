package au.edu.open.controller

import au.edu.open.service.HelloService
import au.edu.open.service.HelloServiceImpl
import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller(value = "/hello-service")
class HelloServiceController(
    private val applicationContext: ApplicationContext,
    private val helloService: HelloService
) {
    @Get(produces = [MediaType.TEXT_PLAIN])
    fun helloService(): HttpResponse<String> {
        return when (applicationContext.isRunning) {
            true -> HttpResponse.ok(helloService.sayHello())
            else -> HttpResponse.status(HttpStatus.SERVICE_UNAVAILABLE, "Service Unavailable")
        }
    }
}
