import java.util.ArrayDeque;
import java.util.Queue;

public class ControlTower implements TowerMediator {
    private final Queue<Aircraft> landingQueue = new ArrayDeque<>();
    private final Queue<Aircraft> takeoffQueue = new ArrayDeque<>();

    @Override
    public void broadcast(String msg, Aircraft sender) {

    }

    @Override
    public boolean requestRunway(Aircraft a) {
        return false;
    }
}
