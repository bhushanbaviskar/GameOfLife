package com.retreat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;

public class Point {
    final int x;
    final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public HashSet<Point> neighbours() {
        return new HashSet<>(Arrays.asList(
                new Point(this.x - 1, this.y - 1),
                new Point(this.x - 1, this.y),
                new Point(this.x - 1, this.y + 1),
                new Point(this.x, this.y - 1),
                new Point(this.x, this.y + 1),
                new Point(this.x + 1, this.y - 1),
                new Point(this.x + 1, this.y),
                new Point(this.x + 1, this.y + 1)
        ));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


    public HashSet<Point> aliveNeighbours(final HashSet<Point> population) {
        return neighbours().stream()
                .filter(point -> isAlive(point, population)).collect(Collectors.toCollection(HashSet::new));
    }

    private boolean isAlive(Point point, HashSet<Point> alive) {
        return alive.contains(point);
    }
}
