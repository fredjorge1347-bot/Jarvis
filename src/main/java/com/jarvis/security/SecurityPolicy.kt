package com.jarvis.security

object SecurityPolicy {
    val protectedScopes = setOf("microphone", "camera", "screen_capture", "overlay", "calendar", "email", "smart_home")
    fun requiresExplicitConsent(scope: String) = scope in protectedScopes
}
