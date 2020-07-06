package model.pets

data class Order(
    val complete: Boolean,
    val id: Long,
    val petId: Int,
    val quantity: Int,
    val shipDate: String?,
    val status: String
)
