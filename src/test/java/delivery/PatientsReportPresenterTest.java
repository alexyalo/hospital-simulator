package delivery;

import core.domain.drug.Antibiotic;
import core.domain.drug.Aspirin;
import core.domain.drug.DrugStock;
import core.domain.drug.Paracetamol;
import core.domain.patient.Patient;
import core.domain.patient.PatientList;
import core.domain.patient.PatientStatus;
import core.domain.patient.PatientsReport;
import core.usecase.GetHospitalReport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PatientsReportPresenterTest {
    @Mock
    IPatientsReportView patientsReportView;

    @Mock
    GetHospitalReport getHospitalReport;


    @Test
    public void
    given_getReport_with_input_then_view_is_notified_with_result() {
        // Given
        PatientListRequestDTO patientListRequestDTO = PatientListRequestDTO.fromString("F,T,T");
        DrugStockRequestDTO drugStockRequestDTO = DrugStockRequestDTO.fromString("An,As");
        DrugStock drugStock = getDrugStock();
        PatientList patientList = getPatientList();
        PatientsReport patientsReport = getPatientsReport();
        PatientsReportResponseDTO expectedResponse = new PatientsReportResponseDTO.Builder()
                .withHealthy(3)
                .build();

        given(getHospitalReport.execute(patientListRequestDTO.asPatientsList(), drugStockRequestDTO.asDrugStock()))
                .willReturn(patientsReport);

        // When
        PatientsReportPresenter presenter = new PatientsReportPresenter(patientsReportView, getHospitalReport);
        presenter.generateReport(patientListRequestDTO, drugStockRequestDTO);

        // Then
        verify(getHospitalReport).execute(patientList, drugStock);
        verify(patientsReportView).onReportGenerated(expectedResponse);
    }

    private PatientList getPatientList() {
        PatientList list = new PatientList();
        list.add(new Patient(PatientStatus.Fever));
        list.add(new Patient(PatientStatus.Tuberculosis));
        list.add(new Patient(PatientStatus.Tuberculosis));
        return list;
    }

    private DrugStock getDrugStock() {
        DrugStock stock = new DrugStock();
        stock.add(new Antibiotic());
        stock.add(new Aspirin());
        return stock;
    }

    private PatientsReport getPatientsReport() {
        PatientsReport patientsReport = new PatientsReport();
        patientsReport.incrementCountForStatus(PatientStatus.Healthy);
        patientsReport.incrementCountForStatus(PatientStatus.Healthy);
        patientsReport.incrementCountForStatus(PatientStatus.Healthy);
        return patientsReport;
    }

}
