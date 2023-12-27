import java.util.Random;

/**
 * DiceGameSimulation simulates two dice games and calculates statistical measures.
 * Game 1: Win if you get a six in four rolls of one die.
 * Game 2: Win if you get double sixes in twenty-four rolls of two dice.
 */
public class DiceGameSimulation {
    private static final int TRIALS = 1_000_000; // Total number of trials for each game
    private static final Random random = new Random(); // Random number generator

    public static void main(String[] args) {
        double[] resultsGame1 = simulateGame1();
        double[] resultsGame2 = simulateGame2();

        // Displaying the results for each game
        System.out.println("Game 1: Mean = " + mean(resultsGame1) +
                           ", Variance = " + variance(resultsGame1) +
                           ", Standard Deviation = " + stdDev(resultsGame1));
        System.out.println("Game 2: Mean = " + mean(resultsGame2) +
                           ", Variance = " + variance(resultsGame2) +
                           ", Standard Deviation = " + stdDev(resultsGame2));
    }

    // Simulates the first game and returns the results
    private static double[] simulateGame1() {
        double[] results = new double[TRIALS];
        for (int i = 0; i < TRIALS; i++) {
            results[i] = (rollDie(4) ? 1 : -1); // Win $1 if six is rolled, else lose $1
        }
        return results;
    }

    // Simulates the second game and returns the results
    private static double[] simulateGame2() {
        double[] results = new double[TRIALS];
        for (int i = 0; i < TRIALS; i++) {
            results[i] = (rollDoubleSixes(24) ? 1 : -1); // Win $1 if double sixes are rolled, else lose $1
        }
        return results;
    }

    // Rolls a single die 'rolls' times, returns true if a six is rolled at least once
    private static boolean rollDie(int rolls) {
        for (int i = 0; i < rolls; i++) {
            if (random.nextInt(6) + 1 == 6) {
                return true;
            }
        }
        return false;
    }

    // Rolls two dice 'rolls' times, returns true if double sixes are rolled at least once
    private static boolean rollDoubleSixes(int rolls) {
        for (int i = 0; i < rolls; i++) {
            int die1 = random.nextInt(6) + 1;
            int die2 = random.nextInt(6) + 1;
            if (die1 == 6 && die2 == 6) {
                return true;
            }
        }
        return false;
    }

    // Calculates the mean of the given array of results
    private static double mean(double[] results) {
        double sum = 0;
        for (double d : results) {
            sum += d;
        }
        return sum / results.length;
    }

    // Calculates the variance of the given array of results
    private static double variance(double[] results) {
        double mean = mean(results);
        double sum = 0;
        for (double d : results) {
            sum += Math.pow(d - mean, 2);
        }
        return sum / results.length;
    }

    // Calculates the standard deviation of the given array of results
    private static double stdDev(double[] results) {
        return Math.sqrt(variance(results));
    }
}