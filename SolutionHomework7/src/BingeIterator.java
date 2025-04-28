import java.util.List;

public class BingeIterator implements EpisodeIterator {
    private final List<Season> seasons;
    private int seasonIndex = 0;
    private SeasonIterator current;

    public BingeIterator(List<Season> seasons) {
        this.seasons = seasons;
        this.current = seasons.isEmpty() ? null : new SeasonIterator(seasons.get(0));
    }

    @Override
    public boolean hasNext() {
        while (current != null && !current.hasNext()) {
            seasonIndex++;
            if (seasonIndex >= seasons.size()) {
                return false;
            }
            current = new SeasonIterator(seasons.get(seasonIndex));
        }
        return current != null && current.hasNext();
    }

    @Override
    public Episode next() {
        return current.next();
    }
}
