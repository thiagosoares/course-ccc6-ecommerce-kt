package br.com.tfsoares.courses.branas.ecommerce.domain.entity

data class OrderItem(
    val idItem: Int,
    val price: Double, // TODO Não sei se deveria estar aqui
    val quantity: Int,
    private val idOrder: Int? = null
) {

    fun getTotal() = price * quantity
}
