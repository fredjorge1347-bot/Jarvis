# Como transformar o JARVIS em APK

Este repositório já contém a estrutura de um app Android. Para gerar um APK instalável, você precisa compilar o app Android na raiz do repositório com Android Studio ou Gradle em uma máquina com o Android SDK instalado.

## 1. Instale as ferramentas oficiais

1. Instale o Android Studio atualizado.
2. Abra **Settings > Build, Execution, Deployment > Build Tools > Gradle** e selecione **JDK 17**.
3. Em **SDK Manager**, instale:
   - Android SDK Platform 36, usado por `compileSdk = 36`.
   - Android SDK Build Tools.
   - Android SDK Platform-Tools.

> Observação: a documentação oficial do Android Gradle Plugin informa que a linha 8.11 usa JDK 17 e suporta API level 36. Por isso, não compile com JDK 25.

## 2. Abra o projeto

1. No Android Studio, escolha **Open**.
2. Selecione a pasta raiz do projeto: `Jarvis`.
3. Aguarde o Gradle sincronizar as dependências.

Se a sincronização falhar por rede, confirme que sua máquina consegue acessar:

- `https://dl.google.com/dl/android/maven2/`
- `https://repo.maven.apache.org/maven2/`
- `https://plugins.gradle.org/m2/`

## 3. Gere um APK de debug

Pelo terminal, dentro da raiz do projeto:

```bash
export JAVA_HOME=/caminho/para/jdk-17
./gradlew assembleDebug
```

Se você não tiver `gradlew` ainda, use o Gradle instalado globalmente uma vez para gerar o wrapper:

```bash
gradle wrapper --gradle-version 8.14.4
./gradlew assembleDebug
```

O APK será criado em:

```text
build/outputs/apk/debug/Jarvis-debug.apk
```

Esse APK é para teste local e pode ser instalado com:

```bash
adb install -r build/outputs/apk/debug/Jarvis-debug.apk
```

## 4. Gere um APK assinado para distribuição

Crie uma chave local, fora do Git:

```bash
keytool -genkeypair -v \
  -keystore jarvis-release.keystore \
  -alias jarvis \
  -keyalg RSA \
  -keysize 4096 \
  -validity 10000
```

Depois configure assinatura de release no Gradle ou use o assistente do Android Studio:

1. **Build > Generate Signed App Bundle / APK**.
2. Selecione **APK**.
3. Escolha a keystore criada.
4. Selecione a variante **release**.

## 5. Permissões no celular

Depois de instalar, algumas funções só funcionam se o usuário autorizar explicitamente no Android:

- Microfone para comandos de voz.
- Notificações para o Foreground Service.
- Sobrepor a outros apps para o overlay flutuante.
- Captura de tela para análise visual via MediaProjection.
- Câmera para recursos de visão.

Essas permissões não podem ser burladas. O Android exige consentimento real do usuário.

## 6. Integrações que precisam de credenciais reais

Para publicar um JARVIS completo, conecte provedores oficiais com contas de desenvolvedor e OAuth:

- IA: OpenAI, Gemini, Anthropic ou provedor escolhido.
- Agenda/email: Google Cloud OAuth e/ou Microsoft Entra.
- Casa inteligente: Home Assistant, Matter, MQTT ou integrações oficiais.
- Voz premium/wake word: SDK licenciado do provedor escolhido.

Sem essas credenciais, o APK compila, mas as integrações externas não terão como acessar serviços reais.
