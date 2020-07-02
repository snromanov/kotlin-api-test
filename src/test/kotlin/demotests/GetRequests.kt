package demotests

import assistants.json
import com.natpryce.hamkrest.isEmpty
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import model.postman.Cookies
import model.postman.Status
import org.junit.jupiter.api.Test
import requests.getRequest
import java.net.HttpURLConnection.HTTP_OK

class GetRequests {

    /**
     * Test url https://postman-echo.com/cookies
     */
    @Test
    fun `get request path cookies`() {
        val response = getRequest(HTTP_OK, "cookies")
        response.json<Cookies>().apply {
            cookies shouldNotBe isEmpty
        }
    }

    /**
     * Test url https://postman-echo.com/status/200
     *
     */
    @Test
    fun `get request path status`() {
        val response = getRequest(HTTP_OK, "status/200")
        response.json<Status>().apply { status shouldBe 200 }
    }
}
