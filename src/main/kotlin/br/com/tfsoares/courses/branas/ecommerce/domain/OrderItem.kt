package br.com.tfsoares.courses.branas.ecommerce.domain

data class OrderItem(private val idItem: Int, private val price: Float, private val quantity: Int) {

    fun getTotal() = price * quantity
}
