# JARVIS Android

JARVIS is a root-level Android app configured to produce a downloadable APK through GitHub Actions. The current implementation intentionally uses only the Android Gradle Plugin and official Android framework APIs so the CI build is small and reliable.

## Implemented foundation

- Root Android application project; there is no nested `:app` module.
- Futuristic native Android dashboard with a custom holographic background.
- Local persistent memory using Android SQLite.
- Foreground TextToSpeech service for spoken responses.
- Android manifest permissions for network, voice, camera, overlay, notifications, and foreground service behavior.
- GitHub Actions workflow that builds Play Store release artifacts (`Jarvis-release.aab` and, when a keystore is configured, `Jarvis-release.apk`).

## Gerar APK

Veja o guia passo a passo em [`BUILDING_APK.md`](BUILDING_APK.md).

## Baixar e instalar

Veja como obter o APK e instalar no Android em [`INSTALL_ANDROID.md`](INSTALL_ANDROID.md).

## Estou só no celular

Se você quer instalar usando apenas o telefone, siga [`MOBILE_ONLY.md`](MOBILE_ONLY.md).

## APK pronto via GitHub Actions

O workflow `.github/workflows/android-apk.yml` compila o pacote seguro `Jarvis-release.aab` para Play Store e, quando uma keystore é configurada, também publica `Jarvis-release.apk`.


## Segurança / Play Store

Veja [`PLAY_STORE_SECURITY.md`](PLAY_STORE_SECURITY.md) para entender como gerar artefatos mais seguros, reduzir alertas do Play Protect e publicar pela Play Console.
