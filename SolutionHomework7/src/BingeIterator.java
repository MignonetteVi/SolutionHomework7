import java.util.List;
import java.util.NoSuchElementException;

public class BingeIterator implements EpisodeIterator {
    private final List<Season> seasons;
    private int seasonIdx = 0;
    private EpisodeIterator current;

    public BingeIterator(List<Season> seasons) {
        this.seasons = seasons;
        this.current = seasons.isEmpty() ? null : new SeasonIterator(seasons.get(0));
    }

    @Override
    public boolean hasNext() {
        while (current != null && !current.hasNext()) {
            seasonIdx++;
            if (seasonIdx >= seasons.size()) {
                return false;
            }
            current = new SeasonIterator(seasons.get(seasonIdx));
        }
        return current != null && current.hasNext();
    }

    @Override
    public Episode next() {
        if (!hasNext()) throw new NoSuchElementException();
        return current.next();
    }
}