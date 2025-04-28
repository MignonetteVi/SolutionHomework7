import java.util.Iterator;

public class SeasonIterator implements Iterator<Episode> {
    public SeasonIterator(Season episodes) {

    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Episode next() {
        return null;
    }
}
