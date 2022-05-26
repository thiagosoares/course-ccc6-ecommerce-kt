package br.com.tfsoares.courses.branas.ecommerce.domain.entity

import br.com.tfsoares.courses.branas.ecommerce.errors.InvalidCouponException
import java.text.DecimalFormat
import java.time.LocalDateTime

data class Coupon(
    val code: String,
    private val percentage: Int,
    private val expireDate: LocalDateTime = LocalDateTime.now()
) {

    companion object {
        private val FORMATTER = DecimalFormat("#.##")
        private const val ERROR_INVALID_COUPON = "Invalid coupon"
    }

    init {
        validate(percentage)
    }

    private fun validate(percentage: Int) {
        this.validatePercentage(percentage)
    }

    private fun validatePercentage(percentage: Int) {
        if (percentage < 0) throw InvalidCouponException(ERROR_INVALID_COUPON)
    }

    fun applyDiscount(value: Double): Double {
        return FORMATTER.format(value * ((100 - percentage).toFloat().div(100))).toDouble()
    }

    fun isExpired(date: LocalDateTime): Boolean {
        return expireDate.isBefore(date)
    }
}
