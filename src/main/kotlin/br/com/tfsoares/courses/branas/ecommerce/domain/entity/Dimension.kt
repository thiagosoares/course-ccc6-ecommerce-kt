package br.com.tfsoares.courses.branas.ecommerce.domain.entity

class Dimension(
    private val width: Int,
    private val height: Int,
    private val length: Int
) {
    fun getVolume() = width.toDouble().div(100) * height.toDouble().div(100) * length.toDouble().div(100)
}
