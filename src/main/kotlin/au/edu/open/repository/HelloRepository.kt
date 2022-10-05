package au.edu.open.repository

import jakarta.inject.Singleton

/**
 * A simple class to test Koin dependency injection with.
 */

@Singleton
class HelloRepository {
    fun getHello(): String = "Hello micronaut"
}