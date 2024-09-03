package com.po.methods;

public class Hurwicz {

    private double hurwiczValue;

    public int hurwicz(int[][] utilities, double alpha) {
        int numActions = utilities.length;

        int bestAction = -1;
        double maxHurwiczValue = Double.NEGATIVE_INFINITY;

        for (int i = 0; i < numActions; i++) {
            int maxUtilityForAction = Integer.MIN_VALUE;
            int minUtilityForAction = Integer.MAX_VALUE;

            for (int j = 0; j < utilities[i].length; j++) {
                if (utilities[i][j] > maxUtilityForAction) {
                    maxUtilityForAction = utilities[i][j];
                }
                if (utilities[i][j] < minUtilityForAction) {
                    minUtilityForAction = utilities[i][j];
                }
            }

            double hurwiczValue = alpha * maxUtilityForAction + (1 - alpha) * minUtilityForAction;

            if (hurwiczValue > maxHurwiczValue) {
                maxHurwiczValue = hurwiczValue;
                bestAction = i;
            }
        }
        this.hurwiczValue = maxHurwiczValue;
        return bestAction;
    }

    public double getHurwiczValue() {
        return hurwiczValue;
    }
}
