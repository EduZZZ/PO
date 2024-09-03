package com.po;

import com.po.listeners.ExecuteButtonListener;
import com.po.listeners.FileButtonListener;
import com.po.ui.MainFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {

    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Aplicação iniciada.");

        // Cria a interface gráfica
        MainFrame frame = new MainFrame();

        // Cria os listeners
        FileButtonListener fileButtonListener = new FileButtonListener(frame);
        ExecuteButtonListener executeButtonListener = new ExecuteButtonListener(frame, fileButtonListener);

        // Associa os listeners aos botões
        frame.getFileButton().addActionListener(fileButtonListener);
        frame.getExecuteButton().addActionListener(executeButtonListener);

        // Exibe a interface
        frame.setVisible(true);

        logger.info("Aplicação finalizada.");
    }
}
