package core.domain.drug;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DrugStock implements IDrugStock {
    private List<IDrug> drugs = new ArrayList<>();

    @Override
    public void add(IDrug drug) {
        drugs.add(drug);
    }

    @Override
    public List<IDrug> getList() {
        return drugs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DrugStock)) return false;
        DrugStock drugStock = (DrugStock) o;
        return drugs.equals(drugStock.drugs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(drugs);
    }
}
