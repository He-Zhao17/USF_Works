import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetOperations {



    /* you implement this using the set interface */
    /* create a SetOperationsTest using JUnit to test this. */

    public static Set<String> Union(Set<String> s1, Set<String> s2) {
        Set<String> s3 = new HashSet<>();
        s3.addAll(s1);
        s3.addAll(s2);
        return s3;
    }

    /* then do intersection and union. */
    public static Set<String> Intersection(Set<String> s1, Set<String> s2) {
        Set<String> s3 = new HashSet<>();
        s3.addAll(s1);
        s3.removeAll(s3); // cb - this should be retainAll() - you're just removing everything that was already in the set.
        return s3;
    }

    public static Set<String> Difference(Set<String> s1, Set<String> s2) {
        Set<String> s3 = new HashSet<>();
        s3.addAll(s1);
        s3.removeAll(s2);
        return s3;
    }


}
