package core.domain.patient;

import java.util.HashMap;
import java.util.Map;

public class PatientsReport {
    private Map<PatientStatus, Runnable> actionsByStatus = new HashMap<PatientStatus, Runnable>(){
        {
            put(PatientStatus.Dead, () -> incrementDeadCount());
            put(PatientStatus.Healthy, () -> incrementHealthyCount());
            put(PatientStatus.Fever, () -> incrementFeverCount());
            put(PatientStatus.Diabetes, () -> incrementDiabetesCount());
            put(PatientStatus.Tuberculosis, () -> incrementTuberculosisCount());
        }
    };
    private int healthyCount;
    private int deadCount;
    private int feverCount;
    private int diabetesCount;
    private int tuberculosisCount;

    public void incrementCountForStatus(PatientStatus status) {
        Runnable action = actionsByStatus.get(status);
        action.run();
    }

    public int getDeadCount() {
        return deadCount;
    }

    public int getHealthyCount() {
        return healthyCount;
    }

    public int getFeverCount() {
        return feverCount;
    }

    public int getDiabetesCount() {
        return diabetesCount;
    }

    public int getTuberculosisCount() {
        return tuberculosisCount;
    }

    private void incrementTuberculosisCount() {
        tuberculosisCount++;
    }

    private void incrementDiabetesCount() {
        diabetesCount++;
    }

    private void incrementHealthyCount() {
        healthyCount++;
    }

    private void incrementFeverCount() {
        feverCount++;
    }

    private void incrementDeadCount() {
        deadCount++;
    }
}
