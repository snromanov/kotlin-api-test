package helper.logger

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result
import org.slf4j.LoggerFactory

/**
 * Simplify function add logs
 */
inline fun <reified T : Any> Triple<Request, Response, Result<T, FuelError>>.toLog(): Triple<Request, Response,
        Result<T, FuelError>> {
    val log = LoggerFactory.getLogger("HttpLogger")
    log.info("{}", first.toString())
    log.info("{}", second.toString())
    log.info("{}", third.toString())
    return this
}
