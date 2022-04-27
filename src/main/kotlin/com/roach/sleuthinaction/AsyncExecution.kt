package com.roach.sleuthinaction

import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class AsyncExecution {

    @Async
    fun execute(
        functionTemplate: () -> Unit
    ) {
        functionTemplate()
    }
}
