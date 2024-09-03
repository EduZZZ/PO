package com.po.methods;

public class Minimax {

    private int minimaxValue;

    public int minimax(int[][] utilities) {
        int numActions = utilities.length;

        int bestAction = -1;
        int maxMinUtility = Integer.MIN_VALUE;

        for (int i = 0; i < numActions; i++) {
            int minUtilityForAction = Integer.MAX_VALUE;
            for (int j = 0; j < utilities[i].length; j++) {
                if (utilities[i][j] < minUtilityForAction) {
                    minUtilityForAction = utilities[i][j];
                }
            }
            if (minUtilityForAction > maxMinUtility) {
                maxMinUtility = minUtilityForAction;
                bestAction = i;
            }
        }
        this.minimaxValue = maxMinUtility;
        return bestAction;
    }

    public int getMinimaxValue() {
        return minimaxValue;
    }
}
