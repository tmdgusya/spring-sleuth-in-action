package com.roach.sleuthinaction

import org.springframework.stereotype.Component

@Component
class SyncExecution {

    fun execute(
        functionTemplate: () -> Unit
    ) {
        functionTemplate()
    }

}