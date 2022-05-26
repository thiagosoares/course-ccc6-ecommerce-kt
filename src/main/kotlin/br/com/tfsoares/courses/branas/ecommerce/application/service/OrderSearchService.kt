package br.com.tfsoares.courses.branas.ecommerce.application.service

import br.com.tfsoares.courses.branas.ecommerce.domain.outbound.OrderOutput
import org.springframework.stereotype.Service

@Service
class OrderSearchService {

    fun findAllByCpf(cpf: String): List<OrderOutput> {
        return emptyList()
    }
}
