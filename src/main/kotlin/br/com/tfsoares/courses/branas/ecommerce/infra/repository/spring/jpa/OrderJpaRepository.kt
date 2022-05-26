package br.com.tfsoares.courses.branas.ecommerce.infra.repository.spring.jpa

import br.com.tfsoares.courses.branas.ecommerce.infra.repository.spring.jpa.domain.OrderDomain
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderJpaRepository : JpaRepository<OrderDomain, Long> {

    fun findAllByCpf(cpf: String): List<OrderDomain>
}
