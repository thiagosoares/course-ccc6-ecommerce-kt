package br.com.tfsoares.courses.branas.ecommerce.domain.inbound

import java.time.LocalDateTime

data class NewOrderDto(
    val cpf: String,
    val orderItems: List<NewOrderItem>,
    val coupon: String? = null,
    val date: LocalDateTime? = null
)
