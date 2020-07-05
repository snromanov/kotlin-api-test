package assistants

import com.fasterxml.jackson.module.kotlin.readValue
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.jackson.defaultMapper
import com.github.kittinunf.result.Result

inline fun <reified T : Any> String.json() = defaultMapper.readValue<T>(this)
inline fun <reified T : Any> ByteArray.json() = defaultMapper.readValue<T>(this)
inline fun <reified T : Any> Response.json() = this.data.json<T>()

inline fun <reified T : Any> Triple<Request, Response, Result<T, FuelError>>.body(): T = this.let { (_, _, result) ->
    when (result) {
        is Result.Success -> result.value
        is Result.Failure -> throw result.error
    }
}
