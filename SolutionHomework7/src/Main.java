import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Season s1 = new Season();
        s1.addEpisode(new Episode("S1E1", 1200));
        s1.addEpisode(new Episode("S1E2", 1300));

        Season s2 = new Season();
        s2.addEpisode(new Episode("S2E1", 1100));
        s2.addEpisode(new Episode("S2E2", 1150));

        System.out.println("Normal:");
        for (Episode e : s1) {
            System.out.println(e.getTitle());
        }

        System.out.println("\nReverse:");
        Iterator<Episode> rev = new ReverseSeasonIterator(s1);
        rev.forEachRemaining(ep -> System.out.println(ep.getTitle()));

        System.out.println("\nShuffle:");
        Iterator<Episode> sh = new ShuffleSeasonIterator(s1);
        sh.forEachRemaining(ep -> System.out.println(ep.getTitle()));

        System.out.println("\nBinge:");
        List<Season> all = Arrays.asList(s1, s2);
        Iterator<Episode> binge = new BingeIterator(all);
        binge.forEachRemaining(ep -> System.out.println(ep.getTitle()));
    }
}