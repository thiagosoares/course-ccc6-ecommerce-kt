package br.com.tfsoares.courses.branas.ecommerce.infra.repository.pg

import br.com.tfsoares.courses.branas.ecommerce.domain.entity.Item
import br.com.tfsoares.courses.branas.ecommerce.domain.repository.ItemRepository
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import javax.persistence.EntityManager

@Component
@Profile("!test & pg")
class ItemPgRepository(val em: EntityManager) : ItemRepository {

    override fun save(item: Item) {
        TODO("Not yet implemented")
    }

    override fun findById(idItem: Int): Item {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Item> {
        TODO("Not yet implemented")
    }

    override fun countAll(): Long {
        TODO("Not yet implemented")
    }
}
