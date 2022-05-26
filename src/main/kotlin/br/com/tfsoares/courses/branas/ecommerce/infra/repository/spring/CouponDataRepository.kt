package br.com.tfsoares.courses.branas.ecommerce.infra.repository.spring

import br.com.tfsoares.courses.branas.ecommerce.domain.entity.Coupon
import br.com.tfsoares.courses.branas.ecommerce.domain.repository.CouponRepository
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("!test & spring")
class CouponDataRepository : CouponRepository {
    override fun findByCode(code: String): Coupon {
        TODO("Not yet implemented")
    }

    override fun save(coupon: Coupon) {
        TODO("Not yet implemented")
    }
}
