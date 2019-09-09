package delivery;

import core.domain.drug.Antibiotic;
import core.domain.drug.Aspirin;
import core.domain.drug.DrugStock;
import core.domain.drug.Insulin;
import core.domain.patient.*;
import core.usecase.GetHospitalReport;
import org.junit.Before;
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

    @Mock
    IDivinityService divinityService;

    @Test
    public void
    given_getReport_with_input_then_view_is_notified_with_result() {
        // Given
        PatientListRequestDTO patientListRequestDTO = PatientListRequestDTO.fromString("D");
        DrugStockRequestDTO drugStockRequestDTO = DrugStockRequestDTO.fromString("I");
        DrugStock drugStock = getDrugStock();
        PatientList patientList = getPatientList();
        PatientsReport patientsReport = getPatientsReport();

        PatientsReportResponseDTO expectedResponse = new PatientsReportResponseDTO.Builder()
                .withDiabetes(1)
                .build();

        given(getHospitalReport.execute(patientListRequestDTO.toPatientsList(), drugStockRequestDTO.toDrugStock()))
                .willReturn(patientsReport);

        // When
        PatientsReportPresenter presenter = new PatientsReportPresenter(patientsReportView, getHospitalReport);
        presenter.generateReport(patientListRequestDTO, drugStockRequestDTO);

        // Then
        verify(getHospitalReport).execute(patientList, drugStock);
        verify(patientsReportView).onReportGenerated(expectedResponse);
    }

    // TODO: Hacer un test para cada combinacion distinta de status de paciente y drogas

    private PatientList getPatientList() {
        PatientList list = new PatientList();
        list.add(new Patient(PatientStatus.Diabetes, divinityService));

        return list;
    }

    private DrugStock getDrugStock() {
        DrugStock stock = new DrugStock();
        stock.add(new Insulin());

        return stock;
    }

    private PatientsReport getPatientsReport() {
        PatientsReport patientsReport = new PatientsReport();
        patientsReport.incrementCountForStatus(PatientStatus.Diabetes);

        return patientsReport;
    }

}
