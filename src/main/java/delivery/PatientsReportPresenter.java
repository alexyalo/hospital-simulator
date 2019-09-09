package delivery;

import core.domain.patient.PatientsReport;
import core.usecase.GetHospitalReport;

public class PatientsReportPresenter {
    private IPatientsReportView patientsReportView;
    private GetHospitalReport getHospitalReport;

    public PatientsReportPresenter(IPatientsReportView patientsReportView, GetHospitalReport getHospitalReport) {
        this.patientsReportView = patientsReportView;
        this.getHospitalReport = getHospitalReport;
    }

    public void generateReport(PatientListRequestDTO patientListRequestDTO, DrugStockRequestDTO drugStockRequestDTO) {
        PatientsReport report = getHospitalReport.execute(patientListRequestDTO.toPatientsList(), drugStockRequestDTO.toDrugStock());

        PatientsReportResponseDTO response = new PatientsReportResponseDTO.Builder()
                .withHealthy(report.getHealthyCount())
                .withDead(report.getDeadCount())
                .withDiabetes(report.getDiabetesCount())
                .withTuberculosis(report.getTuberculosisCount())
                .withFever(report.getFeverCount())
                .build();

        patientsReportView.onReportGenerated(response);
    }
}
