package com.po.methods;

public class MaximizeExpectedUtility {

    private double expectedUtilityValue;

    public int maximizeExpectedUtility(int[][] utilities, double[] probabilities) {
        int numActions = utilities.length;

        int bestAction = -1;
        double maxExpectedUtility = Double.NEGATIVE_INFINITY;

        for (int i = 0; i < numActions; i++) {
            double expectedUtilityForAction = 0.0;

            for (int j = 0; j < utilities[i].length; j++) {
                expectedUtilityForAction += probabilities[j] * utilities[i][j];
            }

            if (expectedUtilityForAction > maxExpectedUtility) {
                maxExpectedUtility = expectedUtilityForAction;
                bestAction = i;
            }
        }
        this.expectedUtilityValue = maxExpectedUtility;
        return bestAction;
    }

    public double getExpectedUtilityValue() {
        return expectedUtilityValue;
    }
}
