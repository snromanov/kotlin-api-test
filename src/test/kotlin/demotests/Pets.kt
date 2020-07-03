package demotests

import assistants.PETSTORE_URL
import assistants.bodyOrder
import assistants.json
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import model.pets.Order
import org.junit.jupiter.api.Test
import requests.getOrderId
import requests.getRequest
import requests.postRequest
import java.net.HttpURLConnection.HTTP_OK

class Pets {

    /**
     * Test url https://petstore.swagger.io/v2/store/order
     *
     */
    @Test
    fun `check order`() {
        val url = "$PETSTORE_URL/v2/store/order"
        val response = postRequest(url, bodyOrder)

        response.json<Order>().assertSoftly {
            id.shouldNotBeNull()
            petId shouldBe 0
            quantity shouldBe 0
            status shouldBe "placed"
            complete.shouldBeTrue()
        }
    }

    /**
     * Test url https://petstore.swagger.io/v2/store/order/{orderId}
     *
     */
    @Test
    fun `get order by id`() {
        val url = "$PETSTORE_URL/v2/store/order/${getOrderId()}"
        val response = getRequest(url, HTTP_OK)

        response.json<Order>().assertSoftly {
            id.shouldNotBeNull()
            petId shouldBe 0
            quantity shouldBe 0
            status shouldBe "placed"
            complete.shouldBeTrue()
        }
    }
}
