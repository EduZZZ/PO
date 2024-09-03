package com.po.listeners;

import com.po.methods.*;
import com.po.ui.MainFrame;
import com.po.utilities.ExcelUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ExecuteButtonListener implements ActionListener {

    private static final Logger logger = LogManager.getLogger(ExecuteButtonListener.class);
    private MainFrame frame;
    private FileButtonListener fileButtonListener;

    public ExecuteButtonListener(MainFrame frame, FileButtonListener fileButtonListener) {
        this.frame = frame;
        this.fileButtonListener = fileButtonListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Carregar matriz de utilidades do Excel
            logger.info("Tentando carregar a matriz de utilidades do Excel...");
            int[][] utilities = ExcelUtils.loadMatrixFromExcel(fileButtonListener.getExcelFilePath());

            // Verificar se a matriz foi carregada corretamente
            if (utilities == null || utilities.length == 0) {
                logger.error("Matriz de utilidades está vazia ou não foi carregada corretamente.");
                JOptionPane.showMessageDialog(frame, "Erro ao carregar a matriz de utilidades. Verifique o arquivo Excel.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Logar o conteúdo da matriz carregada
            logger.info("Matriz de utilidades carregada:");
            for (int i = 0; i < utilities.length; i++) {
                StringBuilder row = new StringBuilder();
                for (int j = 0; j < utilities[i].length; j++) {
                    row.append(utilities[i][j]).append(" ");
                }
                logger.info(row.toString());
            }

            // Criar instâncias das classes de métodos
            Maximax maximax = new Maximax();
            Minimax minimax = new Minimax();
            MaximizeExpectedUtility laplace = new MaximizeExpectedUtility();
            Amplitude amplitude = new Amplitude();
            MinimaxRegret minimaxRegret = new MinimaxRegret();
            Hurwicz hurwicz = new Hurwicz();

            // Probabilidades dos eventos - Laplace usa probabilidades iguais
            double[] equalProbabilities = new double[utilities[0].length];
            for (int i = 0; i < equalProbabilities.length; i++) {
                equalProbabilities[i] = 1.0 / utilities[0].length;
            }

            // Obtém o valor do coeficiente de Hurwicz da interface
            double alpha;
            try {
                alpha = Double.parseDouble(frame.getHurwiczField().getText());
                if (alpha < 0 || alpha > 1) {
                    throw new NumberFormatException("O valor de α deve estar entre 0 e 1.");
                }
            } catch (NumberFormatException ex) {
                logger.error("Valor inválido para o coeficiente de Hurwicz: " + frame.getHurwiczField().getText(), ex);
                JOptionPane.showMessageDialog(frame, "Por favor, insira um valor válido para α (0 ≤ α ≤ 1).", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Chama os métodos e obtém os resultados
            try {
                int bestActionMaximax = maximax.maximax(utilities);
                int maximaxValue = maximax.getMaximaxValue();
                logger.info("Maximax: Melhor Ação = " + bestActionMaximax + ", Valor = " + maximaxValue);

                int bestActionMinimax = minimax.minimax(utilities);
                int minimaxValue = minimax.getMinimaxValue();
                logger.info("Minimax: Melhor Ação = " + bestActionMinimax + ", Valor = " + minimaxValue);

                int bestActionLaplace = laplace.maximizeExpectedUtility(utilities, equalProbabilities);
                double laplaceValue = laplace.getExpectedUtilityValue();
                logger.info("Laplace: Melhor Ação = " + bestActionLaplace + ", Valor = " + laplaceValue);

                int bestActionAmplitude = amplitude.amplitude(utilities);
                double amplitudeValue = amplitude.getAmplitudeValue();
                logger.info("Amplitude: Melhor Ação = " + bestActionAmplitude + ", Valor = " + amplitudeValue);

                int bestActionMinimaxRegret = minimaxRegret.minimaxRegret(utilities);
                int minimaxRegretValue = minimaxRegret.getMinimaxRegretValue();
                logger.info("Minimax Regret: Melhor Ação = " + bestActionMinimaxRegret + ", Valor = " + minimaxRegretValue);

                int bestActionHurwicz = hurwicz.hurwicz(utilities, alpha);
                double hurwiczValue = hurwicz.getHurwiczValue();
                logger.info("Hurwicz: Melhor Ação = " + bestActionHurwicz + ", Valor = " + hurwiczValue);

                // Comparar todos os valores e determinar a ação mais vantajosa
                int[] actions = {bestActionMaximax, bestActionMinimax, bestActionLaplace, bestActionAmplitude, bestActionMinimaxRegret, bestActionHurwicz};
                double[] values = {maximaxValue, minimaxValue, laplaceValue, amplitudeValue, minimaxRegretValue, hurwiczValue};

                double maxValue = values[0];
                int bestOverallAction = actions[0];

                for (int i = 1; i < values.length; i++) {
                    if (values[i] > maxValue) {
                        maxValue = values[i];
                        bestOverallAction = actions[i];
                    }
                }
                logger.info("Ação mais vantajosa: " + bestOverallAction + " com valor: " + maxValue);

                // Exibe os resultados na área de texto
                frame.getResultArea().setText(""); // Limpar resultados anteriores

                frame.getResultArea().append("==== Resultados dos Métodos de Decisão ====\n\n");

                frame.getResultArea().append("Critério Maximax:\n");
                frame.getResultArea().append("• Melhor Ação: " + (bestActionMaximax + 1) + "\n");
                frame.getResultArea().append("• Valor: " + maximaxValue + "\n\n");

                frame.getResultArea().append("Critério Minimax:\n");
                frame.getResultArea().append("• Melhor Ação: " + (bestActionMinimax + 1) + "\n");
                frame.getResultArea().append("• Valor: " + minimaxValue + "\n\n");

                frame.getResultArea().append("Critério de Laplace:\n");
                frame.getResultArea().append("• Melhor Ação: " + (bestActionLaplace + 1) + "\n");
                frame.getResultArea().append("• Valor: " + laplaceValue + "\n\n");

                frame.getResultArea().append("Critério de Amplitude:\n");
                frame.getResultArea().append("• Melhor Ação: " + (bestActionAmplitude + 1) + "\n");
                frame.getResultArea().append("• Valor: " + amplitudeValue + "\n\n");

                frame.getResultArea().append("Critério Minimax de Arrependimento:\n");
                frame.getResultArea().append("• Melhor Ação: " + (bestActionMinimaxRegret + 1) + "\n");
                frame.getResultArea().append("• Valor: " + minimaxRegretValue + "\n\n");

                frame.getResultArea().append("Critério Hurwicz (com α = " + alpha + "):\n");
                frame.getResultArea().append("• Melhor Ação: " + (bestActionHurwicz + 1) + "\n");
                frame.getResultArea().append("• Valor: " + hurwiczValue + "\n\n");

                frame.getResultArea().append("==========================================\n");
                frame.getResultArea().append(">>> A ação mais vantajosa é a " + (bestOverallAction + 1) + " com valor: " + maxValue + "\n");
                frame.getResultArea().append("==========================================\n");

                logger.info("Resultados exibidos na interface com sucesso.");
            } catch (Exception ex) {
                logger.error("Erro ao executar os métodos de decisão.", ex);
                JOptionPane.showMessageDialog(frame, "Erro ao executar os métodos de decisão.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            logger.error("Erro ao carregar o arquivo Excel.", ex);
            frame.getResultArea().setText("Erro ao carregar o arquivo Excel.");
        } catch (Exception ex) {
            logger.error("Erro inesperado ao executar os métodos.", ex);
            JOptionPane.showMessageDialog(frame, "Erro inesperado ao executar os métodos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
