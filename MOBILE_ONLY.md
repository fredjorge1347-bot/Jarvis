# Como instalar o JARVIS usando somente o celular

Se você está no celular, o ponto principal é: Android instala aplicativos por arquivo APK. Este repositório tem o código-fonte; ele não vira app automaticamente dentro do celular sem um APK já gerado.

## Caminho recomendado para usuário mobile

1. Abra a página do projeto no navegador do celular.
2. Procure a aba **Releases**.
3. Baixe o arquivo APK publicado, por exemplo `jarvis-release.apk`.
4. Abra o APK baixado pela notificação de download ou pelo app **Arquivos**.
5. Se o Android bloquear, toque em **Configurações** e permita **Instalar apps desconhecidos** somente para o navegador ou app de arquivos usado.
6. Volte para o APK e toque em **Instalar**.
7. Depois de instalar, abra **JARVIS** na gaveta de aplicativos.

## Se não existir APK em Releases

Se não houver APK publicado em **Releases**, você não conseguirá instalar só baixando o ZIP do código no celular. O ZIP contém arquivos de programação, não um aplicativo instalável.

Nesse caso, existem três opções:

1. Pedir para alguém com computador gerar o APK e te enviar.
2. Usar um serviço de build remoto, como GitHub Actions, para gerar o APK automaticamente.
3. Usar Android Studio em um computador e seguir `BUILDING_APK.md`.

## O que não fazer

- Não instale APKs recebidos de fontes desconhecidas.
- Não baixe APKs de sites aleatórios que dizem ser o JARVIS.
- Não compartilhe permissões sensíveis se o APK não veio de uma release oficial do projeto.

## Depois de abrir o app

O Android pode pedir permissões separadas para microfone, notificações, câmera, sobreposição em outros apps e captura de tela. Essas permissões são necessárias para voz, overlay flutuante e análise visual, mas devem ser concedidas apenas se você confiar no APK instalado.
