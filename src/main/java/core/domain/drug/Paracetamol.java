package core.domain.drug;

import core.domain.patient.PatientDrugHistory;
import core.domain.patient.PatientStatus;

public class Paracetamol implements IDrug {

    @Override
    public PatientStatus getPatientStatusAfterApplication(PatientStatus currentStatus, PatientDrugHistory drugHistory) {

        if (hasTakenAspirin(drugHistory)) {
            return PatientStatus.Dead;
        }

        if (isFeverStatus(currentStatus)) {
            return PatientStatus.Healthy;
        }

        return currentStatus;
    }

    @Override
    public DrugName getName() {
        return DrugName.Paracetamol;
    }

    private boolean isFeverStatus(PatientStatus currentStatus) {
        return currentStatus == PatientStatus.Fever;
    }

    private boolean hasTakenAspirin(PatientDrugHistory drugHistory) {
        return drugHistory.isPresent(DrugName.Aspirin);
    }

}
