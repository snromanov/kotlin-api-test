package helper.auth

import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.jackson.responseObject
import helper.json.objectBody
import helper.library.EnvConfig
import helper.library.TestConfig.baseDomain

class GetToken(private val apiConfig: EnvConfig) {

    /**
     * Function to receive token:
     * - access token
     */
    fun token(): Token {
        val urlAuth = "$baseDomain/auth/login"
        return urlAuth
            .httpPost()
            .objectBody(Auth(apiConfig.login, apiConfig.password))
            .responseObject<Token>()
            .third.get()
    }
}
