package au.edu.open.controller

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller(value = "/health")
class HealthCheckController(
    private val applicationContext: ApplicationContext
) {
    @Get(produces = [MediaType.TEXT_PLAIN])
    fun checkHealth(): HttpResponse<String> {
        return when (applicationContext.isRunning) {
            true -> HttpResponse.ok("ok")
            else -> HttpResponse.status(HttpStatus.SERVICE_UNAVAILABLE, "Service Unavailable")
        }
    }
}
