package chainOfResponsibility;

public class RecyclableWasteCollector extends WasteCollector {

    @Override
    protected boolean canHandle(WasteContainer container) {
        return container.getWasteType() == WasteType.RECYCLABLE;
    }

    @Override
    protected void collect(WasteContainer container) {
        System.out.printf("RecyclableWasteCollector -> %s%n", container);
        System.out.println("Sorted and forwarded to recycling plant.");
        container.empty();
    }
}