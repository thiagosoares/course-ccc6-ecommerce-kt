package br.com.tfsoares.courses.branas.ecommerce.domain.entity

import java.time.LocalDateTime

// TODO Como armazenar o Total aqui ? Faz sentido devolver CPF para que lê order ?
class Order(
    val cpf: Cpf,
    val code: OrderCode,
    var coupon: Coupon? = null,
    private var freight: Freight = Freight(),
    private val items: MutableList<OrderItem> = mutableListOf()
) {

    constructor(
        cpf: String,
        sequence: Long = 1,
        date: LocalDateTime = LocalDateTime.now()
    ) : this(Cpf(cpf), OrderCode(sequence, date)) {
    }

    fun getOrderSize(): Int {
        return items.size
    }

    fun addItem(item: Item, quantity: Int) {
        this.freight.addItem(item, quantity)
        this.items.add(OrderItem(item.getIdItem(), item.getPrice(), quantity))
    }

    fun applyCoupon(coupon: Coupon) {
        this.coupon = coupon
    }

    fun getTotalOrder(): Double {
        return buildOrderTotal()
            .run {
                applyCouponDiscount(this)
            }.run {
                applyFreight(this)
            }
    }

    private fun applyCouponDiscount(orderValue: Double): Double {
        return coupon?.applyDiscount(orderValue) ?: orderValue
    }

    private fun applyFreight(total: Double): Double {
        return total + this.getFreight()
    }

    private fun buildOrderTotal(): Double {
        return items.map { it.getTotal() }.sum()
    }

    fun applyFreight(freight: Freight) {
        this.freight = freight
    }

    fun getFreight() = this.freight.getTotal()

    fun getOrderCode() = code.code

    fun getItems() = this.items.toList()
}
