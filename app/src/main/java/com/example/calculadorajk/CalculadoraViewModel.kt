package com.example.calculadorajk
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

sealed interface CalculadoraAction {
    data class OnDigitoClick(val digito: String) : CalculadoraAction
    data class OnOperacaoClick(val operacao: String) : CalculadoraAction
    object OnCalcularClick : CalculadoraAction
    object OnLimparClick : CalculadoraAction
}
data class CalculadoraUiState(
    val numero1: String = "",
    val numero2: String = "",
    val operacao: String? = null,
    val resultadoVisor: String = "0",
    val erro: String? = null
)
class CalculadoraViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CalculadoraUiState())
    val uiState: StateFlow<CalculadoraUiState> = _uiState.asStateFlow()

    fun onAction(action: CalculadoraAction) {
        when (action) {
            is CalculadoraAction.OnDigitoClick -> inserirDigito(action.digito)
            is CalculadoraAction.OnOperacaoClick -> selecionarOperacao(action.operacao)
            CalculadoraAction.OnCalcularClick -> calcular()
            CalculadoraAction.OnLimparClick -> limpar()
        }
    }
    private fun inserirDigito(digito: String) {
        _uiState.update { estado ->
            if (estado.operacao == null) {
                val novoNum = seLimparZero(estado.numero1) + digito
                estado.copy(numero1 = novoNum, resultadoVisor = novoNum, erro = null)
            } else {
                val novoNum = seLimparZero(estado.numero2) + digito
                estado.copy(numero2 = novoNum, resultadoVisor = novoNum, erro = null)
            }
        }
    }
    private fun selecionarOperacao(op: String) {
        _uiState.update { it.copy(operacao = op) }
    }
    private fun calcular() {
        _uiState.update { estado ->
            // Transformar as Strings para Double
            val valor1 = estado.numero1.toDoubleOrNull()
            val valor2 = estado.numero2.toDoubleOrNull()

            // Ver se os números são válidos e se tem uma operação
            if (valor1 != null && valor2 != null && estado.operacao != null) {

                val calculadora = Calculadora()
                calculadora.num01 = valor1
                calculadora.num02 = valor2

                val resultado = when (estado.operacao) {
                    "+" -> calculadora.somar()
                    "-" -> calculadora.subtrair()
                    "*" -> calculadora.multiplicar()
                    "/" -> calculadora.dividir()
                    else -> null
                }

                // Tratamento de erro e formatação pra volta como String
                if (resultado == null) {
                    estado.copy(erro = "Erro", resultadoVisor = "Erro")
                } else {

                    val formatado = if (resultado % 1 == 0.0) resultado.toLong().toString() else resultado.toString()
                    estado.copy(
                        numero1 = formatado,
                        numero2 = "",
                        operacao = null,
                        resultadoVisor = formatado,
                        erro = null
                    )
                }
            } else {
                estado
            }
        }
    }

    private fun limpar() {
        _uiState.value = CalculadoraUiState()
    }
    private fun seLimparZero(valor: String): String {
        return if (valor == "0") "" else valor
    }
}