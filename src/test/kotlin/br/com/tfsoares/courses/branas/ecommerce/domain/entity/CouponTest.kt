package br.com.tfsoares.courses.branas.ecommerce.domain.entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class CouponTest {

    @Test
    fun `GIVEN a valid input, WHEN create a coupon, THEN create a coupon`() {
        val coupon = Coupon("VALE20", 20)
        assertThat(coupon).isNotNull
    }

    @Test
    fun `GIVEN a valid input, WHEN apply discount, THEN calculate the value with discount`() {
        val coupon = Coupon("VALE20", 20)
        assertThat(coupon.applyDiscount(1000.0)).isEqualTo(800.0)
    }

    @Test
    fun `GIVEN a valid date, WHEN check the validity, THEN get valid`() {
        val coupon = Coupon("VALE20", 20, LocalDateTime.parse("2021-03-10T10:00:00"))
        val isExpired = coupon.isExpired(LocalDateTime.parse("2021-03-09T10:00:00"))
        assertThat(isExpired).isEqualTo(false)
    }

    @Test
    fun `GIVEN expired date, WHEN check the validity, THEN get invalid`() {
        val coupon = Coupon("VALE20", 20, LocalDateTime.parse("2021-03-10T10:00:00"))
        val isExpired = coupon.isExpired(LocalDateTime.parse("2021-03-10T10:00:01"))
        assertThat(isExpired).isEqualTo(true)
    }
}
