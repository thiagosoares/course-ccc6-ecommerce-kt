package br.com.tfsoares.courses.branas.ecommerce.domain.entity

import br.com.tfsoares.courses.branas.ecommerce.errors.InvalidCouponException
import br.com.tfsoares.courses.branas.ecommerce.errors.InvalidCpfException
import br.com.tfsoares.courses.branas.ecommerce.utils.ItemMocks
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime

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
        assertThat(order.getTotalOrder()).isEqualTo(6311.49)
    }

    @Test
    fun `GIVEN a valid cpf and 3 items, WHEN get freight, THEN get freight value`() {
        val order = getValidOrder()
        val freight = order.getFreight()
        assertThat(freight).isEqualTo(250.0)
        assertThat(order.getTotalOrder()).isEqualTo(6311.49)
    }

    @Test
    fun `GIVEN a valid cpf and 3 items without dimensions, WHEN get freight, THEN get freight value`() {
        val order = getValidOrderNoFreight()
        val freight = order.getFreight()
        assertThat(freight).isEqualTo(0.0)
        assertThat(order.getTotalOrder()).isEqualTo(6061.49)
    }

    @Test
    fun `GIVEN a order with coupon, WHEN create a Order, THEN create a order`() {
        val order = getValidOrder()
        order.applyCoupon(Coupon("20OFF", 20))
        assertThat(order.cpf.cpf).isEqualTo(VALID_CPF)
        assertThat(order.getOrderSize()).isEqualTo(3)
        assertThat(order.getTotalOrder()).isEqualTo(5099.19)
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
        assertThat(order.getTotalOrder()).isEqualTo(0.0)
    }

    @Test
    fun `GIVEN a valid order with sequence, WHEN create a Order, THEN generate a valid order code`() {
        val order = Order(VALID_CPF, 99, LocalDateTime.of(2020, 1, 1, 10, 0, 0))
        assertThat(order.getOrderCode()).isEqualTo("202000000099")
    }

    @Test
    fun `GIVEN a invalid cpf, WHEN create a Order, THEN throw a error`() {
        assertThrows<InvalidCpfException> {
            Order(INVALID_CPF)
        }
    }

    private fun getValidOrder(): Order {
        val order = Order(VALID_CPF)
        order.addItem(ItemMocks.gloveItem, 1)
        order.addItem(ItemMocks.helmetItem, 1)
        order.addItem(ItemMocks.bootsItem, 2)
        return order
    }

    private fun getValidOrderNoFreight(): Order {
        val order = Order(VALID_CPF)
        order.addItem(ItemMocks.gloveItemNoDimension, 1)
        order.addItem(ItemMocks.helmetItemNoDimension, 1)
        order.addItem(ItemMocks.bootsItemNoDimension, 2)
        return order
    }
}
