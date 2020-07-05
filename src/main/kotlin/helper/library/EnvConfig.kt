package helper.library

/**
 * Function for get properties from env
 */
data class EnvConfig(
    val login: String = System.getenv("API_LOGIN"),
    val password: String = System.getenv("API_PASSWORD"),
    val base: String = System.getenv("ENV_ID")
)
