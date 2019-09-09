package core.domain.drug;

import core.domain.patient.PatientDrugHistory;
import core.domain.patient.PatientStatus;

import java.util.Objects;

public class Paracetamol implements IDrug {

    private final DrugName name = DrugName.Paracetamol;

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
        return name;
    }

    private boolean isFeverStatus(PatientStatus currentStatus) {
        return currentStatus == PatientStatus.Fever;
    }

    private boolean hasTakenAspirin(PatientDrugHistory drugHistory) {
        return drugHistory.isPresent(DrugName.Aspirin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Paracetamol)) return false;
        Paracetamol that = (Paracetamol) o;
        return name == that.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
