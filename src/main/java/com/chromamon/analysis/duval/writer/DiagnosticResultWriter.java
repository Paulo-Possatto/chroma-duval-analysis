package com.chromamon.analysis.duval.writer;

import com.chromamon.analysis.duval.model.DiagnosticData;
import com.chromamon.analysis.duval.repository.DiagnosticRepository;
import com.chromamon.analysis.duval.repository.MongoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class DiagnosticResultWriter implements ItemWriter<DiagnosticData> {

    @Autowired
    private DiagnosticRepository diagnosticRepository;

    @Autowired
    private MongoRepository mongoRepository;

    @Override
    public void write(Chunk<? extends DiagnosticData> chunk) throws Exception {
        log.info("Saving results into the database!");
        diagnosticRepository.saveAll(chunk);
        for(DiagnosticData data : chunk){
            mongoRepository.updateAnalysisResult(data.getDocumentId(), data);
        }
    }
}