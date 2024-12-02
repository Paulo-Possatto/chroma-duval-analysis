package com.chromamon.analysis.duval.repository;

import com.chromamon.analysis.duval.model.DiagnosticData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosticRepository extends JpaRepository<DiagnosticData, Long> {
}