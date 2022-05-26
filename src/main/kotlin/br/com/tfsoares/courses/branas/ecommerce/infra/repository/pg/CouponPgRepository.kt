package br.com.tfsoares.courses.branas.ecommerce.infra.repository.pg

import br.com.tfsoares.courses.branas.ecommerce.domain.entity.Coupon
import br.com.tfsoares.courses.branas.ecommerce.domain.repository.CouponRepository
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import javax.persistence.EntityManager

@Component
@Profile("!test & pg")
class CouponPgRepository(val em: EntityManager) : CouponRepository {

    override fun findByCode(code: String): Coupon {
        TODO("Not yet implemented")
    }

    override fun save(coupon: Coupon) {
        TODO("Not yet implemented")
    }
}
