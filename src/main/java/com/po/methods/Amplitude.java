package com.po.methods;

public class Amplitude {

    private double amplitudeRatio;

    public int amplitude(int[][] utilities) {
        int numActions = utilities.length;
        int numEvents = utilities[0].length;

        int bestAction = -1;
        double maxAmplitudeRatio = Double.NEGATIVE_INFINITY;

        for (int i = 0; i < numActions; i++) {
            int maxUtilityForAction = Integer.MIN_VALUE;
            int minUtilityForAction = Integer.MAX_VALUE;
            double sumUtilitiesForAction = 0.0;

            // Calcula o máximo, mínimo e soma para a ação atual
            for (int j = 0; j < numEvents; j++) {
                if (utilities[i][j] > maxUtilityForAction) {
                    maxUtilityForAction = utilities[i][j];
                }
                if (utilities[i][j] < minUtilityForAction) {
                    minUtilityForAction = utilities[i][j];
                }
                sumUtilitiesForAction += utilities[i][j];
            }

            double averageUtilityForAction = sumUtilitiesForAction / numEvents;  // Média
            double amplitudeForAction = maxUtilityForAction - minUtilityForAction;  // Amplitude

            // Calcula a métrica (média / amplitude)
            double amplitudeRatio = averageUtilityForAction / amplitudeForAction;

            if (amplitudeRatio > maxAmplitudeRatio) {  // Procuramos maximizar a razão
                maxAmplitudeRatio = amplitudeRatio;
                bestAction = i;
            }
        }
        this.amplitudeRatio = maxAmplitudeRatio;
        return bestAction;
    }

    public double getAmplitudeValue() {
        return amplitudeRatio;
    }
}
