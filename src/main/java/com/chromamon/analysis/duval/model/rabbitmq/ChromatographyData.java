package com.chromamon.analysis.duval.model.rabbitmq;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChromatographyData {
    @JsonProperty("analysis_timestamp")
    private String analysisTimestamp;
    @JsonProperty("H2")
    private Integer h2;
    @JsonProperty("CO")
    private Integer co;
    @JsonProperty("CO2")
    private Integer co2;
    @JsonProperty("C2H4")
    private Integer c2h4;
    @JsonProperty("C2H6")
    private Integer c2h6;
    @JsonProperty("CH4")
    private Integer ch4;
    @JsonProperty("C2H2")
    private Integer c2h2;
    @JsonProperty("oil_acidity")
    private Double oilAcidity;
    @JsonProperty("oil_temperature")
    private Integer oilTemperature;
    @JsonProperty("oil_pressure")
    private Double oilPressure;
}