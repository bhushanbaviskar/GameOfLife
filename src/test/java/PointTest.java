import com.retreat.Point;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

public class PointTest {

    @Test
    public void givenPointShouldReturnAdjacentPoints() {
        Point life = new Point(2, 1);
        HashSet<Point> expected = new HashSet<>(Arrays.asList(
                new Point(1, 0),
                new Point(1, 1),
                new Point(1, 2),
                new Point(2, 0),
                new Point(2, 2),
                new Point(3, 0),
                new Point(3, 1),
                new Point(3, 2)
        ));

        HashSet<Point> neighbours = life.neighbours();
        Assert.assertEquals(expected, neighbours);
    }

    @Test
    public void givenOneLifeShouldReturnAliveNeighbours() {
        HashSet<Point> alive = new HashSet<>(Arrays.asList(
                new Point(2, 0),
                new Point(2, 1),
                new Point(3, 2)
        ));
        HashSet<Point> expected = new HashSet<>(Arrays.asList(
                new Point(2, 0),
                new Point(3, 2)
        ));

        Point life = new Point(2, 1);
        HashSet<Point> aliveNeighbours = life.aliveNeighbours(alive);
        Assert.assertEquals(expected, aliveNeighbours);
    }


}
