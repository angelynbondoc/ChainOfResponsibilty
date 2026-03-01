package chainOfResponsibility;

public abstract class WasteCollector {

    protected WasteCollector nextCollector;
    public WasteCollector setNext(WasteCollector next) {
        this.nextCollector = next;
        return next;
    }

    public final void handle(WasteContainer container) {
        if (canHandle(container)) {
            collect(container);
        } else if (nextCollector != null) {
            nextCollector.handle(container);
        } else {
            System.out.printf("No suitable collector found for %s%n", container);
        }
    }

    protected abstract boolean canHandle(WasteContainer container);
    protected abstract void collect(WasteContainer container);
}