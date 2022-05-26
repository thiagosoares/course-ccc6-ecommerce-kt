package br.com.tfsoares.courses.branas.ecommerce.infra.repository.spring.mapper

import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface OrderJpaMapper {

//    @Mappings(
//        Mapping(target = "coupon", ignore = true),
//        Mapping(target = "freight", ignore = true),
//        Mapping(target = "items", ignore = true)
//    )
//    fun toOrder(orderJpa: OrderJpa): Order

//    fun toOrder(orderJpa: List<OrderJpa>): List<Order>
}
