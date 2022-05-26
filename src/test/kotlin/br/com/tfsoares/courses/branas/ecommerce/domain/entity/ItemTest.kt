package br.com.tfsoares.courses.branas.ecommerce.domain.entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ItemTest {

    @Test
    fun `GIVEN a valid item, WHEN get volume, THEN a valid volume`() {
        val item = getItem()
        assertThat(item.getVolume()).isEqualTo(0.5)
    }

    @Test
    fun `GIVEN a valid item, WHEN get density, THEN a valid density`() {
        val item = getItem()
        assertThat(item.getDensity()).isEqualTo(600.0)
    }

    private fun getItem() = Item(1, "Motorcycle", 9000.0, Dimension(100, 100, 50), 300.0)
}
