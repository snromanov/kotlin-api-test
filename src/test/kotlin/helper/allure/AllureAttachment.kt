package helper.allure

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result
import io.qameta.allure.Allure.addAttachment

/**
 * Simplify function for add allure attachment
 */
inline fun <reified T : Any> Triple<Request, Response, Result<T, FuelError>>.toAllure():
        Triple<Request, Response, Result<T, FuelError>> {
    addAttachment("Request", this.first.toString())
    addAttachment("Response", this.second.toString())
    addAttachment("Result", this.third.toString())
    return this
}
