package io.github.cbinarycastle.betty.event

class CompositeEventLogger(private val eventLoggers: List<EventLogger>) : EventLogger {

    override fun initialize() {
        eventLoggers.forEach { it.initialize() }
    }

    override fun logEvent(event: Event) {
        eventLoggers.forEach { it.logEvent(event) }
    }
}