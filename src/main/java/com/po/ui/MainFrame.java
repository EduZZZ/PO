package com.po.ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel filePanel;
    private JButton fileButton;
    private JLabel fileLabel;
    private JPanel hurwiczPanel;
    private JLabel hurwiczLabel;
    private JTextField hurwiczField;
    private JTextArea resultArea;
    private JScrollPane scrollPane;
    private JButton executeButton;
    private JPanel buttonPanel;

    public MainFrame() {
        super("DSTI - Tomada de Decisão sob Incerteza");

        // Configuração da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 450);
        setLayout(new BorderLayout());

        // Centralizar a janela na tela
        setLocationRelativeTo(null);

        // Inicializar os componentes
        filePanel = new JPanel();
        fileButton = new JButton("Carregar arquivo Excel");
        fileLabel = new JLabel("Nenhum arquivo selecionado.");
        filePanel.add(fileButton);
        filePanel.add(fileLabel);

        hurwiczPanel = new JPanel();
        hurwiczLabel = new JLabel("Coeficiente de Hurwicz (α): ");
        hurwiczField = new JTextField(4);  // Reduzindo o tamanho do campo
        hurwiczField.setText("0.85");  // Valor padrão
        hurwiczPanel.add(hurwiczLabel);
        hurwiczPanel.add(hurwiczField);

        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        scrollPane = new JScrollPane(resultArea);

        executeButton = new JButton("Executar Métodos");
        executeButton.setEnabled(false);

        buttonPanel = new JPanel();
        buttonPanel.add(executeButton);

        // Adicionar os componentes à interface
        add(filePanel, BorderLayout.NORTH);
        add(hurwiczPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Getters para os componentes
    public JButton getFileButton() {
        return fileButton;
    }

    public JLabel getFileLabel() {
        return fileLabel;
    }

    public JTextField getHurwiczField() {
        return hurwiczField;
    }

    public JTextArea getResultArea() {
        return resultArea;
    }

    public JButton getExecuteButton() {
        return executeButton;
    }
}
