package com.roach.sleuthinaction

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

@EnableAsync
@Configuration
class RoachAsyncThreadExecutorConfig {

    @Bean(name = [ROACH_THREAD_EXECUTOR])
    fun threadPoolTaskExecutor(): ThreadPoolTaskExecutor {
        val threadPoolTaskExecutor = ThreadPoolTaskExecutor()
        threadPoolTaskExecutor.corePoolSize = 5
        threadPoolTaskExecutor.maxPoolSize = 10
        threadPoolTaskExecutor.setQueueCapacity(10)
        threadPoolTaskExecutor.setThreadNamePrefix("roach-")
        threadPoolTaskExecutor.initialize()
        return threadPoolTaskExecutor
    }

    companion object {
        private const val ROACH_THREAD_EXECUTOR = "ROACH_THREAD_EXECUTOR"
    }
}
