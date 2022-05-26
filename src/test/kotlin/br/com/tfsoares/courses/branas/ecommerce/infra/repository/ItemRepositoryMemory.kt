package br.com.tfsoares.courses.branas.ecommerce.infra.repository

import br.com.tfsoares.courses.branas.ecommerce.domain.entity.Item
import br.com.tfsoares.courses.branas.ecommerce.domain.repository.ItemRepository
import br.com.tfsoares.courses.branas.ecommerce.errors.OrderItemNotFoundException
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("test")
class ItemRepositoryMemory : ItemRepository {

    private val items = mutableListOf<Item>()

    override fun save(item: Item) {
        items.add(item)
    }

    override fun findById(idItem: Int) = items.firstOrNull { it.getIdItem() == idItem } ?: throw OrderItemNotFoundException()

    override fun findAll() = items

    override fun countAll() = items.size.toLong()
}
