package delivery;

import core.domain.drug.*;

import java.util.*;

public class DrugStockRequestDTO {

    private static Map<String, IDrug> drugsByKey = new HashMap<String, IDrug>(){
        {
            put("P", DrugFactory.getParacetamol());
            put("I", DrugFactory.getInsulin());
            put("An", DrugFactory.getAntibiotic());
            put("As", DrugFactory.getAspirin());
        }
    };
    private final List<IDrug> drugs;

    private DrugStockRequestDTO(List<IDrug> drugs){
        this.drugs = drugs;
    }

    public static DrugStockRequestDTO fromString(String input) {
        String[] splittedInput = Arrays.stream(input.split(","))
                .map(String::trim)
                .toArray(String[]::new);

        List<IDrug> drugs = parseInput(splittedInput);

        return new DrugStockRequestDTO(drugs);
    }

    public DrugStock toDrugStock() {
        DrugStock result = new DrugStock();
        ListIterator<IDrug> listIterator = drugs.listIterator();

        while (listIterator.hasNext()) {
            result.add(listIterator.next());
        }

        return result;
    }

    private static List<IDrug> parseInput(String[] splittedInput) {
        List<IDrug> drugs = new ArrayList<>();

        for (int i = 0; i < splittedInput.length; i++) {
            String drugKey = splittedInput[i];

            if (drugsByKey.containsKey(drugKey)) {
                IDrug drug = drugsByKey.get(drugKey);
                drugs.add(drug);
            }
        }
        return drugs;
    }
}
