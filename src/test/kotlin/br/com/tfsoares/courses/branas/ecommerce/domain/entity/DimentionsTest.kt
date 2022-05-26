package br.com.tfsoares.courses.branas.ecommerce.domain.entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DimentionsTest {

    @Test
    fun `GIVEN valid inputs, WHEN create a dimension, THEN we hava a Dimension`() {
        val dimension = Dimension(100, 30, 10)
        val volume = dimension.getVolume()
        assertThat(volume).isEqualTo(0.03)
    }
}
