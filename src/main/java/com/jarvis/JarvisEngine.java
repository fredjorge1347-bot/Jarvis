package com.jarvis;

public final class JarvisEngine {
    private JarvisEngine() {}

    public static String respond(String input) {
        String lower = input.toLowerCase(java.util.Locale.ROOT);
        if (lower.contains("minecraft") || lower.contains("netherita") || lower.contains("farm de ferro")) {
            return "Claro. Para Minecraft, diga se é Java ou Bedrock e eu explico o passo a passo sem sair do jogo.";
        }
        if (lower.contains("tela")) {
            return "Posso analisar a tela quando você autorizar a captura pelo Android. Essa permissão sempre depende de consentimento.";
        }
        if (lower.contains("agenda")) {
            return "Posso ajudar a organizar agenda, tarefas e rotinas quando você conectar suas contas autorizadas.";
        }
        return "Estou online. Posso memorizar comandos, responder por voz e servir como base do seu assistente pessoal no Android.";
    }
}
