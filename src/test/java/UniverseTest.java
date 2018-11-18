import com.retreat.Point;
import com.retreat.Universe;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class UniverseTest {

    @Test
    public void givenEmptyUniverseShouldReturnEmptyUniverse() {
        Universe universe = new Universe();
        Universe newUniverse = universe.transit();
        Assert.assertEquals(universe, newUniverse);
    }

    @Test
    public void givenOneLifeUniverseShouldReturnEmptyUniverse() {
        Point life = new Point(1, 1);
        Universe universe = new Universe(new HashSet<>(Collections.singletonList(life)));
        Universe nextUniverse = universe.transit();
        Assert.assertEquals(new Universe(), nextUniverse);
    }

    @Test
    public void givenUnderPopulatedUniverseShouldReturnNextUniverse() {
        HashSet<Point> population = new HashSet<>(Arrays.asList(
                new Point(1, 1),
                new Point(2, 2)
        ));
        Universe universe = new Universe(population);
        Universe newUniverse = universe.transit();
        Assert.assertEquals(newUniverse, new Universe());
    }

    @Test
    public void given2or3NeighbourUniverseShouldReturnNextUniverse() {
        HashSet<Point> population = new HashSet<>(Arrays.asList(
                new Point(1, 1),
                new Point(1, 2),
                new Point(2, 1),
                new Point(2, 2)
        ));
        Universe universe = new Universe(population);
        Universe newUniverse = universe.transit();
        Assert.assertEquals(newUniverse, universe);
    }

    @Test
    public void givenMoreThank3NeighbourUniverseShouldReturnNextUniverse() {
        HashSet<Point> population = new HashSet<>(Arrays.asList(
                new Point(0, 1),
                new Point(1, 0),
                new Point(1, 1),
                new Point(1, 2),
                new Point(2, 1)
        ));
        HashSet<Point> expected = new HashSet<>(Arrays.asList(
                new Point(0, 1),
                new Point(1, 0),
                new Point(1, 2),
                new Point(2, 1)
        ));
        Universe universe = new Universe(population);
        Universe newUniverse = universe.transit();
        Assert.assertEquals(newUniverse, new Universe(expected));
    }

    @Test
    public void givenBlinkerPatternUniverseShouldReturnNextUniverse() {
        HashSet<Point> population = new HashSet<>(Arrays.asList(
                new Point(0, 1),
                new Point(1, 1),
                new Point(2, 1)
        ));
        HashSet<Point> expected = new HashSet<>(Arrays.asList(
                new Point(1, 0),
                new Point(1, 1),
                new Point(1, 2)
        ));
        Universe universe = new Universe(population);
        Universe newUniverse = universe.transit();
        Assert.assertEquals(newUniverse, new Universe(expected));
    }

}
