package helper.library

import helper.auth.GetToken

object TestConfig {
    val baseDomain: String = "https://${EnvConfig().base}.domen.com".toLowerCase()
    val accessToken: String = GetToken(EnvConfig()).token().accessToken
}
