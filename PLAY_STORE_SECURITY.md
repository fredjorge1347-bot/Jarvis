# Preparação para Play Store e Play Protect

Este app foi ajustado para reduzir alertas de segurança no Android e se aproximar das exigências da Play Store.

## Mudanças de segurança aplicadas

- O Manifest não solicita permissões perigosas que o app ainda não usa.
- O app não solicita câmera, microfone, overlay, internet ou leitura de arquivos.
- A fala do JARVIS usa `TextToSpeech` dentro da Activity, sem Foreground Service permanente.
- O build de distribuição gera Android App Bundle (`.aab`), formato recomendado para envio à Play Store.
- O workflow aceita uma keystore por GitHub Secrets para gerar APK release assinado quando necessário.

## Por que o Android bloqueia APK manual

Mesmo um APK legítimo pode ser bloqueado quando é:

- APK debug.
- APK não assinado com chave de release.
- APK baixado fora da Play Store.
- App novo sem reputação no Play Protect.
- App que pede permissões sensíveis sem justificativa clara.

Para evitar isso, publique pela Play Console usando o arquivo `Jarvis-release.aab` gerado pelo workflow.

## Secrets para APK release assinado

Configure estes secrets no GitHub se quiser também baixar um APK release assinado:

- `ANDROID_KEYSTORE_BASE64`
- `ANDROID_KEYSTORE_PASSWORD`
- `ANDROID_KEY_ALIAS`
- `ANDROID_KEY_PASSWORD`

Sem esses secrets, o workflow ainda gera o `.aab` para Play Store, mas não publica APK release assinado.
