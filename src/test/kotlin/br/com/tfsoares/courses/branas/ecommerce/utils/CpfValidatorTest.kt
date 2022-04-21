package br.com.tfsoares.courses.branas.ecommerce.utils

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach


import org.junit.jupiter.api.Test

class CpfValidatorTest {

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }


    @Test
    fun `GIVEN , WHEN , THEN `() {
        assertThat("1").isEqualTo("1")
    }

    fun expectTrue(cpf: String) {
        assertThat(CpfValidator.validate(cpf)).isTrue
    }

    fun expectFalse(cpf: String) {
        assertThat(CpfValidator.validate(cpf)).isFalse
    }

    @Test
    fun `GIVEN a valid CPF with mask, WHEN validate, THEN assert true`() {
        expectTrue("115.906.360-58")
    }

    @Test
    fun `GIVEN a valid CPF with no mask, WHEN validate, THEN assert true`() {
        expectTrue("11590636058")
    }

    @Test
    fun `GIVEN a valid CPF with first verifier equals 2, WHEN validate, THEN assert true`() {
        expectTrue("252.002.360-02")
    }

    @Test
    fun `GIVEN a valid CPF with first verifier less than 2, WHEN validate, THEN assert true`() {
        expectTrue("862.869.870-10")
    }

    // Errors

    @Test
    fun `GIVEN a blank CPF, WHEN validate, THEN assert false`() {
        expectFalse("")
    }

    @Test
    fun `GIVEN a invalid CPF, WHEN validate, THEN assert false`() {
        expectFalse("123.456.789-00")
    }

    @Test
    fun `GIVEN a invalid CPF with no mask, WHEN validate, THEN assert false`() {
        expectFalse("12345678900")
    }

    @Test
    fun `GIVEN a CPF with invalid verifying digit, WHEN validate, THEN assert false`() {
        expectFalse("115.906.360-00")
    }

    @Test
    fun `GIVEN a CPF with size less than 11, WHEN validate, THEN assert false`() {
        expectFalse("1159063600")
    }

    @Test
    fun `GIVEN a CPF with size EQUALS 13, WHEN validate, THEN assert false`() {
        expectFalse("1150906.3600-00")
    }

    @Test
    fun `GIVEN a CPF with size more than 14, WHEN validate, THEN assert false`() {
        expectFalse("115.906.360.000-00")
    }

    @Test
    fun `GIVE a All 1 CPF, WHEN validate, THEN assert false`() {
        expectFalse("11111111111")
    }

    @Test
    fun `GIVE a invalid CPF with letters, WHEN validate, THEN assert false`() {
        expectFalse("111.111.11X-11")
    }

    @Test
    fun `GIVE a invalid CPF with letters on check digits, WHEN validate, THEN assert false`() {
        expectFalse("115.906.360.000-XY")
    }


}
