package br.com.tfsoares.courses.branas.ecommerce.application.service

import br.com.tfsoares.courses.branas.ecommerce.domain.entity.Order
import br.com.tfsoares.courses.branas.ecommerce.domain.inbound.NewOrderDto
import br.com.tfsoares.courses.branas.ecommerce.domain.repository.CouponRepository
import br.com.tfsoares.courses.branas.ecommerce.domain.repository.OrderRepository
import br.com.tfsoares.courses.branas.ecommerce.infra.repository.ItemRepositoryMemory
import br.com.tfsoares.courses.branas.ecommerce.utils.MockData.Companion.CPF_1
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlaceOrderCreateServiceMockTest {

    @Test
    fun `GIVEN, a basic order, WHEN save order, THEN save the order - Mock`() {

        val orderRepository = mockk<OrderRepository>()
        val itemRepository = mockk<ItemRepositoryMemory>()
        val couponRepository = mockk<CouponRepository>()
        val orderService = PlaceOrderCreateService(itemRepository, orderRepository, couponRepository)

        every {
            orderRepository.save(any())
        } returns Unit

        every {
            orderRepository.countAll()
        } returns 1

        val order = NewOrderDto(CPF_1, emptyList())
        orderService.save(order)

        val orderSlot = slot<Order>()
        verify(exactly = 1) {
            orderRepository.save(capture(orderSlot))
        }
        assertThat(orderSlot.captured.cpf.cpf).isEqualTo(CPF_1)
    }
}
