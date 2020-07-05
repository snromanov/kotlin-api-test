package helper.auth

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy::class)
data class Token(
    val accessToken: String
)

data class Auth(
    val login: String,
    val password: String
)
