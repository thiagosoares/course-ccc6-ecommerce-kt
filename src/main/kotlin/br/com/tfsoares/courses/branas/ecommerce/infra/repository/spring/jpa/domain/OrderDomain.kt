package br.com.tfsoares.courses.branas.ecommerce.infra.repository.spring.jpa.domain

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "order", schema = "store")
class OrderDomain(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "order_id_seq")
    @SequenceGenerator(name = "sequenceGenerator")
    var id: Long? = null,

    @field:NotNull
    @field:Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    var cpf: String,

    @field:NotNull
    @field:Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    var code: String,

    @field:NotNull
    @field:Size(min = 1, max = 50)
    @Column(name = "freight_value", length = 50, unique = true, nullable = false)
    var freightValue: Double,

    @field:NotNull
    @field:Size(min = 1, max = 50)
    @Column(name = "total_order", length = 50, unique = true, nullable = false)
    var totalOrder: Double,

    @ManyToOne
    @field:NotNull
    @field:Size(min = 1, max = 50)
    @JoinColumn(name = "coupon_code")
    val coupon: CouponDomain?,

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    val items: List<OrderItemDomain>
) {
    override fun toString(): String {
        return "OrderDomain(id=$id, cpf='$cpf', code='$code', freightValue=$freightValue, totalOrder=$totalOrder, coupon=$coupon)"
    }
}
