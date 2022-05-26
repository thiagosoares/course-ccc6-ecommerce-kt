package br.com.tfsoares.courses.branas.ecommerce.domain.entity

data class Item(
    private val idItem: Int,
    private val description: String,
    private val price: Double,
    private val dimension: Dimension? = null,
    private val weight: Double? = null
) {

    fun getIdItem() = this.idItem
    fun getPrice() = this.price

    fun getVolume(): Double {
        return dimension?.getVolume() ?: 0.0
    }

    fun getDensity(): Double {
        return if (dimension != null && weight != null) {
            weight / dimension.getVolume()
        } else {
            0.0
        }
    }
}
