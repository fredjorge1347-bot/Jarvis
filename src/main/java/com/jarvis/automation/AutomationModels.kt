package com.jarvis.automation

data class AutomationFlow(val id: String, val name: String, val triggers: List<Trigger>, val conditions: List<Condition>, val actions: List<Action>)
sealed interface Trigger { data class Cron(val expression: String): Trigger; data class Webhook(val path: String): Trigger; data class DeviceEvent(val type: String): Trigger }
sealed interface Condition { data class PermissionGranted(val permission: String): Condition; data class VariableEquals(val name: String, val value: String): Condition }
sealed interface Action { data class Speak(val text: String): Action; data class HttpWebhook(val url: String, val body: String): Action; data class LaunchApp(val packageName: String): Action }
