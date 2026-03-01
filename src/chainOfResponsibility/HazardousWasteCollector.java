package chainOfResponsibility;

public class HazardousWasteCollector extends WasteCollector {

    @Override
    protected boolean canHandle(WasteContainer container) {
        return container.getWasteType() == WasteType.HAZARDOUS;
    }

    @Override
    protected void collect(WasteContainer container) {
        System.out.printf("HazardousWasteCollector  -> %s%n", container);
        System.out.println("Hazmat unit dispatched for regulated safe disposal.");
        container.empty();
    }
}