package au.edu.open.service

import au.edu.open.repository.HelloRepository
import jakarta.inject.Singleton

/**
 * Implementation to provide concrete version with our Koin example.
 */
@Singleton
class HelloServiceImpl(private val helloRepository: HelloRepository) : HelloService {
    override fun sayHello(): String {
        return " ${helloRepository.getHello()}"
    }

}