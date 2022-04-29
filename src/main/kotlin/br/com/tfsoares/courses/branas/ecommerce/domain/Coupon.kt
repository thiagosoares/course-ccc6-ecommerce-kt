package br.com.tfsoares.courses.branas.ecommerce.domain

import br.com.tfsoares.courses.branas.ecommerce.errors.InvalidCouponException
import java.text.DecimalFormat

data class Coupon(private val code: String, private val percentage: Int) {

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

    fun applyDiscount(value: Float): Float {
        return FORMATTER.format(value * ((100 - percentage).toFloat().div(100))).toFloat()
    }
}
