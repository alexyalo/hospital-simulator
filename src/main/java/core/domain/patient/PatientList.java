package core.domain.patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PatientList implements IPatientsList {
    private List<Patient> patients = new ArrayList<>();

    @Override
    public void add(Patient patient) {
        patients.add(patient);
    }

    @Override
    public List<Patient> getList() {
        return patients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PatientList)) return false;
        PatientList that = (PatientList) o;
        return patients.equals(that.patients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patients);
    }
}
