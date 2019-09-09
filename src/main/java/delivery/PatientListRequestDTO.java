package delivery;

import core.domain.patient.Patient;
import core.domain.patient.PatientList;
import core.domain.patient.PatientStatus;
import dependencyinjection.ServiceProvider;

import java.util.*;

public class PatientListRequestDTO {
    private final List<Patient> patientsList;

    private static Map<String, PatientStatus> patientStatusByString = new HashMap<String, PatientStatus>(){
        {
            put("F", PatientStatus.Fever);
            put("X", PatientStatus.Dead);
            put("T", PatientStatus.Tuberculosis);
            put("D", PatientStatus.Diabetes);
            put("H", PatientStatus.Healthy);
        }
    };

    private PatientListRequestDTO(List<Patient> patientsList) {
        this.patientsList = patientsList;
    }

    public static PatientListRequestDTO fromString(String input) {
        String[] splittedInput = Arrays.stream(input.split(","))
                .map(String::trim)
                .toArray(String[]::new);

        List<Patient> patients = parseInput(splittedInput);

        return new PatientListRequestDTO(patients);
    }

    public PatientList toPatientsList() {
        PatientList result = new PatientList();
        ListIterator<Patient> listIterator = patientsList.listIterator();

        while (listIterator.hasNext()) {
            result.add(listIterator.next());
        }

        return result;
    }

    private static List<Patient> parseInput(String[] splittedInput) {
        List<Patient> patients = new ArrayList<>();

        for (int i = 0; i < splittedInput.length; i++) {
            String statusKey = splittedInput[i];

            if (patientStatusByString.containsKey(statusKey)) {
                Patient patient = new Patient(patientStatusByString.get(statusKey), ServiceProvider.getDivinity());
                patients.add(patient);
            }
        }
        return patients;
    }
}
