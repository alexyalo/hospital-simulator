package core;

import core.domain.drug.IDrugStock;
import core.domain.patient.Patient;
import core.domain.patient.PatientStatus;
import core.domain.drug.DrugStock;
import core.domain.drug.Antibiotic;
import core.domain.drug.Aspirin;
import core.domain.drug.Insulin;
import core.domain.drug.Paracetamol;
import core.domain.drug.IDrug;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PatientTest {

    private IDrug paracetamol;
    private IDrug aspirin;
    private IDrug antibiotic;
    private IDrugStock prescribedDrugs;
    private IDrug insulin;

    @Before
    public void
    setup() {
        paracetamol = new Paracetamol();
        aspirin = new Aspirin();
        antibiotic = new Antibiotic();
        insulin = new Insulin();

        prescribedDrugs = new DrugStock();
    }

    @Test
    public void
    given_a_patient_with_fever_when_given_aspirin_then_patient_should_be_healthy(){
        Patient patient = getFeverPatient();

        IDrugStock prescribedDrugs = new DrugStock();
        prescribedDrugs.add(aspirin);

        patient.applyDrugs(prescribedDrugs.getList());
        PatientStatus postDrugApplicationStatus = patient.getStatus();

        assertThat(postDrugApplicationStatus, is(PatientStatus.Healthy));
    }

    @Test public void
    given_a_healthy_patient_when_given_aspirin_then_patient_should_be_healthy(){
        Patient patient = getHealthyPatient();
        prescribedDrugs.add(aspirin);

        patient.applyDrugs(prescribedDrugs.getList());
        PatientStatus postDrugApplicationStatus = patient.getStatus();

        assertThat(postDrugApplicationStatus, is(PatientStatus.Healthy));
    }

    @Test public void
    given_a_fever_patient_when_given_aspirin_and_paracetamol_then_patient_should_be_dead(){
        Patient patient = getFeverPatient();
        prescribedDrugs.add(aspirin);
        prescribedDrugs.add(paracetamol);

        patient.applyDrugs(prescribedDrugs.getList());
        PatientStatus postDrugApplicationStatus = patient.getStatus();

        assertThat(postDrugApplicationStatus, is(PatientStatus.Dead));
    }

    @Test public void
    given_a_fever_patient_when_given_paracetamol_and_aspirin_then_patient_should_be_dead(){
        Patient patient = getFeverPatient();
        prescribedDrugs.add(paracetamol);
        prescribedDrugs.add(aspirin);

        patient.applyDrugs(prescribedDrugs.getList());
        PatientStatus postDrugApplicationStatus = patient.getStatus();

        assertThat(postDrugApplicationStatus, is(PatientStatus.Dead));
    }

    @Test public void
    given_patient_with_tuberculosis_when_given_paracetamol_then_patient_status_should_be_tuberculosis(){
        Patient patient = getTuberculosisPatient();
        prescribedDrugs.add(paracetamol);

        patient.applyDrugs(prescribedDrugs.getList());
        PatientStatus postDrugApplicationStatus = patient.getStatus();

        assertThat(postDrugApplicationStatus, is(PatientStatus.Tuberculosis));
    }

    @Test public void
    given_patient_with_tuberculosis_when_given_antibiotic_then_patient_status_should_be_healthy(){
        Patient patient = getTuberculosisPatient();
        prescribedDrugs.add(antibiotic);

        patient.applyDrugs(prescribedDrugs.getList());
        PatientStatus postDrugApplicationStatus = patient.getStatus();

        assertThat(postDrugApplicationStatus, is(PatientStatus.Healthy));
    }

    @Test
    public void
    given_patient_with_diabetes_when_given_aspirin_patient_is_dead() {
        Patient patient = getDiabeticPatient();
        prescribedDrugs.add(aspirin);

        patient.applyDrugs(prescribedDrugs.getList());

        assertThat(patient.getStatus(), is(PatientStatus.Dead));
    }

    @Test
    public void
    given_diabetes_when_not_given_insulin_patient_is_dead() {
        Patient patient = getDiabeticPatient();

        patient.applyDrugs(prescribedDrugs.getList());

        assertThat(patient.getStatus(), is(PatientStatus.Dead));
    }

    @Test
    public void
    given_diabetes_when_given_insulin_patient_is_diabetes() {
        Patient patient = getDiabeticPatient();
        prescribedDrugs.add(insulin);

        patient.applyDrugs(prescribedDrugs.getList());

        assertThat(patient.getStatus(), is(PatientStatus.Diabetes));
    }

    private Patient getDiabeticPatient() {

        return new Patient(PatientStatus.Diabetes);
    }

    private Patient getTuberculosisPatient() {

        return new Patient(PatientStatus.Tuberculosis);
    }


    private Patient getFeverPatient() {

        return new Patient(PatientStatus.Fever);
    }

    private Patient getHealthyPatient() {

        return new Patient(PatientStatus.Healthy);
    }


}
