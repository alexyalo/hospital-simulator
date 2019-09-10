package core.infrastructure;

import core.domain.patient.IDivinityService;

import java.util.Random;

public class FlyingSpaghettiMonster implements IDivinityService {

    // Obvio un dios debe ser único, asi que singleton
    private static FlyingSpaghettiMonster INSTANCE;

    private FlyingSpaghettiMonster() {
    }

    public static FlyingSpaghettiMonster getInstance() {
        if (INSTANCE == null)
            INSTANCE = new FlyingSpaghettiMonster();

        return INSTANCE;
    }

    @Override
    public boolean isResurrectionAllowed() {
        int oneInAMillion = getOneInAMillionChance();
        return oneInAMillion == 1;
    }

    private int getOneInAMillionChance() {
        return new Random().nextInt(1000000) + 1;
    }
}
