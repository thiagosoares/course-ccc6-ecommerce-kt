package br.com.tfsoares.courses.branas.ecommerce.infra.repository.spring.jpa.domain

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "order_item", schema = "store")
class OrderItemDomain(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "order_id_seq")
    @SequenceGenerator(name = "sequenceGenerator")
    var id: Long? = null,

    @ManyToOne
    @field:NotNull
    @JoinColumn(name = "id_order")
    var order: OrderDomain,

    @ManyToOne
    @field:NotNull
    @field:Size(min = 1, max = 50)
    @JoinColumn(name = "id_item")
    var item: ItemDomain,

    @field:NotNull
    @field:Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    val price: Long,

    @field:NotNull
    @field:Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    val quantity: Long
)

//    @Embeddable
//    class OrderItemIdDomain(
//        @OneToOne
//        @field:NotNull
//        @JoinColumn(name = "id_order")
//        var order: OrderDomain,
//
//        @ManyToOne
//        @field:NotNull
//        @field:Size(min = 1, max = 50)
//        @JoinColumn(name = "id_item")
//        var item: ItemDomain
//    ) : java.io.Serializable
// }
