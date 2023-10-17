public class DiceSimulation {
    public static void main(String[] args) {
        int numDice = 10;        // Number of dice thrown
        int numSimulations = 2660000;  // Number of times to simulate

        long startTime = System.currentTimeMillis();

        int[][] simulationResults = new int[numSimulations][6];

        // Simulate rolling 10 dice numSimulations times
        for (int i = 0; i < numSimulations; i++) {
            int[] distribution = simulateDiceRolls(numDice);
            simulationResults[i] = distribution;
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
}
