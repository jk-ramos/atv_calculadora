package com.example.calculadorajk

class Calculadora {
    var num01: Double = 0.0
    var num02: Double = 0.0

    fun somar(): Double {
        return num01 + num02
    }

    fun subtrair(): Double {
        return num01 - num02
    }

    fun multiplicar(): Double {
        return num01 * num02
    }

    // Retorna Double opcional (Double?) para poder tratar a divisão por zero
    fun dividir(): Double? {
        if (num02 == 0.0) return null
        return num01 / num02
    }
}