package model.pets

data class Order(
    val complete: Boolean,
    val id: Int,
    val petId: Int,
    val quantity: Int,
    val shipDate: String?,
    val status: String
)
