package chainOfResponsibility;

public class GeneralWasteCollector extends WasteCollector {

    @Override
    protected boolean canHandle(WasteContainer container) {
        return container.getWasteType() == WasteType.GENERAL;
    }

    @Override
    protected void collect(WasteContainer container) {
        System.out.printf("GeneralWasteCollector    -> %s%n", container);
        System.out.println("Transported to municipal landfill.");
        container.empty();
    }
}