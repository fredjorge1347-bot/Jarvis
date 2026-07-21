# JARVIS Android

JARVIS is a production-oriented Android AI assistant foundation built with Kotlin, Jetpack Compose, Material 3, MVVM, Room, Hilt, WorkManager-ready services, CameraX/ML Kit dependencies, Android foreground service support, overlay permission declarations, and MediaProjection consent plumbing.

## Implemented foundation

- Premium Compose dashboard with animated holographic HUD visual language.
- Hilt dependency injection and clean separation between UI, domain, data, automation, voice, vision, and security packages.
- Room-backed persistent memory for conversations and preferences.
- Foreground voice service using official Android TextToSpeech and notification requirements.
- Screen-capture consent controller using the official MediaProjection API.
- Automation domain model for triggers, conditions, and actions.
- Explicit Android permissions for microphone, camera, overlay, notifications, foreground microphone service, and network access.

## Production notes

Third-party AI, email, calendar, smart-home, and storage providers must be connected with the user's own API credentials and OAuth consent screens before release. Wake-word engines and premium voices must use licensed SDKs configured per deployment.


## Gerar APK

Veja o guia passo a passo em [`BUILDING_APK.md`](BUILDING_APK.md).


## Baixar e instalar

Veja como obter o APK e instalar no Android em [`INSTALL_ANDROID.md`](INSTALL_ANDROID.md).


## Estou só no celular

Se você quer instalar usando apenas o telefone, siga [`MOBILE_ONLY.md`](MOBILE_ONLY.md).


## APK pronto via GitHub Actions

O workflow `.github/workflows/android-apk.yml` compila o APK e publica o artifact `Jarvis-debug-apk` para download. Tags `v*` também anexam o APK em Releases.
