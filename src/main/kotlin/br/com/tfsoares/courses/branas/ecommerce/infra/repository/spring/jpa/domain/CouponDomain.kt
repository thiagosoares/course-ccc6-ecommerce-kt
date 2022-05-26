package br.com.tfsoares.courses.branas.ecommerce.infra.repository.spring.jpa.domain

import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "coupon", schema = "store")
class CouponDomain(

    @Id
    var code: String,

    @field:NotNull
    @field:Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    var percentage: Int,

    @field:NotNull
    @field:Size(min = 1, max = 50)
    @Column(name = "expiration_date", length = 50, unique = true, nullable = false)
    var expiration_date: LocalDateTime

)
