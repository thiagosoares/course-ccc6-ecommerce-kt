package br.com.tfsoares.courses.branas.ecommerce.utils
import br.com.tfsoares.courses.branas.ecommerce.errors.InvalidCpfException

class Cpf(val cpf: String) {

    companion object {
        private const val CPF_LENGTH = 11
        private const val CPF_BODY_LENGTH = 9
    }

    init {
        isValid(cpf)
    }

    private fun isValid(cpf: String) {

        val digits = splitCpfDigits(cpf)
        validateFormat(digits)
        val bodyDigits = getBodyDigits(digits)
        val firstCheckDigit = getCheckDigit(bodyDigits)
        val secondCheckDigit = getCheckDigit(bodyDigits + firstCheckDigit)
        val originalCheckDigit = getOriginalCheckDigits(digits)
        val isInvalid = originalCheckDigit != "$firstCheckDigit$secondCheckDigit"

        if (isInvalid) throw InvalidCpfException("Invalid CPF")
    }

    private fun splitCpfDigits(cpf: String) = unmaskCpf(cpf).chunked(1)

    private fun unmaskCpf(cpf: String) = cpf.replace("[^0-9]".toRegex(), "")

    private fun validateFormat(digits: List<String>) {
        if (digits.size != CPF_LENGTH) {
            throw InvalidCpfException("Invalid CPF size")
        }
        if (digits.all { it == digits[0] }) {
            throw InvalidCpfException("Invalid CPF pattern")
        }
    }

    private fun getBodyDigits(digits: List<String>): List<String> {
        return digits.subList(0, CPF_BODY_LENGTH)
    }

    private fun getCheckDigit(digits: List<String>): String {
        var sumFirstCheckDigit = 0
        val baseMultiplier = digits.size + 1
        for (i in digits.indices) {
            sumFirstCheckDigit += (baseMultiplier - i) * digits[i].toInt()
        }
        val divisionRest = (sumFirstCheckDigit % CPF_LENGTH)
        val digit = if (divisionRest < 2) 0 else CPF_LENGTH - divisionRest
        return digit.toString()
    }

    private fun getOriginalCheckDigits(digits: List<String>): Any {
        return getCheckDigits(digits).joinToString(separator = "")
    }

    private fun getCheckDigits(digits: List<String>): List<String> {
        return digits.subList(CPF_BODY_LENGTH, CPF_LENGTH)
    }
}
