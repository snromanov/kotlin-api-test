package helper.assertions

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.kittinunf.fuel.core.Response

/**
 * Simplify function for add annotations in Allure-reports if tests tear down
 */

fun checkOk(response: Response) =
    assertThat(response.statusCode, "Check http status = HTTP_OK").isEqualTo(200)

fun checkUnauthorized(response: Response) =
    assertThat(response.statusCode, "Check http status = HTTP_UNAUTHORIZED").isEqualTo(401)

// todo
