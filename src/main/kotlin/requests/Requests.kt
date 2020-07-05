package requests

import assistants.PETSTORE_URL
import assistants.body
import assistants.bodyOrder
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.jackson.responseObject
import helper.allure.toAllure
import helper.logger.toLogIfNot
import java.net.HttpURLConnection.HTTP_OK

/**
 * Get request
 *
 * @param url
 * @return response
 */
fun getRequest(url: String, status: Int): Response {
    val (_, response) =
        url
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
            .toLogIfNot(HTTP_OK)

    return response
}

/**
 * Get order id
 *
 */
inline fun <reified T : Any> getOrderId(): T {
    val url = "$PETSTORE_URL/v2/store/order"
    return url
        .httpPost()
        .jsonBody(bodyOrder)
        .responseObject<T>()
        .toAllure()
        .toLogIfNot(HTTP_OK)
        .body()
}
