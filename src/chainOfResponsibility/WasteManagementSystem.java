package chainOfResponsibility;

public class WasteManagementSystem {

    public static void main(String[] args) {

        WasteManagementOrchestrator wms = new WasteManagementOrchestrator();

        //register the containers
        System.out.println("Registering Containers");
        WasteContainer c1 = new WasteContainer("BIN-001", WasteType.ORGANIC,    200);
        WasteContainer c2 = new WasteContainer("BIN-002", WasteType.RECYCLABLE, 150);
        WasteContainer c3 = new WasteContainer("BIN-003", WasteType.HAZARDOUS,   50);
        WasteContainer c4 = new WasteContainer("BIN-004", WasteType.GENERAL,    300);
        WasteContainer c5 = new WasteContainer("BIN-005", WasteType.ORGANIC,    100);

        wms.register(c1);
        wms.register(c2);
        wms.register(c3);
        wms.register(c4);
        wms.register(c5);

        //simulate waste accumulation
        System.out.println("\nWaste Accumulation (Round 1)");
        c1.addWaste(185);   //92.5% -> disposal needed
        c2.addWaste(100);   //66.7% -> within limit
        c3.addWaste(48);    //96.0% -> disposal needed
        c4.addWaste(270);   //90.0% -> disposal needed (boundary)
        c5.addWaste(40);    //40.0% -> within limit
        System.out.println("Waste levels updated.");

        wms.runCollectionCycle();

        //more accumulation
        System.out.println("Waste Accumulation (Round 2)");
        c2.addWaste(140);   //now 93.3% -> disposal needed
        c5.addWaste(95);    //capped at 100 L -> disposal needed
        System.out.println("Waste levels updated.");

        wms.runCollectionCycle();

        //validate chain covers all waste types
        wms.validateChainCoverage();
    }
}