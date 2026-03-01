package chainOfResponsibility;

import java.util.ArrayList;
import java.util.List;

public class WasteManagementOrchestrator {

    private final WasteCollector       chainHead;
    private final List<WasteContainer> containers = new ArrayList<>();

    public WasteManagementOrchestrator() {
        //instantiate all handlers
        OrganicWasteCollector    organic    = new OrganicWasteCollector();
        RecyclableWasteCollector recyclable = new RecyclableWasteCollector();
        HazardousWasteCollector  hazardous  = new HazardousWasteCollector();
        GeneralWasteCollector    general    = new GeneralWasteCollector();

        //chain of order
        organic.setNext(recyclable)
               .setNext(hazardous)
               .setNext(general);

        chainHead = organic;

        
        System.out.print("Automated Waste Management System  -  Ready \n");
        System.out.println("   Chain: Organic -> Recyclable -> Hazardous -> General \n");
    }

    //register a container so the system can monitor it
    public void register(WasteContainer container) {
        containers.add(container);
        System.out.printf("Registered : %s%n", container);
    }

    //scan every registered container.
    public void runCollectionCycle() {
        System.out.println("\nCollection Cycle Started");
        boolean anyDisposed = false;

        for (WasteContainer c : containers) {
            if (c.needsDisposal()) {
                double pct = (c.getCurrentLoad() / c.getMaxCapacity()) * 100.0;
                System.out.printf("%n-> Disposal triggered for %s  (%.0f%% full):%n", c, pct);
                chainHead.handle(c);
                anyDisposed = true;
            }
        }

        if (!anyDisposed) {
            System.out.println("[i]No containers require disposal at this time.");
        }
        System.out.println("Collection Cycle Complete \n");
    }
    //validation
    public void validateChainCoverage() {
        System.out.println("Validation : Chain Coverage Test");
        for (WasteType type : WasteType.values()) {
            System.out.printf("%nTesting handler for %-12s ->%n", type);
            WasteContainer probe = new WasteContainer("PROBE-" + type, type, 100);
            probe.addWaste(100);
            chainHead.handle(probe);
        }
        System.out.println("\nValidation Complete \n");
    }
}
