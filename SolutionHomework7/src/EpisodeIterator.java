import java.util.Iterator;

public interface EpisodeIterator extends Iterator<Episode> {
    @Override
    default void remove() {
        throw new UnsupportedOperationException();
    }
}