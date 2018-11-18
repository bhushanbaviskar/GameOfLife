package com.retreat;

import lombok.Builder;

import java.util.HashSet;
import java.util.Objects;

@Builder
public class Universe {

    private HashSet<Point> population;

    public Universe() {
        population = new HashSet<>();
    }

    public Universe(HashSet<Point> population) {
        this.population = population;
    }

    public Universe transit() {
        if (population.isEmpty())
            return this;
        if (population.size() == 1)
            return new Universe();

        HashSet<Point> newPopulation = new HashSet<>();
        for (Point life : population) {
            if (underPopulated(life)) continue;
            newPopulation.addAll(reproductionCheck(life));
            if (twoOrThreeNeighbours(life)) {
                newPopulation.add(life);
                continue;
            }
            if (moreThan3Neighbours(life)) continue;
            newPopulation.add(life);
        }
        return new Universe(newPopulation);
    }

    private HashSet<Point> reproductionCheck(Point life) {
        HashSet<Point> reproducedCells = new HashSet<>();
        HashSet<Point> allNeighbours = life.neighbours();
        for (Point cell : allNeighbours) {
            if (population.contains(cell)) continue;
            if (exactly3LiveNeighbours(cell)) reproducedCells.add(cell);
        }
        return reproducedCells;
    }

    private boolean exactly3LiveNeighbours(Point cell) {
        return cell.aliveNeighbours(population).size() == 3;
    }

    private boolean moreThan3Neighbours(Point life) {
        return life.aliveNeighbours(population).size() > 3;
    }

    private boolean twoOrThreeNeighbours(Point life) {
        return life.aliveNeighbours(population).size() == 2 || life.aliveNeighbours(population).size() == 3;
    }

    private boolean underPopulated(Point life) {
        return life.aliveNeighbours(population).size() < 2;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Universe universe = (Universe) o;
        return Objects.equals(population, universe.population);
    }

    @Override
    public int hashCode() {

        return Objects.hash(population);
    }

}
