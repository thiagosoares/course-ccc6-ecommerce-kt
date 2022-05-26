package br.com.tfsoares.courses.branas.ecommerce.infra.repository.spring.jpa.domain

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "item", schema = "store")
class ItemDomain(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "order_id_seq")
    @SequenceGenerator(name = "sequenceGenerator")
    var id: Long? = null,

    @field:NotNull
    @field:Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    var category: String,

    @field:NotNull
    @field:Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    var description: String,

    @field:NotNull
    @field:Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    var price: Double,

    @field:NotNull
    @field:Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    var width: Int,

    @field:NotNull
    @field:Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    var height: Int,

    @field:NotNull
    @field:Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    var length: Int,

    @field:NotNull
    @field:Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    var weight: Int
)
