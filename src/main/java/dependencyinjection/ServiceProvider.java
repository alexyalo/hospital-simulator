package dependencyinjection;

import core.domain.patient.IDivinityService;
import core.infrastructure.FlyingSpaghettiMonster;

public class ServiceProvider {
    public static IDivinityService getDivinity() {
        return FlyingSpaghettiMonster.getInstance();
    }
}
