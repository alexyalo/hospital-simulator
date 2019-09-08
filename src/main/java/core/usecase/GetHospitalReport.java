package core.usecase;

import core.domain.drug.IDrug;
import core.domain.drug.IDrugStock;
import core.domain.patient.IPatientsList;
import core.domain.patient.Patient;
import core.domain.patient.PatientsReport;

import java.util.List;
import java.util.ListIterator;

public class GetHospitalReport {

    public PatientsReport execute(IPatientsList patients, IDrugStock drugStock) {
        return getReport(patients.getList(), drugStock.getList());
    }

    private PatientsReport getReport(List<Patient> patientList, List<IDrug> drugs) {
        PatientsReport report = new PatientsReport();
        ListIterator<Patient> patientListIterator = patientList.listIterator();

        while (patientListIterator.hasNext()) {
            Patient patient = patientListIterator.next();

            patient.applyDrugs(drugs);

            report.incrementCountForStatus(patient.getStatus());
        }

        return report;
    }
}
