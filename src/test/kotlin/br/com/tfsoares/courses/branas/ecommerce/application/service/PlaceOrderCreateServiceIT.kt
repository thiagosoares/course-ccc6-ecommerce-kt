package br.com.tfsoares.courses.branas.ecommerce.application.service

import br.com.tfsoares.courses.branas.ecommerce.IntegrationTest
import br.com.tfsoares.courses.branas.ecommerce.infra.repository.pg.CouponPgRepository
import br.com.tfsoares.courses.branas.ecommerce.infra.repository.pg.ItemPgRepository
import br.com.tfsoares.courses.branas.ecommerce.infra.repository.spring.OrderDataRepository
import br.com.tfsoares.courses.branas.ecommerce.infra.repository.spring.jpa.OrderJpaRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import javax.persistence.EntityManager

@IntegrationTest
class PlaceOrderCreateServiceIT {

    @Autowired
    private lateinit var em: EntityManager

    @Autowired
    private lateinit var orderJpaRepository: OrderJpaRepository

    @Test
    fun `GIVEN, `() {

        val list = em.createNativeQuery("SELECT * FROM store.coupon").resultList
        list.forEach {
            println(it)
        }

//        val orderRepository = OrderPgRepository(em)
//        val itemRepository = ItemPgRepository(em)
//        val couponRepository = CouponPgRepository(em)
//        val orderService = PlaceOrderService(itemRepository, orderRepository, couponRepository)
//
//        orderRepository.findAll().forEach {
//            println(it.cpf)
//            println(it.code)
//            println(it.coupon)
// //            println(it.getFreight())
//        }

//        val order = NewOrderDto(CPF_1, emptyList())
//        val orderDto = orderService.save(order)
//
//        assertThat(orderRepository.countAll()).isEqualTo(1)
//        assertThat(itemRepository.countAll()).isEqualTo(0)
//
//        assertThat(orderDto.code).isEqualTo("202200000001")
//        assertThat(orderDto.total).isEqualTo(0.1)
    }

    @Test
    fun `GIVEN, a empty order, WHEN save order, THEN save the order`() {
        val orderRepository = OrderDataRepository(orderJpaRepository)
        val itemRepository = ItemPgRepository(em)
        val couponRepository = CouponPgRepository(em)
        val orderService = PlaceOrderCreateService(itemRepository, orderRepository, couponRepository)

        orderRepository.findAll().forEach {
            println(it.cpf)
            println(it.code)
            println(it.coupon)
//            println(it.getFreight())
            println(">>>")
        }
    }

    /*@Test
    fun `GIVEN, a basic order, WHEN save order, THEN save the order`() {

        val orderRepository = OrderRepositoryMemory()
        val itemRepository = ItemRepositoryMemory()
        itemRepository.save(bootsItem)
        itemRepository.save(helmetItem)
        val couponRepository = CouponRepositoryMemory()
        val orderService = PlaceOrderService(itemRepository, orderRepository, couponRepository)

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
        val orderService = PlaceOrderService(itemRepository, orderRepository, couponRepository)

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

        val orderService = PlaceOrderService(itemRepository, orderRepository, couponRepository)

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
        val orderService = PlaceOrderService(itemRepository, orderRepository, couponRepository)

        val order = NewOrderDto(CPF_1, listOf(helmetNewItem, bootsNewItem), null, LocalDateTime.of(2020, 1, 1, 10, 0, 0))
        orderService.save(order)

        assertThat(orderRepository.countAll()).isEqualTo(1)
        assertThat(itemRepository.countAll()).isEqualTo(2)
        assertThat(orderRepository.findAll()[0].freight.getTotal()).isEqualTo(60.0)
    }*/
}
