package br.com.tfsoares.courses.branas.ecommerce.domain.entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FreightTest {

    @Test
    fun `GIVEN valid input, WHEN calculate a freight, THEN get a valid values`() {
        val freight = getFreight()
        val total = freight.getTotal()
        assertThat(total).isEqualTo(260.0)
    }

    @Test
    fun `GIVEN distance less than the minimum, WHEN calculate a freight, THEN get the minimum`() {
        val freight = Freight()
        freight.addItem(Item(3, "Cabo", 30.0, Dimension(10, 10, 10), 0.9), 1)
        val total = freight.getTotal()
        assertThat(total).isEqualTo(10.0)
    }

    @Test
    fun `GIVEN one item, WHEN get unit freight value, THEN get the unitary value`() {
        val unitaryFreight = Freight.calculateUnitaryFreight(Item(3, "Cabo", 30.0, Dimension(10, 10, 10), 2.0), 3)
        assertThat(unitaryFreight).isEqualTo(60.0)
    }

    @Test
    fun `GIVEN one small item, WHEN get unit freight value, THEN get the unitary value`() {
        val unitaryFreight = Freight.calculateUnitaryFreight(Item(3, "Cabo", 30.0, Dimension(10, 10, 10), 0.9), 1)
        assertThat(unitaryFreight).isEqualTo(9.0)
    }

    @Test
    fun `GIVEN one item, WHEN roundMinFreight, THEN get the unitary value`() {
        val unitaryFreight = Freight.calculateUnitaryFreight(Item(3, "Cabo", 30.0, Dimension(10, 10, 10), 2.0), 3)
        val roundedFreight = Freight.roundMinFreight(unitaryFreight)
        assertThat(roundedFreight).isEqualTo(60.0)
    }

    @Test
    fun `GIVEN one small item, WHEN roundMinFreight, THEN get the unitary value`() {
        val unitaryFreight = Freight.calculateUnitaryFreight(Item(3, "Cabo", 30.0, Dimension(10, 10, 10), 0.9), 1)
        val roundedFreight = Freight.roundMinFreight(unitaryFreight)
        assertThat(roundedFreight).isEqualTo(10.0)
    }

    private fun getFreight(): Freight {
        val freight = Freight()
        freight.addItem(Item(1, "Guitarra", 1000.0, Dimension(100, 30, 10), 3.0), 1)
        freight.addItem(Item(2, "Amplificador", 5000.0, Dimension(50, 50, 50), 20.0), 1)
        freight.addItem(Item(3, "Cabo", 30.0, Dimension(10, 10, 10), 1.0), 3)
        return freight
    }
}
