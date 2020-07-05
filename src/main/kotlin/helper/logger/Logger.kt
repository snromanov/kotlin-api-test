package helper.logger

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result
import org.slf4j.Logger
import org.slf4j.LoggerFactory

val log: Logger = LoggerFactory.getLogger("HttpLogger")

/**
 * Simplify function add logs for  if request fail
 *
 */
inline fun <reified T : Any> Triple<Request, Response, Result<T, FuelError>>.toLogIfNot(status: Int) = also {
    when {
        second.statusCode != status -> {

            log.info("{}", first.toString())
            log.info("{}", second.toString())

            throw IllegalArgumentException(
                "The server should have returned code = $status, " +
                        "but returned the code = ${second.statusCode}"
            )
        }
    }
}
