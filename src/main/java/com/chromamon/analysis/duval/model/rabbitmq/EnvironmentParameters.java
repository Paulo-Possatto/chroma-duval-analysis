package com.chromamon.analysis.duval.model.rabbitmq;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EnvironmentParameters {
    @JsonProperty("environment_temperature")
    private Integer environmentTemperature;
    @JsonProperty("environment_humidity")
    private Integer environmentHumidity;
    @JsonProperty("atmospheric_pressure")
    private Double atmosphericPressure;
}