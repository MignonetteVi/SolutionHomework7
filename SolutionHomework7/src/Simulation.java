import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Simulation {
    public static void main(String[] args) {
        ControlTower tower = new ControlTower();
        List<Aircraft> fleet = new ArrayList<>();
        Random rnd = new Random();

        // Spawn 10 random aircraft
        for (int i = 1; i <= 10; i++) {
            int fuel = rnd.nextInt(50) + 1;
            int type = rnd.nextInt(3);
            String id = "AC" + i;
            Aircraft a;
            switch (type) {
                case 0 -> a = new PassengerPlane(id, fuel, tower);
                case 1 -> a = new CargoPlane(id, fuel, tower);
                default -> a = new Helicopter(id, fuel, tower);
            }
            fleet.add(a);
        }

        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        exec.scheduleAtFixedRate(() -> {
            Aircraft a = fleet.get(rnd.nextInt(fleet.size()));
            // Random action
            if (rnd.nextBoolean()) {
                System.out.printf("%s requests LAND (fuel=%d)%n", a.getId(), a.getFuelLevel());
                a.send("LAND");
            } else {
                System.out.printf("%s requests TAKEOFF (fuel=%d)%n", a.getId(), a.getFuelLevel());
                a.send("TAKEOFF");
            }
            // 5% chance of emergency
            if (rnd.nextInt(20) == 0) {
                System.out.printf("%s sends MAYDAY!%n", a.getId());
                a.send("MAYDAY");
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
}