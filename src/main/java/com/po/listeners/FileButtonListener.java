package com.po.listeners;

import com.po.ui.MainFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileButtonListener implements ActionListener {

    private static final Logger logger = LogManager.getLogger(FileButtonListener.class);
    private MainFrame frame;
    private String excelFilePath;

    public FileButtonListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                excelFilePath = selectedFile.getAbsolutePath();
                frame.getFileLabel().setText(selectedFile.getName());
                frame.getExecuteButton().setEnabled(true);  // Ativa o botão de execução
                logger.info("Arquivo Excel carregado: " + excelFilePath);
            }
        } catch (Exception ex) {
            logger.error("Erro ao carregar o arquivo Excel", ex);
            JOptionPane.showMessageDialog(frame, "Erro ao carregar o arquivo Excel.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String getExcelFilePath() {
        return excelFilePath;
    }
}
