package io.github.cbinarycastle.macao.event

interface EventLogger {

    fun initialize()

    fun logEvent(event: Event)
}