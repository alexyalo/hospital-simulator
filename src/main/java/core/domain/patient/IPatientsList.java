package core.domain.patient;

import java.util.List;

public interface IPatientsList {
    void add(Patient patient);

    List<Patient> getList();
}
