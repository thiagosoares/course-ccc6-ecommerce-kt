package br.com.tfsoares.courses.branas.ecommerce.domain

import br.com.tfsoares.courses.branas.ecommerce.errors.InvalidCouponException
import br.com.tfsoares.courses.branas.ecommerce.errors.InvalidCpfException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class OrderTest {

    companion object {
        const val VALID_CPF = "014.662.210-38"
        const val INVALID_CPF = "014.662.210-00"
    }

    @Test
    fun `GIVEN a valid cpf, WHEN create a Order, THEN create a empty order`() {
        val order = Order(VALID_CPF)
        assertThat(order.cpf).isEqualTo(Cpf(VALID_CPF))
        assertThat(order.getOrderSize()).isEqualTo(0)
    }

    @Test
    fun `GIVEN a valid cpf and 3 items, WHEN create a Order, THEN create a non empty order`() {
        val order = getValidOrder()
        assertThat(order.cpf.cpf).isEqualTo(VALID_CPF)
        assertThat(order.getOrderSize()).isEqualTo(3)
        assertThat(order.getTotalOrder()).isEqualTo(1887.97F)
    }

    @Test
    fun `GIVEN a order with coupon, WHEN create a Order, THEN create a order`() {
        val order = getValidOrder()
        order.applyCoupon(Coupon("20FF", 20))
        assertThat(order.cpf.cpf).isEqualTo(VALID_CPF)
        assertThat(order.getOrderSize()).isEqualTo(3)
        assertThat(order.getTotalOrder()).isEqualTo(1510.38F)
    }

    @Test
    fun `GIVEN a order with invalid coupon, WHEN create a Order, THEN get a coupon error`() {
        val order = getValidOrder()
        assertThrows<InvalidCouponException> {
            order.applyCoupon(Coupon("20FF", -1))
        }
    }

    @Test
    fun `GIVEN a empty order with coupon, WHEN create a Order, THEN create a order `() {
        val order = Order(VALID_CPF)
        order.applyCoupon(Coupon("20FF", 20))
        assertThat(order.cpf.cpf).isEqualTo(VALID_CPF)
        assertThat(order.getOrderSize()).isEqualTo(0)
        assertThat(order.getTotalOrder()).isEqualTo(0F)
    }

    @Test
    fun `GIVEN a invalid cpf, WHEN create a Order, THEN throw a error`() {
        assertThrows<InvalidCpfException> {
            Order(INVALID_CPF)
        }
    }

    private fun getValidOrder(): Order {
        val order = Order(VALID_CPF)
        order.addItem(OrderItem(1, 159.99F, 1))
        order.addItem(OrderItem(2, 113.99F, 2))
        order.addItem(OrderItem(1, 1500.00F, 1))
        return order
    }
}
