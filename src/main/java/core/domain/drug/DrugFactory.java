package core.domain.drug;

public class DrugFactory {
    public static IDrug getParacetamol() {
        return new Paracetamol();
    }

    public static IDrug getInsulin() {
        return new Insulin();
    }

    public static IDrug getAntibiotic() {
        return new Antibiotic();
    }

    public static IDrug getAspirin() {
        return new Aspirin();
    }
}
