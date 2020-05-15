package demotests

import assertk.assertThat
import assertk.assertions.isNotNull
import com.github.kittinunf.fuel.httpGet
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.containsSubstring
import helper.allure.toAllure
import helper.assertions.checkOk
import helper.logger.toLog
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

/**
 * If you need more examples, use https://github.com/kittinunf/fuel
 */
@Story("Demo test uses kotlin fuel")
class DemoTest {

    private val demoUrl = "https://postman-echo.com"
    private val path = "$demoUrl/cookies"

    @DisplayName("Get response and check contains")
    @Test
    fun getRequestCheckContains() {
        val (_, response, result) =
            path
                .httpGet()
                .responseString()
                .toAllure()
                .toLog()
        val resultForAssert = result.get()
        checkOk(response)
        assertThat("Result contain", resultForAssert, containsSubstring("cookies"))
    }

    @DisplayName("Get response and check body")
    @Test
    fun getRequestCheckCorrectBody() {
        val correctBody = "{\"cookies\":{}}"
        val (_, response, result) =
            path
                .httpGet()
                .responseString()
                .toAllure()
                .toLog()
        val resultForAssert = result.get()
        checkOk(response)
        assertThat("There is test body", resultForAssert, containsSubstring(correctBody))
    }

    @DisplayName("Get response and check response is not null")
    @Test
    fun getResponseAndCheckResponseNotNull() {
        val (_, response, _) =
            path
                .httpGet()
                .responseString()
                .toAllure()
                .toLog()
        checkOk(response)
        assertThat(response, "Response is not null").isNotNull()
    }
}
