import java.util.NoSuchElementException;

public class SeasonIterator implements EpisodeIterator {
    private final Season season;
    private int index = 0;

    public SeasonIterator(Season season) {
        this.season = season;
    }

    @Override
    public boolean hasNext() {
        return index < season.getEpisodes().size();
    }

    @Override
    public Episode next() {
        if (!hasNext()) throw new NoSuchElementException();
        return season.getEpisodes().get(index++);
    }
}