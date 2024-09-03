package com.po.methods;

public class MinimaxRegret {

    private int minimaxRegretValue;

    public int minimaxRegret(int[][] utilities) {
        int numActions = utilities.length;
        int numEvents = utilities[0].length;

        int[][] regrets = new int[numActions][numEvents];

        for (int j = 0; j < numEvents; j++) {
            int maxUtilityForEvent = Integer.MIN_VALUE;

            for (int i = 0; i < numActions; i++) {
                if (utilities[i][j] > maxUtilityForEvent) {
                    maxUtilityForEvent = utilities[i][j];
                }
            }

            for (int i = 0; i < numActions; i++) {
                regrets[i][j] = maxUtilityForEvent - utilities[i][j];
            }
        }

        int bestAction = -1;
        int minMaxRegret = Integer.MAX_VALUE;

        for (int i = 0; i < numActions; i++) {
            int maxRegretForAction = Integer.MIN_VALUE;

            for (int j = 0; j < numEvents; j++) {
                if (regrets[i][j] > maxRegretForAction) {
                    maxRegretForAction = regrets[i][j];
                }
            }

            if (maxRegretForAction < minMaxRegret) {
                minMaxRegret = maxRegretForAction;
                bestAction = i;
            }
        }
        this.minimaxRegretValue = minMaxRegret;
        return bestAction;
    }

    public int getMinimaxRegretValue() {
        return minimaxRegretValue;
    }
}
