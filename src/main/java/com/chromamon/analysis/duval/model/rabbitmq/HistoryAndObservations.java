package com.chromamon.analysis.duval.model.rabbitmq;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HistoryAndObservations {
    @JsonProperty("last_maintenance_date")
    private String lastMaintenanceDate;
    @JsonProperty("maintenance_done")
    private String maintenanceDone;
    @JsonProperty("observations")
    private String observations;
}