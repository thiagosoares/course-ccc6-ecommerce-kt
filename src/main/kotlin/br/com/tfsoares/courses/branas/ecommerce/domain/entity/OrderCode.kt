package br.com.tfsoares.courses.branas.ecommerce.domain.entity

import java.time.LocalDateTime

data class OrderCode(
    val code: String
) {

    constructor(
        sequence: Long,
        date: LocalDateTime
    ) : this(generateCode(sequence, date))

    companion object {
        private fun generateCode(sequence: Long, date: LocalDateTime): String {
            return "${date.year}${sequence.toString().padStart(8, '0')}"
        }
    }
}
