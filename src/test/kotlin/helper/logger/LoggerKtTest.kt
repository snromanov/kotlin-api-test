package helper.logger

import assistants.PETSTORE_URL
import assistants.POSTMAN_URL
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.should
import io.kotest.matchers.string.startWith
import org.junit.jupiter.api.Test
import requests.getRequest

class LoggerKtTest {

    @Test
    fun loggerException() {
        val url = listOf(POSTMAN_URL, PETSTORE_URL).shuffled().first()
        val status = 418
        val exception = shouldThrow<IllegalArgumentException> {
            getRequest(url, status)
        }
        exception.message should startWith("The server should have returned code = $status")
    }
}
