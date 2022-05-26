package br.com.tfsoares.courses.branas.ecommerce.infra.repository

import br.com.tfsoares.courses.branas.ecommerce.domain.entity.Coupon
import br.com.tfsoares.courses.branas.ecommerce.domain.repository.CouponRepository
import br.com.tfsoares.courses.branas.ecommerce.errors.OrderItemNotFoundException
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("test")
class CouponRepositoryMemory : CouponRepository {

    private val items = mutableListOf<Coupon>()

    override fun findByCode(code: String) = items.firstOrNull { it.code == code } ?: throw OrderItemNotFoundException()

    override fun save(coupon: Coupon) {
        items.add(coupon)
    }
}
