package br.com.tfsoares.courses.branas.ecommerce.application.service

import br.com.tfsoares.courses.branas.ecommerce.domain.entity.Coupon
import br.com.tfsoares.courses.branas.ecommerce.domain.entity.Order
import br.com.tfsoares.courses.branas.ecommerce.domain.inbound.NewOrderDto
import br.com.tfsoares.courses.branas.ecommerce.infra.repository.CouponRepositoryMemory
import br.com.tfsoares.courses.branas.ecommerce.infra.repository.ItemRepositoryMemory
import br.com.tfsoares.courses.branas.ecommerce.infra.repository.OrderRepositoryMemory
import br.com.tfsoares.courses.branas.ecommerce.utils.ItemMocks.Companion.bootsItem
import br.com.tfsoares.courses.branas.ecommerce.utils.ItemMocks.Companion.bootsNewItem
import br.com.tfsoares.courses.branas.ecommerce.utils.ItemMocks.Companion.helmetItem
import br.com.tfsoares.courses.branas.ecommerce.utils.ItemMocks.Companion.helmetNewItem
import br.com.tfsoares.courses.branas.ecommerce.utils.MockData.Companion.CPF_1
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class PlaceOrderCreateServiceTest {

    @Test
    fun `GIVEN, a empty order, WHEN save order, THEN save the order`() {

        val orderRepository = OrderRepositoryMemory()
        val itemRepository = ItemRepositoryMemory()
        val couponRepository = CouponRepositoryMemory()
        val orderService = PlaceOrderCreateService(itemRepository, orderRepository, couponRepository)

        val order = NewOrderDto(CPF_1, emptyList())
        val orderDto = orderService.save(order)

        assertThat(orderRepository.countAll()).isEqualTo(1)
        assertThat(itemRepository.countAll()).isEqualTo(0)

        assertThat(orderDto.code).isEqualTo("202200000001")
        assertThat(orderDto.total).isEqualTo(0.0)
    }

    @Test
    fun `GIVEN, a basic order, WHEN save order, THEN save the order`() {

        val orderRepository = OrderRepositoryMemory()
        val itemRepository = ItemRepositoryMemory()
        itemRepository.save(bootsItem)
        itemRepository.save(helmetItem)
        val couponRepository = CouponRepositoryMemory()
        val orderService = PlaceOrderCreateService(itemRepository, orderRepository, couponRepository)

        val order = NewOrderDto(CPF_1, listOf(helmetNewItem, bootsNewItem), null, LocalDateTime.of(2020, 1, 1, 10, 0, 0))
        val orderDto = orderService.save(order)

        assertThat(orderRepository.countAll()).isEqualTo(1)
        assertThat(itemRepository.countAll()).isEqualTo(2)

        assertThat(orderDto.code).isEqualTo("202200000001")
        assertThat(orderDto.total).isEqualTo(1150.5)
    }

    @Test
    fun `GIVEN, a basic order, WHEN save order on non empty, THEN save the order`() {

        val orderRepository = OrderRepositoryMemory()
        orderRepository.save(Order(CPF_1))
        val itemRepository = ItemRepositoryMemory()
        itemRepository.save(bootsItem)
        itemRepository.save(helmetItem)
        val couponRepository = CouponRepositoryMemory()
        val orderService = PlaceOrderCreateService(itemRepository, orderRepository, couponRepository)

        val order = NewOrderDto(CPF_1, listOf(helmetNewItem, bootsNewItem), null, LocalDateTime.of(2020, 1, 1, 10, 0, 0))
        val orderDto = orderService.save(order)

        assertThat(orderRepository.countAll()).isEqualTo(2)
        assertThat(itemRepository.countAll()).isEqualTo(2)

        assertThat(orderDto.code).isEqualTo("202200000002")
        assertThat(orderDto.total).isEqualTo(1150.5)
    }

    @Test
    fun `GIVEN, a order with discount, WHEN save order, THEN save the order`() {

        val orderRepository = OrderRepositoryMemory()
        val itemRepository = ItemRepositoryMemory()
        itemRepository.save(bootsItem)
        itemRepository.save(helmetItem)
        val couponRepository = CouponRepositoryMemory()
        couponRepository.save(Coupon("20OFF", 20))

        val orderService = PlaceOrderCreateService(itemRepository, orderRepository, couponRepository)

        val order = NewOrderDto(
            cpf = CPF_1,
            orderItems = listOf(helmetNewItem, bootsNewItem),
            coupon = "20OFF",
            date = LocalDateTime.of(2020, 1, 1, 10, 0, 0)
        )
        val orderDto = orderService.save(order)

        assertThat(orderRepository.countAll()).isEqualTo(1)
        assertThat(itemRepository.countAll()).isEqualTo(2)

        assertThat(orderDto.code).isEqualTo("202200000001")
        assertThat(orderDto.total).isEqualTo(932.4)
    }

    @Test
    fun `GIVEN, order, WHEN save order, THEN calculate the freight`() {

        val orderRepository = OrderRepositoryMemory()
        val itemRepository = ItemRepositoryMemory()
        itemRepository.save(bootsItem)
        itemRepository.save(helmetItem)
        val couponRepository = CouponRepositoryMemory()
        val orderService = PlaceOrderCreateService(itemRepository, orderRepository, couponRepository)

        val order = NewOrderDto(CPF_1, listOf(helmetNewItem, bootsNewItem), null, LocalDateTime.of(2020, 1, 1, 10, 0, 0))
        orderService.save(order)

        assertThat(orderRepository.countAll()).isEqualTo(1)
        assertThat(itemRepository.countAll()).isEqualTo(2)
        assertThat(orderRepository.findAll()[0].freight).isEqualTo(60.0)
    }
}
