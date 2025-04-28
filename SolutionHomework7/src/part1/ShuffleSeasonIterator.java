package part1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class ShuffleSeasonIterator implements EpisodeIterator {
    private final List<Episode> shuffled;
    private int index = 0;

    public ShuffleSeasonIterator(Season season) {
        this.shuffled = new ArrayList<>(season.getEpisodes());
        Collections.shuffle(shuffled, new Random(42));
    }

    @Override
    public boolean hasNext() {
        return index < shuffled.size();
    }

    @Override
    public Episode next() {
        if (!hasNext()) throw new NoSuchElementException();
        return shuffled.get(index++);
    }
}