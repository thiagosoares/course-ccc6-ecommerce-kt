package br.com.tfsoares.courses.branas.ecommerce.application.controller

import br.com.tfsoares.courses.branas.ecommerce.application.service.OrderSearchService
import br.com.tfsoares.courses.branas.ecommerce.application.service.PlaceOrderCreateService
import br.com.tfsoares.courses.branas.ecommerce.domain.outbound.OrderOutput
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/order")
class PlaceOrderController(
    val orderSearchService: OrderSearchService,
    val placeOrderCreateService: PlaceOrderCreateService
) {

    @GetMapping("/{cpf}")
    fun findAll(cpf: String): List<OrderOutput> {
        return orderSearchService.findAllByCpf(cpf)
    }
}
