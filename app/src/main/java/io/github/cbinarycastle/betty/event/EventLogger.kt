package io.github.cbinarycastle.betty.event

interface EventLogger {

    fun initialize()

    fun logEvent(event: Event)
}