package com.ionos.codingtask.path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

public class ShortestPathPerformanceTest {

  private static final int WARM_UP_ITERATIONS = 2;
  private static final int MAX_ITERATIONS = 15;
  private static final int MAX_SIZE = 1 << 12;

  private static final Map<Integer, List<Long>> totalTimes = new TreeMap<>();

  public static void main(String[] args) {
    for (int iteration = 0; iteration < WARM_UP_ITERATIONS; iteration++) {
      System.out.printf("Warm-up iteration %d:%n", iteration + 1);
      for (int size = 2; size <= MAX_SIZE; size <<= 1) {
        runTaskAndMeasure(size);
      }
      System.out.println();
    }

    totalTimes.clear();

    for (int iteration = 0; iteration < MAX_ITERATIONS; iteration++) {
      System.out.printf("Iteration %d:%n", iteration + 1);
      for (int size = 2; size <= MAX_SIZE; size <<= 1) {
        runTaskAndMeasure(size);
      }
      System.out.println();
    }

    System.out.println("Medians:");
    totalTimes.forEach(
        (size, times) ->
            System.out.printf(
                "  Size: %,5d --> median time: %,7.3f ms%n", size, median(times) / 1_000_000.0));
  }

  private static void runTaskAndMeasure(int size) {
    System.out.printf("Running for size %d...%n", size);

    System.out.println("  Creating random field...");
    int[][] field = createRandomField(size);

    System.out.println("  Finding shortest path...");
    long time = System.nanoTime();
    long totalCost = ShortestPathSolver.solve(field);
    time = System.nanoTime() - time;

    System.out.printf("  -> total cost = %d / time = %.3f ms%n", totalCost, time / 1_000_000.0);

    totalTimes.computeIfAbsent(size, ArrayList::new).add(time);
  }

  private static int[][] createRandomField(int size) {
    ThreadLocalRandom random = ThreadLocalRandom.current();
    int[][] field = new int[size][size];
    for (int x = 0; x < size; x++) {
      for (int y = 0; y < size; y++) {
        field[x][y] = random.nextInt(100);
      }
    }
    return field;
  }

  private static double median(List<Long> times) {
    Collections.sort(times);
    int size = times.size();
    int middle = size / 2;
    if (size % 2 != 0) {
      return times.get(middle);
    } else {
      return (times.get(middle - 1) + times.get(middle)) / 2.0;
    }
  }
}
