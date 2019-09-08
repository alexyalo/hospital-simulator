package core;

import core.domain.patient.PatientDrugHistory;
import core.domain.drug.Aspirin;
import core.domain.drug.IDrug;
import core.domain.drug.Paracetamol;
import org.junit.Before;
import org.junit.Test;

import static core.domain.drug.DrugName.Aspirin;
import static core.domain.drug.DrugName.Paracetamol;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DrugHistoryTest {

    private IDrug aspirin;
    private IDrug paracetamol;
    private PatientDrugHistory drugHistory;

    @Before
    public void
    setup() {
        drugHistory = new PatientDrugHistory();
        aspirin = new Aspirin();
        paracetamol = new Paracetamol();
    }

    @Test
    public void
    given_add_aspirin_then_aspirin_should_be_present(){
        drugHistory.add(aspirin.getName());

        assertTrue(drugHistory.isPresent(Aspirin));
    }

    @Test
    public void
    given_add_aspirin_then_paracetamol_should_not_be_present(){

        drugHistory.add(aspirin.getName());

        assertTrue(drugHistory.isPresent(Aspirin));
        assertFalse(drugHistory.isPresent(Paracetamol));
    }

    @Test
    public void
    given_add_aspirin_and_paracetamol_then_paracetamol_and_aspirin_should_not_be_present(){
        drugHistory.add(aspirin.getName());
        drugHistory.add(paracetamol.getName());

        assertTrue(drugHistory.isPresent(Aspirin));
        assertTrue(drugHistory.isPresent(Paracetamol));
    }
}
