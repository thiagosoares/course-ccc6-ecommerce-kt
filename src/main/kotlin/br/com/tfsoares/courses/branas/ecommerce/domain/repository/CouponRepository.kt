package br.com.tfsoares.courses.branas.ecommerce.domain.repository

import br.com.tfsoares.courses.branas.ecommerce.domain.entity.Coupon

interface CouponRepository {

    fun findByCode(code: String): Coupon

    fun save(coupon: Coupon)
}
