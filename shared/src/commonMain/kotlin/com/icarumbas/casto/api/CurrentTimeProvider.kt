package com.icarumbas.casto.api

import kotlinx.datetime.Clock

interface CurrentTimeProvider {
    fun getCurrentTimeMils(): Long
}

class DateTimeCurrentTimeProvider : CurrentTimeProvider {

    override fun getCurrentTimeMils(): Long {
        return Clock.System.now().toEpochMilliseconds()
    }
}