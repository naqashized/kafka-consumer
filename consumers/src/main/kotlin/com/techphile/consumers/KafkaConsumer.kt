package com.techphile.consumers

import com.techphile.consumers.dto.EventRequest
import com.techphile.consumers.dto.EventResponse
import org.apache.kafka.clients.admin.NewTopic
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.config.TopicBuilder
import org.springframework.kafka.support.converter.JsonMessageConverter
import org.springframework.kafka.support.converter.RecordMessageConverter
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper
import org.springframework.kafka.support.mapping.Jackson2JavaTypeMapper
import org.springframework.stereotype.Component

@Component
@KafkaListener(id = "events", topics = ["requests", "responses"])
class KafkaConsumer {
    companion object{
        val logger: Logger = LoggerFactory.getLogger(KafkaHandler::class.java)
    }

    @KafkaHandler
    fun request(request: EventRequest) {
        logger.info("Request Received: $request")
    }

    @KafkaHandler
    fun response(eventResponse: EventResponse) {
        logger.info("Response Received: $eventResponse")
    }

    @Bean
    fun converter(): RecordMessageConverter? {
        val converter = JsonMessageConverter()
        val typeMapper = DefaultJackson2JavaTypeMapper()
        typeMapper.typePrecedence = Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID
        typeMapper.addTrustedPackages("com.sse.events.dto")
        val mappings: MutableMap<String, Class<*>> = HashMap()
        mappings["requests"] = EventRequest::class.java
        mappings["responses"] = EventResponse::class.java
        typeMapper.idClassMapping = mappings
        converter.typeMapper = typeMapper
        return converter
    }

    @Bean
    fun requestTopic(): NewTopic? {
        return TopicBuilder
                .name("requests")
                .partitions(3)
                .replicas(1)
                .build()
    }

    @Bean
    fun responseTopic(): NewTopic? {
        return TopicBuilder
                .name("responses")
                .partitions(3)
                .replicas(1)
                .build()
    }
}