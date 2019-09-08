package core.domain.patient;

import core.domain.drug.DrugName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PatientDrugHistory {
    private List<DrugName> drugs = new ArrayList<>();

    public void add(DrugName drug) {
        drugs.add(drug);
    }

    public boolean isPresent(DrugName drug) {
        return drugs.contains(drug);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PatientDrugHistory)) return false;
        PatientDrugHistory that = (PatientDrugHistory) o;
        return Objects.equals(drugs, that.drugs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(drugs);
    }
}
