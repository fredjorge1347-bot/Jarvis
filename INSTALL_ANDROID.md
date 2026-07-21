# Como baixar e instalar o JARVIS no Android

Atualmente este repositório contém o código-fonte do app. Para "baixar o app" no celular, primeiro alguém precisa gerar um arquivo APK a partir do projeto Android.

## Opção 1: baixar o APK pronto de uma release

Quando uma release existir, faça assim:

1. Abra a página do projeto no GitHub.
2. Entre em **Releases**.
3. Baixe o arquivo `jarvis-debug.apk` ou `jarvis-release.apk`.
4. No Android, abra o APK baixado.
5. Se o Android pedir, autorize **Instalar apps desconhecidos** para o navegador ou gerenciador de arquivos usado.
6. Toque em **Instalar**.

> Só instale APKs publicados por uma fonte confiável do projeto. APKs enviados por terceiros podem conter alterações maliciosas.

## Opção 2: gerar o APK no computador e passar para o celular

Se ainda não houver release publicada, gere o APK localmente seguindo `BUILDING_APK.md`.

Depois de gerar o APK, você pode instalar de duas formas:

### Instalar com cabo USB e ADB

```bash
adb install -r build/outputs/apk/debug/Jarvis-debug.apk
```

### Enviar o APK para o celular

1. Copie `build/outputs/apk/debug/Jarvis-debug.apk` para o celular por cabo USB, Google Drive, Nearby Share ou outro método confiável.
2. Abra o arquivo no Android.
3. Autorize a instalação de apps desconhecidos, se necessário.
4. Toque em **Instalar**.

## Opção 3: instalar direto pelo Android Studio

1. Conecte o celular por USB.
2. Ative **Opções do desenvolvedor** no Android.
3. Ative **Depuração USB**.
4. No Android Studio, selecione o aparelho conectado.
5. Clique em **Run**.

O Android Studio vai compilar, instalar e abrir o JARVIS automaticamente.

## Depois de instalar

Para usar os recursos avançados, o Android vai pedir permissões separadas:

- Microfone, para comandos de voz.
- Notificações, para serviço em segundo plano.
- Sobrepor a outros apps, para o holograma flutuante.
- Câmera, para visão computacional.
- Captura de tela, para análise visual com consentimento.

Essas permissões são obrigatórias por segurança do Android e precisam ser aceitas manualmente pelo usuário.
