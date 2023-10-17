public class SimulationEngine  {

//    public static void main(String[] args) {
//        int numDice = 3;         // Number of dice thrown
//        int targetValue = 4;     // Value of dice that needs to be rolled or higher
//        int numSimulations = 1000;  // Number of times to simulate
//        int numSides = 6;        // Number of sides on each die
//
//        int[] distribution = simulateDiceRolls(numDice, targetValue, numSimulations, numSides);
//
//        System.out.println("Distribution of results:");
//        System.out.println(Arrays.toString(distribution));
//    }

    private static int[] simulateDiceRollsD6 (int numDice, int targetValue, int numSimulations) {
        int numSides = 6;
        int[] distribution = new int[numSides + 1];  // Array to hold the distribution

        for (int i = 0; i < numSimulations; i++) {
            int total = 0;

            // Roll each die and calculate the total
            for (int j = 0; j < numDice; j++) {
                total += (int) (Math.random() * numSides) + 1;  // Simulate rolling a die
            }

            // Update the distribution based on the total
            if (total >= targetValue && total <= numSides) {
                distribution[total]++;
            }
        }

        return distribution;
    }

    private static int[] simulateDiceRolls(int numDice, int targetValue, int numSimulations, int numSides) {
        int[] distribution = new int[numSides + 1];  // Array to hold the distribution

        for (int i = 0; i < numSimulations; i++) {
            int total = 0;

            // Roll each die and calculate the total
            for (int j = 0; j < numDice; j++) {
                total += (int) (Math.random() * numSides) + 1;  // Simulate rolling a die
            }

            // Update the distribution based on the total
            if (total >= targetValue && total <= numSides) {
                distribution[total]++;
            }
        }

        return distribution;
    }
}