package core.domain.drug;

import core.domain.patient.PatientDrugHistory;
import core.domain.patient.PatientStatus;

import java.util.Objects;

public class Insulin implements IDrug {

    private final DrugName name = DrugName.Insulin;

    @Override
    public PatientStatus getPatientStatusAfterApplication(PatientStatus currentStatus, PatientDrugHistory drugHistory) {

        if (hasTakenAntibiotic(drugHistory) && isNotDiabeticStatus(currentStatus)) {
            return PatientStatus.Fever;
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

    private boolean hasTakenAntibiotic(PatientDrugHistory drugHistory) {
        return drugHistory.isPresent(DrugName.Antibiotic);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Insulin)) return false;
        Insulin insulin = (Insulin) o;
        return name == insulin.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
