package br.com.tfsoares.courses.branas.ecommerce.domain.entity

data class Freight(
    private var total: Double = 0.0
) {

    companion object {
        private const val DISTANCE = 1000
        private const val FACTOR = 100
        private const val MIN_FREIGHT = 10.0

        fun calculateUnitaryFreight(item: Item, quantity: Int): Double {
            return calculateFreightValue(item, quantity)
        }

        fun roundMinFreight(total: Double) = if (total > 0 && total < MIN_FREIGHT) MIN_FREIGHT else total

        private fun calculateFreightValue(
            item: Item,
            quantity: Int
        ): Double {
            val freight = item.getVolume() * DISTANCE * (item.getDensity() / FACTOR)
            return freight * quantity
        }
    }

    fun addItem(item: Item, quantity: Int) {
        this.total += calculateFreightValue(item, quantity)
    }

    fun getTotal(): Double {
        return roundMinFreight(this.total)
    }
}
