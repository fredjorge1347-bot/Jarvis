plugins {
    id("com.android.application") version "8.11.0"
}

val hasReleaseSigning = providers.gradleProperty("JARVIS_KEYSTORE_FILE").isPresent

android {
    namespace = "com.jarvis"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.jarvis"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0.0"
    }

    signingConfigs {
        create("release") {
            val storeFilePath = providers.gradleProperty("JARVIS_KEYSTORE_FILE").orNull
            if (storeFilePath != null) {
                storeFile = file(storeFilePath)
                storePassword = providers.gradleProperty("JARVIS_KEYSTORE_PASSWORD").orNull
                keyAlias = providers.gradleProperty("JARVIS_KEY_ALIAS").orNull
                keyPassword = providers.gradleProperty("JARVIS_KEY_PASSWORD").orNull
            }
        }
    }

    buildTypes {
        release {
            isDebuggable = false
            isMinifyEnabled = false
            if (hasReleaseSigning) {
                signingConfig = signingConfigs.getByName("release")
            }
        }
    }
}

base {
    archivesName.set("Jarvis")
}
