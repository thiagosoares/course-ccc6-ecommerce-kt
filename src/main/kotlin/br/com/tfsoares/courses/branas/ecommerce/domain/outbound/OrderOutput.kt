package br.com.tfsoares.courses.branas.ecommerce.domain.outbound

import br.com.tfsoares.courses.branas.ecommerce.domain.entity.Coupon

class OrderOutput(
    val cpf: String,
    val code: String,
    var coupon: Coupon? = null, // TODO Rever esse tipo
    val freight: Double,
    private val items: List<OrderItemOutput> = listOf()
)
