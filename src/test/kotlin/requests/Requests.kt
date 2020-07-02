package requests

import assistants.PETSTORE_URL
import assistants.bodyOrder
import assistants.json
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import helper.allure.toAllure
import helper.logger.toLogIfNot
import model.pets.Order
import java.net.HttpURLConnection.HTTP_OK

/**
 * Get request
 *
 * @param url
 * @return response
 */
fun getRequest(url: String): Response {
    val (_, response) =
        url
            .httpGet()
            .responseString()
            .toAllure()
            .toLogIfNot(HTTP_OK)

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
fun getOrderId() =
    "$PETSTORE_URL/v2/store/order"
        .httpPost()
        .jsonBody(bodyOrder)
        .responseString()
        .toAllure()
        .toLogIfNot(HTTP_OK)
        .second
        .json<Order>()
        .id
