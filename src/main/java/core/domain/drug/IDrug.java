package core.domain.drug;

import core.domain.patient.PatientDrugHistory;
import core.domain.patient.PatientStatus;

public interface IDrug {
    PatientStatus getPatientStatusAfterApplication(PatientStatus currentStatus, PatientDrugHistory drugHistory);

    DrugName getName();
}