package com.roach.sleuthinaction

import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import java.util.stream.IntStream

@Service
class TestService(
    private val syncExecution: SyncExecution,
    private val asyncExecution: AsyncExecution,
) {

    fun test(): String {

        val restTemplate: RestTemplate = RestTemplate()
        val linkedMultiValueMap = LinkedMultiValueMap<String, String>()
        linkedMultiValueMap["X-b3-traceId"] = MDC.get("traceId")
        linkedMultiValueMap["X-b3-spanId"] = MDC.get("spanId")

        IntStream.range(0, 10).forEach {
            log.info("test : {}", it)

            asyncExecution.execute {
                restTemplate.exchange<String>(
                    "http://localhost:9090/hello",
                    HttpMethod.POST,
                    HttpEntity<Any>(null, linkedMultiValueMap)
                )
            }
        }

        return "hello"
    }

    companion object {
        private val log = LoggerFactory.getLogger(TestService::class.java)
    }
}
