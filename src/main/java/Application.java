import core.usecase.GetHospitalReport;
import delivery.*;

public class Application {
    public static void main(String args[]) {
        if (args.length == 0) {
            System.out.println("Please specify at least 1 patient status. Format: hospital.jar [patients] [drugs]");
            return;
        }

        IPatientsReportView view = new PatientsReportView();
        GetHospitalReport getHospitalReport = new GetHospitalReport();
        PatientsReportPresenter presenter = new PatientsReportPresenter(view, getHospitalReport);

        String patients = args[0];
        String drugs = args.length > 1 ? args[1] : "";

        presenter.generateReport(PatientListRequestDTO.fromString(patients), DrugStockRequestDTO.fromString(drugs));
    }
}
