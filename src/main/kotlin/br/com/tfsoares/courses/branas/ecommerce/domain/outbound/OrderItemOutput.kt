package br.com.tfsoares.courses.branas.ecommerce.domain.outbound

data class OrderItemOutput(
    val idItem: Int,
    val price: Double,
    val quantity: Int
)
