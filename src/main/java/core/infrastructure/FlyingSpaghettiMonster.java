package core.infrastructure;

import core.domain.patient.IDivinityService;

import java.util.Random;

public class FlyingSpaghettiMonster implements IDivinityService {
    @Override
    public boolean isResurrectionAllowed() {
        int oneInAMillion = getOneInAMillionChance();
        return oneInAMillion == 1;
    }

    private int getOneInAMillionChance() {
        return new Random().nextInt(1000000) + 1;
    }
}
