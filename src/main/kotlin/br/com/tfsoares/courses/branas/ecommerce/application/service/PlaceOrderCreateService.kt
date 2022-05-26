package br.com.tfsoares.courses.branas.ecommerce.application.service

import br.com.tfsoares.courses.branas.ecommerce.domain.entity.Freight
import br.com.tfsoares.courses.branas.ecommerce.domain.entity.Order
import br.com.tfsoares.courses.branas.ecommerce.domain.inbound.NewOrderDto
import br.com.tfsoares.courses.branas.ecommerce.domain.outbound.OrderCreateDto
import br.com.tfsoares.courses.branas.ecommerce.domain.repository.CouponRepository
import br.com.tfsoares.courses.branas.ecommerce.domain.repository.ItemRepository
import br.com.tfsoares.courses.branas.ecommerce.domain.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class PlaceOrderCreateService(
    private val itemRepository: ItemRepository,
    private val orderRepository: OrderRepository,
    private val couponRepository: CouponRepository
) {

    fun save(newOrder: NewOrderDto): OrderCreateDto {

        val order = Order(cpf = newOrder.cpf, sequence = getSequence())

        addItems(newOrder, order)
        applyCoupon(newOrder, order)
        saveOrder(order)
        return OrderCreateDto(order.getOrderCode(), order.getTotalOrder())
    }

    private fun saveOrder(order: Order) {
        orderRepository.save(order)
    }

    private fun getSequence() = orderRepository.countAll() + 1

    private fun addItems(newOrder: NewOrderDto, order: Order) {
        val freight = Freight()
        newOrder.orderItems.forEach {
            val item = itemRepository.findById(it.idItem)
            order.addItem(item, it.quantity)
            freight.addItem(item, it.quantity)
        }
        order.applyFreight(freight)
    }

    private fun applyCoupon(newOrder: NewOrderDto, order: Order) {
        newOrder.coupon?.let {
            val coupon = couponRepository.findByCode(it)
            order.applyCoupon(coupon)
        }
    }
}
