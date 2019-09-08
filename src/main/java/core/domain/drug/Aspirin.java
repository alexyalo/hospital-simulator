package core.domain.drug;

import core.domain.patient.PatientDrugHistory;
import core.domain.patient.PatientStatus;

import java.util.Objects;

public class Aspirin implements IDrug {

    private DrugName name = DrugName.Aspirin;

    @Override
    public PatientStatus getPatientStatusAfterApplication(PatientStatus currentStatus, PatientDrugHistory drugHistory) {

        if (hasTakenParacetamol(drugHistory)) {
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

    private boolean hasTakenParacetamol(PatientDrugHistory drugHistory) {
        return drugHistory.isPresent(DrugName.Paracetamol);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aspirin)) return false;
        Aspirin aspirin = (Aspirin) o;
        return name == aspirin.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
