package core.domain.drug;

import core.domain.patient.PatientDrugHistory;
import core.domain.patient.PatientStatus;

public class Insulin implements IDrug {
    @Override
    public PatientStatus getPatientStatusAfterApplication(PatientStatus currentStatus, PatientDrugHistory drugHistory) {

        if (hasTakenAntibiotic(drugHistory) && isNotDiabeticStatus(currentStatus)) {
            return PatientStatus.Fever;
        }

        return currentStatus;
    }

    @Override
    public DrugName getName() {
        return DrugName.Insulin;
    }

    private boolean isNotDiabeticStatus(PatientStatus currentStatus) {
        return !currentStatus.equals(PatientStatus.Diabetes);
    }

    private boolean hasTakenAntibiotic(PatientDrugHistory drugHistory) {
        return drugHistory.isPresent(DrugName.Antibiotic);
    }
}
