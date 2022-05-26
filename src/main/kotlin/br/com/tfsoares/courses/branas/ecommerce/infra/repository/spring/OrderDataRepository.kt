package br.com.tfsoares.courses.branas.ecommerce.infra.repository.spring

import br.com.tfsoares.courses.branas.ecommerce.domain.entity.*
import br.com.tfsoares.courses.branas.ecommerce.domain.outbound.OrderOutput
import br.com.tfsoares.courses.branas.ecommerce.domain.repository.OrderRepository
import br.com.tfsoares.courses.branas.ecommerce.infra.repository.spring.jpa.OrderJpaRepository
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("!test & spring")
class OrderDataRepository(
    val orderJpaRepository: OrderJpaRepository
) : OrderRepository {

    override fun save(order: Order) {
//        val orderDomain = OrderDomain(1, "")
//        orderJpaRepository.save(orderDomain)
        TODO("Keep Calm!")
    }

    override fun findAll(): List<OrderOutput> {

        val list = orderJpaRepository.findAllByCpf("61923445073")

        list.forEach {

            println(it.id)

            it.items.forEach { it2 -> println(it2.item.id) }
        }

        return list.map {
            OrderOutput(
                cpf = it.cpf,
                code = it.code,
                coupon = it.coupon?.let { cp -> Coupon(cp.code, cp.percentage) },
                freight = it.freightValue,
                items = mutableListOf()
            )
        }
    }

    override fun countAll(): Long {
        return orderJpaRepository.count()
    }
}
