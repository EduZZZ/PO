package com.po.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    public static int[][] loadMatrixFromExcel(String filePath) throws IOException {
        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0);
            int maxRow = 0;
            int maxCol = 0;

            // Determina o tamanho máximo da matriz com base nos dados
            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    maxRow = i + 1; // +1 porque as linhas são indexadas a partir de 0
                    int currentColCount = row.getPhysicalNumberOfCells();
                    if (currentColCount > maxCol) {
                        maxCol = currentColCount;
                    }
                }
            }

            // Agora criamos a matriz com os tamanhos encontrados
            int[][] matrix = new int[maxRow][maxCol];

            // Preenche a matriz com os dados da planilha
            for (int i = 0; i < maxRow; i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    for (int j = 0; j < maxCol; j++) {
                        Cell cell = row.getCell(j);
                        if (cell != null) {
                            matrix[i][j] = (int) cell.getNumericCellValue();
                        } else {
                            matrix[i][j] = 0; // Preenche com 0 se a célula estiver vazia
                        }
                    }
                }
            }

            return matrix;
        }
    }
}
