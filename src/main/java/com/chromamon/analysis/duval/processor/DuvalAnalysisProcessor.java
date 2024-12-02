package com.chromamon.analysis.duval.processor;

import com.chromamon.analysis.duval.model.AnalysisData;
import com.chromamon.analysis.duval.model.DiagnosticData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DuvalAnalysisProcessor implements ItemProcessor<AnalysisData, DiagnosticData> {

    @Override
    public DiagnosticData process(AnalysisData data) {
        log.info("Data processing started! - Document ID: {}", data.getDocumentId());
        String result = applyDuvalMethod(data);
        log.info("Data process finished! - Document ID: {}", data.getDocumentId());
        return new DiagnosticData(
                data.getTransformerIdentification(),
                "Duval",
                result,
                data.getAnalysisTimestamp(),
                data.getDocumentId()
        );
    }

    private String applyDuvalMethod(AnalysisData data) {
        double acetylene = data.getC2h2();
        double methane = data.getCh4();
        double ethylene = data.getC2h4();

        double proportionAcetylene, proportionMethane, proportionEthylene;

        double totalGases = acetylene + methane + ethylene;
        if(totalGases == 0){
            System.err.println("The sum of the gases is zero");
            return "Impossible to measure";
        }

        proportionAcetylene = (acetylene/totalGases)*100;
        proportionEthylene = (ethylene/totalGases)*100;
        proportionMethane = (methane/totalGases)*100;


        return checkTriangleValue(proportionAcetylene, proportionMethane, proportionEthylene);
    }

    private String checkTriangleValue(double c2h2, double ch4, double c2h4){
        if (c2h2 > 98) {
            return "PD - Partial Discharges";
        } else if (c2h2 >= 4 && ch4 <= 23 && c2h4 <= 20) {
            return "T1 - Thermal Faults of temperature < 300°C";
        } else if (c2h2 >= 4 && ch4 <= 40 && c2h4 <= 50) {
            return "T2 - Thermal Faults of temperature 300°C < T2 < 700°C";
        } else if (c2h2 >= 15 && ch4 <= 50 && c2h4 > 50) {
            return "T3 - Thermal Faults of temperature > 700°C";
        } else if (ch4 > 23 && c2h4 <= 20) {
            return "D1 - Discharges of Low Energy (Sparks)";
        } else if (ch4 > 23 && c2h4 > 20 && c2h4 <= 50) {
            return "D2 - Discharges of High Energy (Arch)";
        } else if (c2h2 >= 4 && c2h2 <= 15 && ch4 > 23 && c2h4 > 50) {
            return "DT - Electrical and Thermal Fault";
        } else {
            return "Non identified on the Duval triangle";
        }
    }
}