package br.com.tfsoares.courses.branas.ecommerce.domain

class Order(cpf: String) {

    val cpf: Cpf
    private var coupon: Coupon? = null
    private val items: MutableList<OrderItem>

    init {
        this.cpf = Cpf(cpf)
        items = mutableListOf()
    }

    fun getOrderSize(): Int {
        return items.size
    }

    fun addItem(orderItem: OrderItem) {
        this.items.add(orderItem)
    }

    fun applyCoupon(coupon: Coupon) {
        this.coupon = coupon
    }

    fun getTotalOrder(): Float {
        return applyCouponDiscount(buildOrderTotal())
    }

    private fun buildOrderTotal(): Float {
        return items.map { it.getTotal() }.sum()
    }

    private fun applyCouponDiscount(orderValue: Float): Float {
        return coupon?.applyDiscount(orderValue) ?: orderValue
    }
}
