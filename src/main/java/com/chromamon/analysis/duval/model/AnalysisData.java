package com.chromamon.analysis.duval.model;

import com.chromamon.analysis.duval.model.rabbitmq.RabbitMQData;
import lombok.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AnalysisData {

    private String transformerIdentification;
    private String transformerName;
    private String location;
    private Date installationDate;
    private Date analysisTimestamp;
    private Integer h2;
    private Integer co;
    private Integer co2;
    private Integer c2h4;
    private Integer c2h6;
    private Integer ch4;
    private Integer c2h2;
    private Double oilAcidity;
    private Integer oilTemperature;
    private Double oilPressure;
    private Integer environmentTemperature;
    private Integer environmentHumidity;
    private Double atmosphericPressure;
    private Date lastMaintenanceDate;
    private String maintenanceDone;
    private String observations;
    private String documentId;

    public static AnalysisData convertToAnalysisData(RabbitMQData rabbitMq){
        AnalysisData data = new AnalysisData();
        data.setTransformerIdentification(rabbitMq.getTransformerIdentification().getTransformerId());
        data.setTransformerName(rabbitMq.getTransformerIdentification().getTransformerName());
        data.setLocation(rabbitMq.getTransformerIdentification().getLocation());
        data.setInstallationDate(fromStringToDate(rabbitMq.getTransformerIdentification().getInstallationDate(), false));
        data.setAnalysisTimestamp(fromStringToDate(rabbitMq.getChromatographyData().getAnalysisTimestamp(), true));
        data.setH2(rabbitMq.getChromatographyData().getH2());
        data.setCo(rabbitMq.getChromatographyData().getCo());
        data.setCo2(rabbitMq.getChromatographyData().getCo2());
        data.setC2h4(rabbitMq.getChromatographyData().getC2h4());
        data.setC2h6(rabbitMq.getChromatographyData().getC2h6());
        data.setCh4(rabbitMq.getChromatographyData().getCh4());
        data.setC2h2(rabbitMq.getChromatographyData().getC2h2());
        data.setOilAcidity(rabbitMq.getChromatographyData().getOilAcidity());
        data.setOilTemperature(rabbitMq.getChromatographyData().getOilTemperature());
        data.setOilPressure(rabbitMq.getChromatographyData().getOilPressure());
        data.setEnvironmentTemperature(rabbitMq.getEnvironmentParameters().getEnvironmentTemperature());
        data.setEnvironmentHumidity(rabbitMq.getEnvironmentParameters().getEnvironmentHumidity());
        data.setAtmosphericPressure(rabbitMq.getEnvironmentParameters().getAtmosphericPressure());
        data.setLastMaintenanceDate(fromStringToDate(rabbitMq.getHistoryAndObservations().getLastMaintenanceDate(), false));
        data.setMaintenanceDone(rabbitMq.getHistoryAndObservations().getMaintenanceDone());
        data.setObservations(rabbitMq.getHistoryAndObservations().getObservations());
        data.setDocumentId(rabbitMq.getDocumentId());
        return data;
    }

    private static Date fromStringToDate(String string, boolean isTimestamp){
        try {
            SimpleDateFormat formatter = null;
            if(!isTimestamp) {
                formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            } else {
                formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            }
            return formatter.parse(string);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}