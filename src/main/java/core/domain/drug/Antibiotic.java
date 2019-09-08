package core.domain.drug;

import core.domain.patient.PatientDrugHistory;
import core.domain.patient.PatientStatus;

import java.util.Objects;

public class Antibiotic implements IDrug {

    private final DrugName name = DrugName.Antibiotic;

    @Override
    public PatientStatus getPatientStatusAfterApplication(PatientStatus currentStatus, PatientDrugHistory drugHistory) {
        /*
         Esta lógica prefiero dejarla encapsulada aquí porque hay mas posibilidades de que cambie la implementación de
         Antibiotic (ej: si inventan uno mejor) y quizás ya no es incompatible con la Insulina.
         */
        if (hasTakenInsulin(drugHistory) && isNotDiabeticStatus(currentStatus)) {
            return PatientStatus.Fever;
        }

        if (isTuberculosisStatus(currentStatus)) {
            return PatientStatus.Healthy;
        }

        return currentStatus;
    }

    @Override
    public DrugName getName() {
        return name;
    }

    private boolean isNotDiabeticStatus(PatientStatus currentStatus) {
        return !currentStatus.equals(PatientStatus.Diabetes);
    }

    private boolean isTuberculosisStatus(PatientStatus currentStatus) {
        return currentStatus.equals(PatientStatus.Tuberculosis);
    }

    private boolean hasTakenInsulin(PatientDrugHistory drugHistory) {
        return drugHistory.isPresent(DrugName.Insulin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Antibiotic)) return false;
        Antibiotic that = (Antibiotic) o;
        return name == that.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
