package com.jarvis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jarvis.domain.JarvisEngine
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val vm: JarvisViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); setContent { JarvisApp(vm) } }
}

@HiltViewModel
class JarvisViewModel @Inject constructor(private val engine: JarvisEngine) : ViewModel() {
    private val _state = MutableStateFlow(JarvisState()); val state = _state.asStateFlow()
    fun ask(text: String) = viewModelScope.launch { _state.value = _state.value.copy(transcript = text, answer = engine.respond(text)) }
}

data class JarvisState(val transcript: String = "", val answer: String = "JARVIS inicializado. Autorize voz, overlay e visão quando quiser ativar a camada inteligente.")

@Composable fun JarvisApp(vm: JarvisViewModel) { val state by vm.state.collectAsState(); MaterialTheme(colorScheme = darkColorScheme(primary = Color.Cyan, secondary = Color(0xFF7C4DFF), background = Color(0xFF050814))) { JarvisDashboard(state, vm::ask) } }

@Composable fun JarvisDashboard(state: JarvisState, onAsk: (String) -> Unit) {
 var input by remember { mutableStateOf("") }
 Box(Modifier.fillMaxSize().background(Brush.radialGradient(listOf(Color(0xFF12214A), Color(0xFF050814))))) {
  HologramCore(Modifier.fillMaxSize())
  Column(Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
   Text("JARVIS", color = Color.White, fontSize = 42.sp, fontWeight = FontWeight.ExtraBold)
   Text("Sistema operacional pessoal de IA", color = Color.Cyan.copy(alpha = .85f))
   GlassCard { Column(Modifier.padding(18.dp)) { Text("Núcleo de IA", color = Color.Cyan, fontWeight = FontWeight.Bold); Text(state.answer, color = Color.White, lineHeight = 22.sp) } }
   Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) { StatusChip("Voz", "pronto"); StatusChip("Memória", "ativa"); StatusChip("Overlay", "permissão") }
   OutlinedTextField(input, { input = it }, modifier = Modifier.fillMaxWidth(), label = { Text("Comando para o JARVIS") })
   Button(onClick = { if (input.isNotBlank()) onAsk(input) }, modifier = Modifier.fillMaxWidth()) { Text("Executar comando") }
   LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) { items(6) { i -> GlassCard { Text(listOf("Agenda inteligente", "Automações", "Casa conectada", "Copiloto de jogos", "Visão de tela", "Agentes especializados")[i], Modifier.padding(16.dp), color = Color.White) } } }
  }
 }
}

@Composable fun StatusChip(name: String, status: String) { AssistChip(onClick = {}, label = { Text("$name: $status") }) }
@Composable fun GlassCard(content: @Composable () -> Unit) { Surface(color = Color.White.copy(alpha = .08f), shape = RoundedCornerShape(24.dp), border = androidx.compose.foundation.BorderStroke(1.dp, Color.Cyan.copy(alpha = .25f)), content = content) }
@Composable fun HologramCore(modifier: Modifier) { val t by rememberInfiniteTransition(label="core").animateFloat(0f, 360f, infiniteRepeatable(tween(9000, easing = LinearEasing)), label="spin"); Canvas(modifier) { val c = center; for (r in listOf(80f, 150f, 230f)) drawCircle(Color.Cyan.copy(alpha=.12f), r, c, style=Stroke(2f)); rotate(t, c) { drawLine(Color.Cyan.copy(alpha=.35f), c, Offset(size.width, c.y), strokeWidth=3f) } } }
