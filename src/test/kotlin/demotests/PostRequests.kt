package demotests

import assistants.PETSTORE_URL
import assistants.json
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import model.pets.Order
import org.junit.jupiter.api.Test
import requests.postRequest

class PostRequests {

    /**
     * Body
     */
    private val bodyOrder = """ { "id": 0,
  "petId": 0,
  "quantity": 0,
  "shipDate": "2020-07-02T13:45:38.799Z",
  "status": "placed",
  "complete": true }""".trimIndent()

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
}
