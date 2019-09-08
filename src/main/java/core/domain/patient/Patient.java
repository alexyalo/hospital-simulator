package core.domain.patient;

import core.domain.drug.IDrug;
import core.domain.drug.DrugName;

import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class Patient {

    private PatientStatus currentStatus;
    private PatientDrugHistory drugsTaken;

    public Patient(PatientStatus initialStatus) {
        currentStatus = initialStatus;
        drugsTaken = new PatientDrugHistory();
    }

    public PatientStatus getStatus() {
        return currentStatus;
    }

    public void applyDrugs(List<IDrug> drugs) {

        if (isDiabeticAndMissingInsulin(drugs)) {
            updateStatus(PatientStatus.Dead);
            return;
        }

        ListIterator<IDrug> drugList = drugs.listIterator();
        while (drugList.hasNext()){
            applyDrug(drugList.next());
        }
    }

    private boolean isDiabeticAndMissingInsulin(List<IDrug> drugs) {

        return isDiabetic() && isInsulinMissing(drugs);
    }

    private boolean isDiabetic() {

        return currentStatus.equals(PatientStatus.Diabetes);
    }

    private boolean isInsulinMissing(List<IDrug> drugs) {
        return drugs.stream()
                .filter(d -> d.getName().equals(DrugName.Insulin))
                .toArray().length == 0;
    }

    private void applyDrug(IDrug drug) {
        PatientStatus status = drug.getPatientStatusAfterApplication(currentStatus, drugsTaken);

        updateDrugsTaken(drug);

        updateStatus(status);
    }

    private void updateDrugsTaken(IDrug drug) {

        drugsTaken.add(drug.getName());
    }

    private void updateStatus(PatientStatus status) {

        currentStatus = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return currentStatus == patient.currentStatus &&
                Objects.equals(drugsTaken, patient.drugsTaken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentStatus, drugsTaken);
    }
}