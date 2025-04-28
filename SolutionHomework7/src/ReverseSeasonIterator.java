public class ReverseSeasonIterator implements EpisodeIterator {
    private final Season season;
    private int index;

    public ReverseSeasonIterator(Season season) {
        this.season = season;
        this.index = season.getEpisodes().size() - 1;
    }

    @Override
    public boolean hasNext() {
        return index >= 0;
    }

    @Override
    public Episode next() {
        return season.getEpisodes().get(index--);
    }
}