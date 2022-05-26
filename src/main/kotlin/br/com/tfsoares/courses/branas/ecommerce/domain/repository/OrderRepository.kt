package br.com.tfsoares.courses.branas.ecommerce.domain.repository

import br.com.tfsoares.courses.branas.ecommerce.domain.entity.Order
import br.com.tfsoares.courses.branas.ecommerce.domain.outbound.OrderOutput

interface OrderRepository {

    fun save(order: Order)
    fun findAll(): List<OrderOutput>
    fun countAll(): Long
}
