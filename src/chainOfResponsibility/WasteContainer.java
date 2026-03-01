package chainOfResponsibility;

public class WasteContainer {

    private final String    id;
    private final WasteType wasteType;
    private final double    maxCapacity;   // litres
    private       double    currentLoad;   // litres

    public WasteContainer(String id, WasteType wasteType, double maxCapacity) {
        this.id          = id;
        this.wasteType   = wasteType;
        this.maxCapacity = maxCapacity;
        this.currentLoad = 0;
    }

    //add waste
    public void addWaste(double amount) {
        this.currentLoad = Math.min(currentLoad + amount, maxCapacity);
    }

    //disposal is triggered once the container reaches 90% capacity. 
    public boolean needsDisposal() {
        return currentLoad >= maxCapacity * 0.90;
    }

    //Reset load after a successful collection.
    public void empty() {
        System.out.printf("[Container %s] cleared (%.1f / %.1f L removed).%n",
                id, currentLoad, maxCapacity);
        currentLoad = 0;
    }

    public String    getId()          { return id; }
    public WasteType getWasteType()   { return wasteType; }
    public double    getMaxCapacity() { return maxCapacity; }
    public double    getCurrentLoad() { return currentLoad; }

    @Override
    public String toString() {
        return String.format("Container[id=%-8s type=%-12s load=%5.1f/%5.1f L]",
                id, wasteType, currentLoad, maxCapacity);
    }
}