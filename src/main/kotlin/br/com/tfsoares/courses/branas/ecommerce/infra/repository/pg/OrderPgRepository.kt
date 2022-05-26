package br.com.tfsoares.courses.branas.ecommerce.infra.repository.pg

import br.com.tfsoares.courses.branas.ecommerce.domain.entity.*
import br.com.tfsoares.courses.branas.ecommerce.domain.outbound.OrderItemOutput
import br.com.tfsoares.courses.branas.ecommerce.domain.outbound.OrderOutput
import br.com.tfsoares.courses.branas.ecommerce.domain.repository.OrderRepository
import org.apache.commons.lang3.math.NumberUtils.toDouble
import org.apache.commons.lang3.math.NumberUtils.toInt
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import javax.persistence.EntityManager

/**
 * Saudades da JPA e do SpringData
 */
@Component
@Profile("!test & pg")
class OrderPgRepository(
    val em: EntityManager
) : OrderRepository {

    override fun save(order: Order) {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<OrderOutput> {
        val orderResult = em.createNativeQuery(
            "SELECT id, cpf, code, freight_value, total_order, coupon_id FROM store.order"
        ).resultList as MutableList<Array<Any?>>

        val couponIds = orderResult.mapNotNull { it[5]?.toString() }
        val orderCoupons = getAllOrderCoupons(couponIds)

        val orderIds = orderResult.mapNotNull { it[0] }
        val orderItems = getAllOrderItems(orderIds)

        // TODO Como obter o getTotalOrder aqui ?

        return orderResult.map {
            OrderOutput(
                cpf = it[1].toString(),
                code = it[2].toString(),
                coupon = filterCoupon(orderCoupons, it[5]),
                freight = toDouble(it[3].toString()),
                items = filterOrderItems(orderItems, it[0])
            )
        }
    }

    private fun getAllOrderCoupons(couponIds: List<String>): List<Coupon> {
        val q = em.createNativeQuery(
            "SELECT code, percentage, expiration_date FROM store.coupon WHERE code IN(:codes)"
        )
        q.setParameter("codes", couponIds)
        val couponsResult = q.resultList as MutableList<Array<Any>>

        return couponsResult.map {
            Coupon(
                code = it[0].toString(),
                percentage = toInt(it[1].toString()),
                expireDate = LocalDateTime.now() // TODO Essa conversão não está rolando
            )
        }
    }

    private fun getAllOrderItems(orderIds: List<Any>): List<OrderItemOutput> {

        val q = em.createNativeQuery(
            "select id_order, id_item, price, quantity " +
                "from store.order_item oi " +
                "where oi.id_order IN (:orderIds)"
        )
        q.setParameter("orderIds", orderIds)
        val couponsResult = q.resultList as MutableList<Array<Any>>

        return couponsResult.map {
            OrderItemOutput(
                idItem = toInt(it[1].toString()),
                price = toDouble(it[2].toString()),
                quantity = toInt(it[3].toString())
            )
        }
    }

    private fun filterCoupon(coupons: List<Coupon>, code: Any?): Coupon? {
        return coupons.firstOrNull { it.code == code.toString() }
    }

    private fun filterOrderItems(orderItems: List<OrderItemOutput>, orderId: Any?): MutableList<OrderItemOutput> {
        return orderItems.filter { it.idItem == orderId }.toMutableList()
    }

    override fun countAll(): Long {

        TODO("Not yet implemented")
    }
}
