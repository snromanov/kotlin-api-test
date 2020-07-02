package assistants

import com.fasterxml.jackson.module.kotlin.readValue
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.jackson.defaultMapper

inline fun <reified T : Any> String.json() = defaultMapper.readValue<T>(this)
inline fun <reified T : Any> ByteArray.json() = defaultMapper.readValue<T>(this)
inline fun <reified T : Any> Response.json() = this.data.json<T>()
