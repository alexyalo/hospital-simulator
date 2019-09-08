package delivery;

public class PatientsReportView implements IPatientsReportView {
    @Override
    public void onReportGenerated(PatientsReportResponseDTO response) {
        System.out.println(response.getResponse());
    }
}
