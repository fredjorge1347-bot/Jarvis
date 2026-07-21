package com.jarvis.domain

import com.jarvis.data.Memory
import com.jarvis.data.MemoryDao
import javax.inject.Inject

class JarvisEngine @Inject constructor(private val memoryDao: MemoryDao) {
    suspend fun respond(input: String): String {
        val lower = input.lowercase()
        val answer = when {
            "minecraft" in lower || "netherita" in lower || "farm de ferro" in lower -> "Claro. Para Minecraft, posso orientar por etapas sem interromper seu jogo. Diga a versão e se joga Java ou Bedrock para eu ajustar a estratégia."
            "tela" in lower -> "Posso analisar a tela quando você autorizar a captura via MediaProjection. A permissão é sempre explícita e controlada por você."
            "agenda" in lower -> "Posso organizar sua agenda, detectar conflitos e preparar um briefing do dia quando você conectar suas contas."
            else -> "Estou online. Posso conversar, memorizar preferências, executar automações autorizadas e atuar como copiloto do dispositivo."
        }
        if (input.length > 12) memoryDao.insert(Memory(kind = "conversation", content = input, importance = 2))
        return answer
    }
}
