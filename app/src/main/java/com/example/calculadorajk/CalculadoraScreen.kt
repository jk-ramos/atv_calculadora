package com.example.calculadorajk
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculadorajk.ui.theme.CorBotaoCinza
import com.example.calculadorajk.ui.theme.CorBotaoEscuro
import com.example.calculadorajk.ui.theme.CorBotaoRosa
import com.example.calculadorajk.ui.theme.CorFundo
import com.example.calculadorajk.ui.theme.CorTexto
@Composable
fun CalculadoraScreen(viewModel: CalculadoraViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CorFundo)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "${uiState.numero1} ${uiState.operacao ?: ""} ${uiState.numero2}",
                color = Color.Gray,
                fontSize = 24.sp
            )
            Text(
                text = uiState.resultadoVisor,
                color = if (uiState.erro != null) Color.Red else CorBotaoRosa,
                fontSize = 64.sp,
                fontWeight = FontWeight.Light,
                maxLines = 1
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        val espacamento = 8.dp
        Column(
            modifier = Modifier.weight(2.5f).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(espacamento)
        ) {
            Row(modifier = Modifier.weight(1f).fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(espacamento)) {
                BotaoCalc("AC", modifier = Modifier.weight(1f), corFundo = CorBotaoCinza) { viewModel.onAction(CalculadoraAction.OnLimparClick) }
                BotaoCalc("+/-", modifier = Modifier.weight(1f), corFundo = CorBotaoCinza) { /* Não faz nada */ }
                BotaoCalc("%", modifier = Modifier.weight(1f), corFundo = CorBotaoCinza) {  /* Não faz nada */ }
                BotaoCalc("/", modifier = Modifier.weight(1f), corFundo = CorBotaoRosa) { viewModel.onAction(CalculadoraAction.OnOperacaoClick("/")) }
            }
            Row(modifier = Modifier.weight(1f).fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(espacamento)) {
                BotaoCalc("7", modifier = Modifier.weight(1f)) { viewModel.onAction(CalculadoraAction.OnDigitoClick("7")) }
                BotaoCalc("8", modifier = Modifier.weight(1f)) { viewModel.onAction(CalculadoraAction.OnDigitoClick("8")) }
                BotaoCalc("9", modifier = Modifier.weight(1f)) { viewModel.onAction(CalculadoraAction.OnDigitoClick("9")) }
                BotaoCalc("*", modifier = Modifier.weight(1f), corFundo = CorBotaoRosa) { viewModel.onAction(CalculadoraAction.OnOperacaoClick("*")) }
            }
            Row(modifier = Modifier.weight(1f).fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(espacamento)) {
                BotaoCalc("4", modifier = Modifier.weight(1f)) { viewModel.onAction(CalculadoraAction.OnDigitoClick("4")) }
                BotaoCalc("5", modifier = Modifier.weight(1f)) { viewModel.onAction(CalculadoraAction.OnDigitoClick("5")) }
                BotaoCalc("6", modifier = Modifier.weight(1f)) { viewModel.onAction(CalculadoraAction.OnDigitoClick("6")) }
                BotaoCalc("-", modifier = Modifier.weight(1f), corFundo = CorBotaoRosa) { viewModel.onAction(CalculadoraAction.OnOperacaoClick("-")) }
            }
            Row(modifier = Modifier.weight(1f).fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(espacamento)) {
                BotaoCalc("1", modifier = Modifier.weight(1f)) { viewModel.onAction(CalculadoraAction.OnDigitoClick("1")) }
                BotaoCalc("2", modifier = Modifier.weight(1f)) { viewModel.onAction(CalculadoraAction.OnDigitoClick("2")) }
                BotaoCalc("3", modifier = Modifier.weight(1f)) { viewModel.onAction(CalculadoraAction.OnDigitoClick("3")) }
                BotaoCalc("+", modifier = Modifier.weight(1f), corFundo = CorBotaoRosa) { viewModel.onAction(CalculadoraAction.OnOperacaoClick("+")) }
            }
            Row(modifier = Modifier.weight(1f).fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(espacamento)) {
                BotaoCalc("0", modifier = Modifier.weight(2f)) { viewModel.onAction(CalculadoraAction.OnDigitoClick("0")) }
                BotaoCalc(".", modifier = Modifier.weight(1f)) { viewModel.onAction(CalculadoraAction.OnDigitoClick(".")) }
                BotaoCalc("=", modifier = Modifier.weight(1f), corFundo = CorBotaoRosa) { viewModel.onAction(CalculadoraAction.OnCalcularClick) }
            }
        }
    }
}

@Composable
fun BotaoCalc(
    texto: String,
    modifier: Modifier = Modifier,
    corFundo: Color = CorBotaoEscuro,
    corTexto: Color = CorTexto,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxHeight(),
        shape = RoundedCornerShape(32.dp),
        colors = ButtonDefaults.buttonColors(containerColor = corFundo, contentColor = corTexto)
    ) {
        Text(text = texto, fontSize = 28.sp)
    }
}