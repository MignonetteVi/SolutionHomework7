import java.util.ArrayDeque;
import java.util.Queue;

public class ControlTower implements TowerMediator {
    private final Queue<Aircraft> landingQueue = new ArrayDeque<>();
    private final Queue<Aircraft> takeoffQueue = new ArrayDeque<>();

    @Override
    public synchronized void broadcast(String msg, Aircraft sender) {
        System.out.printf("Tower receives '%s' from %s.%n", msg, sender.getId());
        if ("MAYDAY".equalsIgnoreCase(msg.trim())) {
            handleEmergency(sender);
        } else {
            landingQueue.forEach(a -> {
                if (!a.equals(sender)) a.receive(msg);
            });
            takeoffQueue.forEach(a -> {
                if (!a.equals(sender)) a.receive(msg);
            });
        }
    }

    @Override
    public synchronized boolean requestRunway(Aircraft a) {
        if (a.getFuelLevel() < 10) {
            System.out.printf("%s low fuel! Granting immediate runway clearance.%n", a.getId());
            a.receive("Runway CLEARANCE (EMERGENCY)");
            return true;
        }

        landingQueue.offer(a);
        System.out.printf("%s queued for landing. Position: %d.%n", a.getId(), landingQueue.size());
        scheduleNext();
        return false;
    }

    private void handleEmergency(Aircraft emergencyPlane) {
        System.out.printf("EMERGENCY: %s demands MAYDAY! Clearing runway...%n", emergencyPlane.getId());

        landingQueue.forEach(a -> a.receive("HOLD POSITION! EMERGENCY LADING"
        ));
        takeoffQueue.forEach(a -> a.receive("HOLD POSITION! EMERGENCY LADING"
        ));

        landingQueue.clear();
        takeoffQueue.clear();

        emergencyPlane.receive("Runway CLEARANCE (EMERGENCY)");
    }

    private void scheduleNext() {
        if (!landingQueue.isEmpty()) {
            Aircraft next = landingQueue.poll();
            System.out.printf("Granting runway to %s for landing.%n", next.getId());
            next.receive("Runway CLEARANCE for LANDING");
        } else if (!takeoffQueue.isEmpty()) {
            Aircraft next = takeoffQueue.poll();
            System.out.printf("Granting runway to %s for takeoff.%n", next.getId());
            next.receive("Runway CLEARANCE for TAKEOFF");
        }
    }
}