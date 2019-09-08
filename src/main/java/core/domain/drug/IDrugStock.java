package core.domain.drug;

import java.util.List;

public interface IDrugStock {
    void add(IDrug drug);

    List<IDrug> getList();
}
