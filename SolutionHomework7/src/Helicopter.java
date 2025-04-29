public class Helicopter extends Aircraft {
    public Helicopter(String id, int fuelLevel, TowerMediator tower) {
        super(id, fuelLevel, tower);
    }

    @Override
    public void receive(String msg) {
        System.out.printf("Helicopter %s receives: %s%n", id, msg);
    }
}