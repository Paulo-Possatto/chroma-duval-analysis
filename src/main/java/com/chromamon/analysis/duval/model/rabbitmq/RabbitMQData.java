package com.chromamon.analysis.duval.model.rabbitmq;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RabbitMQData {
    @JsonProperty("transformer_identification")
    private TransformerIdentification transformerIdentification;
    @JsonProperty("chromatography_data")
    private ChromatographyData chromatographyData;
    @JsonProperty("environment_parameters")
    private EnvironmentParameters environmentParameters;
    @JsonProperty("history_and_observations")
    private HistoryAndObservations historyAndObservations;
    @JsonProperty("document_id")
    private String documentId;
}