package com.po.methods;

public class Maximax {

    private int maximaxValue;

    public int maximax(int[][] utilities) {
        int numActions = utilities.length;

        int bestAction = -1;
        int maxUtility = Integer.MIN_VALUE;

        for (int i = 0; i < numActions; i++) {
            int maxUtilityForAction = Integer.MIN_VALUE;

            for (int j = 0; j < utilities[i].length; j++) {
                if (utilities[i][j] > maxUtilityForAction) {
                    maxUtilityForAction = utilities[i][j];
                }
            }

            if (maxUtilityForAction > maxUtility) {
                maxUtility = maxUtilityForAction;
                bestAction = i;
            }
        }
        this.maximaxValue = maxUtility;
        return bestAction;
    }

    public int getMaximaxValue() {
        return maximaxValue;
    }
}
