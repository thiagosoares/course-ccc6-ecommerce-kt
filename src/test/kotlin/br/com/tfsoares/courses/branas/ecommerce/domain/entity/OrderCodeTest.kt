package br.com.tfsoares.courses.branas.ecommerce.domain.entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class OrderCodeTest {

    @Test
    fun `GIVEN a valid sequence, WHEN create a code, THEN generate de correct code`() {
        val code = OrderCode(99, LocalDateTime.of(2020, 1, 1, 10, 0, 0))
        assertThat(code.code).isEqualTo("202000000099")
    }

    @Test
    fun `GIVEN a big sequence, WHEN create a code, THEN generate de correct code`() {
        val code = OrderCode(999999999, LocalDateTime.of(2020, 1, 1, 10, 0, 0))
        assertThat(code.code).isEqualTo("2020999999999")
    }
}
