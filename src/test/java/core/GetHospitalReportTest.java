package core;

import core.domain.drug.IDrugStock;
import core.domain.patient.*;
import core.domain.drug.DrugStock;
import core.domain.drug.Antibiotic;
import core.domain.drug.Aspirin;
import core.domain.drug.Insulin;
import core.domain.drug.Paracetamol;
import core.usecase.GetHospitalReport;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class GetHospitalReportTest {

    private IDrugStock drugStock;
    private IPatientsList patients;
    private GetHospitalReport getHospitalReport;

    @Mock
    IDivinityService divinityService;

    @Before
    public void
    setup() {
        drugStock = new DrugStock();
        patients = new PatientList();
        getHospitalReport = new GetHospitalReport();

        // No miracle by default
        given(divinityService.isResurrectionAllowed()).willReturn(false);
    }

    @Test
    public void
    given_two_diabetic_patients_and_no_drugs_then_getReport_should_be_two_dead_and_zero_diabetes(){

        patients.add(getDiabetesPatient());
        patients.add(getDiabetesPatient());

        PatientsReport result = getHospitalReport.execute(patients, drugStock);

        assertThat(result.getDeadCount(), is(2));
        assertThat(result.getDiabetesCount(), is(0));
    }

    @Test
    public void
    given_one_fever_patient_and_paracetamol_then_getReport_should_show_only_one_healthy(){

        drugStock.add(new Paracetamol());
        patients.add(getFeverPatient());

        PatientsReport result = getHospitalReport.execute(patients, drugStock);

        assertThat(result.getHealthyCount(), is(1));
        assertThat(result.getFeverCount(), is(0));
    }

    @Test
    public void
    given_one_fever_and_tuberculosis_patient_and_aspirin_and_antibiotic_then_getReport_should_show_two_healthy(){

        drugStock.add(new Paracetamol());
        drugStock.add(new Antibiotic());
        patients.add(getFeverPatient());
        patients.add(getTuberculosisPatient());

        PatientsReport result = getHospitalReport.execute(patients, drugStock);

        assertThat(result.getHealthyCount(), is(2));
        assertThat(result.getFeverCount(), is(0));
        assertThat(result.getTuberculosisCount(), is(0));
    }

    @Test
    public void
    given_one_diabetic_patient_and_insulin_and_antibiotic_then_getReport_should_show_one_diabetic(){

        drugStock.add(new Insulin());
        drugStock.add(new Antibiotic());
        patients.add(getDiabetesPatient());

        PatientsReport result = getHospitalReport.execute(patients, drugStock);

        assertThat(result.getHealthyCount(), is(0));
        assertThat(result.getFeverCount(), is(0));
        assertThat(result.getDiabetesCount(), is(1));
    }

    @Test
    public void
    given_one_diabetic_patient_and_one_fever_and_insulin_and_aspirin_then_getReport_should_show_one_diabetic_and_one_healthy(){

        drugStock.add(new Insulin());
        drugStock.add(new Aspirin());
        patients.add(getDiabetesPatient());
        patients.add(getFeverPatient());

        PatientsReport result = getHospitalReport.execute(patients, drugStock);

        assertThat(result.getHealthyCount(), is(1));
        assertThat(result.getFeverCount(), is(0));
        assertThat(result.getDiabetesCount(), is(1));
    }

    @Test
    public void
    given_one_healthy_and_fever_patient_and_paracetamol_and_aspirin_then_getReport_should_show_two_dead(){

        drugStock.add(new Aspirin());
        drugStock.add(new Paracetamol());
        patients.add(getHealthyPatient());
        patients.add(getFeverPatient());

        PatientsReport result = getHospitalReport.execute(patients, drugStock);

        assertThat(result.getHealthyCount(), is(0));
        assertThat(result.getFeverCount(), is(0));
        assertThat(result.getDeadCount(), is(2));
    }

    private Patient getDiabetesPatient() {
        return new Patient(PatientStatus.Diabetes, divinityService);
    }

    private Patient getTuberculosisPatient() {
        return new Patient(PatientStatus.Tuberculosis, divinityService);
    }

    private Patient getFeverPatient() {
        return new Patient(PatientStatus.Fever, divinityService);
    }

    private Patient getHealthyPatient() {
        return new Patient(PatientStatus.Healthy, divinityService);
    }

}
