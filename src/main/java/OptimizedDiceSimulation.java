import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class OptimizedDiceSimulation {
    public static void main(String[] args) {
        int numDice = 10;              // Number of dice thrown
        int numSimulations = 20000000;  // Number of times to simulate
        int numThreads = 16;           // Number of threads to use

        long startTime = System.currentTimeMillis();

        int[][] simulationResults = new int[numSimulations][6];

        // Batch simulations for more efficient parallel processing
        List<Callable<Void>> tasks = new ArrayList<>();
        for (int i = 0; i < numSimulations; i++) {
            tasks.add(new SimulationTask(i, numDice, simulationResults));
        }

        // Create a thread pool
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        try {
            // Execute tasks and wait for all to complete
            executor.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

        long endTime = System.currentTimeMillis();

        // Aggregate the results
        double[] averageDistribution = averageResults(simulationResults, numSimulations);
        int[] totalOccurrences = totalOccurrences(simulationResults);

        System.out.println("Aggregate Distribution of Results:");
        for (int i = 0; i < 6; i++) {
            System.out.println("Number " + (i + 1) + ": " + averageDistribution[i] + " average occurrences, " +
                    totalOccurrences[i] + " total occurrences");
        }

        // Calculate and print the time taken
        long elapsedTime = endTime - startTime;
        System.out.println("Time taken: " + elapsedTime + " milliseconds");
    }

    private static int[] simulateDiceRolls(int numDice) {
        int[] distribution = new int[6];  // Array to hold the distribution

        for (int i = 0; i < numDice; i++) {
            int roll = (int) (Math.random() * 6) + 1;  // Simulate rolling a die
            distribution[roll - 1]++;  // Update the distribution based on the roll
        }

        return distribution;
    }

    private static double[] averageResults(int[][] results, int numSimulations) {
        double[] averageDistribution = new double[6];

        for (int i = 0; i < results.length; i++) {
            for (int j = 0; j < 6; j++) {
                averageDistribution[j] += results[i][j];
            }
        }

        // Calculate the average occurrences for each number
        for (int i = 0; i < 6; i++) {
            averageDistribution[i] /= numSimulations;
        }

        return averageDistribution;
    }

    private static int[] totalOccurrences(int[][] results) {
        int[] totalOccurrences = new int[6];

        for (int i = 0; i < results.length; i++) {
            for (int j = 0; j < 6; j++) {
                totalOccurrences[j] += results[i][j];
            }
        }

        return totalOccurrences;
    }

    private static class SimulationTask implements Callable<Void> {
        private int simulationIndex;
        private int numDice;
        private int[][] simulationResults;

        public SimulationTask(int simulationIndex, int numDice, int[][] simulationResults) {
            this.simulationIndex = simulationIndex;
            this.numDice = numDice;
            this.simulationResults = simulationResults;
        }

        @Override
        public Void call() {
            int[] distribution = simulateDiceRolls(numDice);
            simulationResults[simulationIndex] = distribution;
            return null;
        }
    }
}
