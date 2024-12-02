package com.chromamon.analysis.duval.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "duval_diagnostic_results", schema = "analysis")
public class DiagnosticData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transformer_id", nullable = false)
    private String transformerId;

    @Column(name = "method", nullable = false)
    private String method;

    @Column(name = "result", nullable = false)
    private String result;

    @Column(name = "analysis_date", nullable = false)
    private Date analysisDate;

    @Column(name = "document_id", nullable = false)
    private String documentId;

    public DiagnosticData(String transformerId, String method, String result, Date analysisDate, String documentId) {
        this.transformerId = transformerId;
        this.method = method;
        this.result = result;
        this.analysisDate = analysisDate;
        this.documentId = documentId;
    }
}