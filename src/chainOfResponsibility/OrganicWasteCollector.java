package chainOfResponsibility;

public class OrganicWasteCollector extends WasteCollector {

    @Override
    protected boolean canHandle(WasteContainer container) {
        return container.getWasteType() == WasteType.ORGANIC;
    }

    @Override
    protected void collect(WasteContainer container) {
        System.out.printf("OrganicWasteCollector    -> %s%n", container);
        System.out.println("Routed to composting facility for bio-processing.");
        container.empty();
    }
}
