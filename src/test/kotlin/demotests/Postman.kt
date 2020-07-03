package demotests

import assistants.POSTMAN_URL
import assistants.json
import com.natpryce.hamkrest.isEmpty
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import model.postman.Cookies
import model.postman.Status
import org.junit.jupiter.api.Test
import requests.getRequest
import java.net.HttpURLConnection.HTTP_OK

class Postman {

    /**
     * Test url https://postman-echo.com/cookies
     */
    @Test
    fun `get request path cookies`() {
        val url = "$POSTMAN_URL/cookies"
        val response = getRequest(url, HTTP_OK)
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
        val url = "$POSTMAN_URL/status/200"
        val response = getRequest(url, HTTP_OK)
        response.json<Status>().apply { status shouldBe 200 }
    }
}
