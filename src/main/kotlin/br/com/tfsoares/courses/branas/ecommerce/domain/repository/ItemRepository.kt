package br.com.tfsoares.courses.branas.ecommerce.domain.repository

import br.com.tfsoares.courses.branas.ecommerce.domain.entity.Item

interface ItemRepository {

    fun save(item: Item)
    fun findById(idItem: Int): Item
    fun findAll(): List<Item>
    fun countAll(): Long
}
