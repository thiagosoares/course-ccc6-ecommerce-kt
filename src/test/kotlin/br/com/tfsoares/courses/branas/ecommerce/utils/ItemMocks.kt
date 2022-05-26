package br.com.tfsoares.courses.branas.ecommerce.utils

import br.com.tfsoares.courses.branas.ecommerce.domain.entity.Dimension
import br.com.tfsoares.courses.branas.ecommerce.domain.entity.Item
import br.com.tfsoares.courses.branas.ecommerce.domain.inbound.NewOrderItem

class ItemMocks {

    companion object {

        val helmetItem = Item(1, "Helmet", 1000.50, Dimension(100, 30, 10), 3.0)
        val gloveItem = Item(2, "Boots", 5000.99, Dimension(50, 50, 50), 20.0)
        val bootsItem = Item(3, "Gloves", 30.0, Dimension(10, 10, 10), 1.0)

        val helmetItemNoDimension = Item(1, "Helmet", 1000.50)
        val gloveItemNoDimension = Item(2, "Boots", 5000.99)
        val bootsItemNoDimension = Item(3, "Gloves", 30.0)

        val helmetNewItem = NewOrderItem(1, 1)
        val gloveNewItem = NewOrderItem(2, 2)
        val bootsNewItem = NewOrderItem(3, 3)
    }
}
