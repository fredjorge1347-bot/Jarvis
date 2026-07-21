plugins {
    id("com.android.application") version "8.11.0"
}

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
}

base {
    archivesName.set("Jarvis")
}
