package br.com.tfsoares.courses.branas.ecommerce.infra.repository

import br.com.tfsoares.courses.branas.ecommerce.domain.entity.Order
import br.com.tfsoares.courses.branas.ecommerce.domain.outbound.OrderItemOutput
import br.com.tfsoares.courses.branas.ecommerce.domain.outbound.OrderOutput
import br.com.tfsoares.courses.branas.ecommerce.domain.repository.OrderRepository
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("test")
class OrderRepositoryMemory : OrderRepository {

    private val orders = mutableListOf<OrderOutput>()

    override fun save(order: Order) {
        orders.add(
            OrderOutput(
                cpf = order.cpf.cpf,
                code = order.code.code,
                coupon = order.coupon, // TODO Rever esse tipo
                freight = order.getFreight(),
                items = order.getItems().map { OrderItemOutput(it.idItem, it.price, it.quantity) }
            )
        )
    }

    override fun findAll() = orders

    override fun countAll() = orders.size.toLong()
}
