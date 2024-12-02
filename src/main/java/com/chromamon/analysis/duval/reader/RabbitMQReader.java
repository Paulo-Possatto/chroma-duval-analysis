package com.chromamon.analysis.duval.reader;

import com.chromamon.analysis.duval.model.AnalysisData;
import com.chromamon.analysis.duval.model.rabbitmq.RabbitMQData;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Slf4j
@Component
@NoArgsConstructor
public class RabbitMQReader implements ItemReader<AnalysisData> {

    private final Queue<AnalysisData> dataQueue = new LinkedList<>();

    @RabbitListener(queues = "data-ingestion")
    public void receiveMessage(@Payload RabbitMQData data) {
        if (data != null) {
            log.info("Transformer Received: {}", data);
            dataQueue.offer(AnalysisData.convertToAnalysisData(data));
        } else {
            log.error("Received null data");
            throw new RuntimeException("Received null data");
        }
    }

    @Override
    public AnalysisData read() {
        return dataQueue.poll();
    }
}