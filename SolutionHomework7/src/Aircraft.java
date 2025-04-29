public abstract class Aircraft {
    protected final String id;
    protected int fuelLevel;

    protected final TowerMediator tower;

    public Aircraft(String id, int fuelLevel, TowerMediator tower) {
        this.id = id;
        this.fuelLevel = fuelLevel;
        this.tower = tower;
    }

    public String getId() {
        return id;
    }

    public int getFuelLevel() {
        return fuelLevel;
    }

    public abstract void receive(String msg);

    public void send(String msg) {
        tower.broadcast(msg, this);
    }

    public boolean requestRunway() {
        return tower.requestRunway(this);
    }
}
