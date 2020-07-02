package requests

import assistants.POSTMAN_URL
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import helper.allure.toAllure
import helper.logger.toLogIfNot
import java.net.HttpURLConnection

/**
 * Get request
 *
 * @param status
 * @param path
 * @return response
 */
fun getRequest(status: Int, path: String): Response {
    val (_, response) =
        "$POSTMAN_URL/$path"
            .httpGet()
            .responseString()
            .toAllure()
            .toLogIfNot(status)

    return response
}

/**
 * Post request
 *
 * @param url
 * @param data
 * @return
 */
fun postRequest(url: String, data: String): Response {
    val (_, response) =
        url
            .httpPost()
            .jsonBody(data)
            .responseString()
            .toAllure()
            .toLogIfNot(HttpURLConnection.HTTP_OK)

    return response
}
