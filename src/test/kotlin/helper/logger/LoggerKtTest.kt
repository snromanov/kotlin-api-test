package helper.logger

import assistants.POSTMAN_URL
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.should
import io.kotest.matchers.string.startWith
import org.junit.jupiter.api.Test
import requests.getRequest

class LoggerKtTest {

    @Test
    fun loggerException() {
        val exception = shouldThrow<IllegalArgumentException> {
            getRequest(POSTMAN_URL, 418)
        }
        exception.message should startWith("The server should have returned code")
    }
}
