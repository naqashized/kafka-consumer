package com.techphile.producers.scheduler

import com.techphile.producers.dto.EventRequest
import com.techphile.producers.dto.EventType
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.Instant

@Component
@EnableScheduling
class EventProducer(val kafkaTemplate: KafkaTemplate<Object, EventRequest>) {

    companion object{
        val logger: Logger = LoggerFactory.getLogger(KafkaHandler::class.java)
    }

    @Scheduled(fixedRate = 60000)
    fun produce(){
        val time = Instant.now()
        logger.info("Passing new event at $time")
        val event = EventRequest(EventType.IOT, "Please receive new IOT message at $time")
        kafkaTemplate.send("requests", event)
    }
}