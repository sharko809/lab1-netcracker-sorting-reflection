package com.sharko.daniel.main;

import com.sharko.daniel.sort.SortType;
import com.sharko.daniel.util.Util;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.charts.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.charts.XSSFChartLegend;

import java.io.*;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class containing methods for working with Excel.
 */
class MyExcelUtils {

    /**
     * Writes data to Excel (.xlsx) file. File is named after the array filling
     * type method.
     *
     * @param fileName  Name of file to create.
     * @param arrLength Length of experimental array.
     * @param time      Time of sorting.
     * @param annotated Set with sorting classes.
     */
    static synchronized void toExcelWriterXLSX(String fileName, int arrLength, long time, Set<Class<?>> annotated) {
        try {
            String path = fileName + Util.EXCEL_FILE_EXTENSION;
            File file = new File(path);
            if (!file.exists()) {
                createFile(path);
            }

            FileInputStream inp = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(inp);
            XSSFSheet sheet = workbook.getSheetAt(0);

            writeHeading(sheet, annotated.size(), annotated);
            writeData(sheet, annotated.size(), arrLength, time);

            inp.close();
            FileOutputStream outFile = new FileOutputStream(new File(path));
            writeAndClose(workbook, outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates line charts in every file.
     */
    static synchronized void createCharts(int exNumber) throws IOException {
        for (SortType sortType : SortType.values()) {
            createChart(sortType.toString(), exNumber);
        }
    }

    /**
     * Creates line chart in already existing excel file.
     *
     * @param name file name
     */
    private static synchronized void createChart(String name, int exNumber) throws IOException {
        File file = new File(name + Util.EXCEL_FILE_EXTENSION);
        FileInputStream inp = new FileInputStream(file);
        XSSFWorkbook myWorkbook = new XSSFWorkbook(inp);
        XSSFSheet myWorksheet = myWorkbook.getSheetAt(0);

		/*
         * At the end of this step, we have a worksheet with test data, that we
		 * want to write into a chart
		 */
        // Create a drawing canvas on the worksheet
        XSSFDrawing xlsxDrawing = myWorksheet.createDrawingPatriarch();
        // Define anchor points in the worksheet to position the chart
        XSSFClientAnchor anchor = xlsxDrawing.createAnchor(7, 7, 7, 7, 7, 5, 20, 25);
        // Create the chart object based on the anchor point
        XSSFChart myLineChart = xlsxDrawing.createChart(anchor);


        // Define legends for the line chart and set the position of the legend
        XSSFChartLegend legend = myLineChart.getOrCreateLegend();
        legend.setPosition(LegendPosition.BOTTOM);
        // Create data for the chart
        LineChartData data = myLineChart.getChartDataFactory().createLineChartData();
        // Define chart AXIS
        ChartAxis bottomAxis = myLineChart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);
        ValueAxis leftAxis = myLineChart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);
        /* Define Data sources for the chart */
        /* Set the right cell range that contain values for the chart */
        /* Pass the worksheet and cell range address as inputs */
        /*
         * Cell Range Address is defined as First row, last row, first column,
		 * last column
		 */
        ChartDataSource<Number> xs = DataSources.fromNumericCellRange(myWorksheet,
                new CellRangeAddress(0, exNumber, 0, 0));
        ChartDataSource<Number> ys1 = DataSources.fromNumericCellRange(myWorksheet,
                new CellRangeAddress(0, exNumber, 1, 1));
        ChartDataSource<Number> ys2 = DataSources.fromNumericCellRange(myWorksheet,
                new CellRangeAddress(0, exNumber, 2, 2));
        ChartDataSource<Number> ys3 = DataSources.fromNumericCellRange(myWorksheet,
                new CellRangeAddress(0, exNumber, 3, 3));
        ChartDataSource<Number> ys4 = DataSources.fromNumericCellRange(myWorksheet,
                new CellRangeAddress(0, exNumber, 4, 4));
        ChartDataSource<Number> ys5 = DataSources.fromNumericCellRange(myWorksheet,
                new CellRangeAddress(0, exNumber, 5, 5));
        ChartDataSource<Number> ys6 = DataSources.fromNumericCellRange(myWorksheet,
                new CellRangeAddress(0, exNumber, 6, 6));
        // Add chart data sources as data to the chart
        data.addSerie(xs, ys1).setTitle(myWorksheet.getRow(0).getCell(1).getStringCellValue());
        data.addSerie(xs, ys2).setTitle(myWorksheet.getRow(0).getCell(2).getStringCellValue());
        data.addSerie(xs, ys3).setTitle(myWorksheet.getRow(0).getCell(3).getStringCellValue());
        data.addSerie(xs, ys4).setTitle(myWorksheet.getRow(0).getCell(4).getStringCellValue());
        data.addSerie(xs, ys5).setTitle(myWorksheet.getRow(0).getCell(5).getStringCellValue());
        data.addSerie(xs, ys6).setTitle(myWorksheet.getRow(0).getCell(6).getStringCellValue());
        // Plot the chart with the inputs from data and chart axis
        myLineChart.plot(data, bottomAxis, leftAxis);
        // Finally define FileOutputStream and write chart information
        FileOutputStream fileOut = new FileOutputStream(name + Util.EXCEL_FILE_EXTENSION);
        writeAndClose(myWorkbook, fileOut);
    }

    /**
     * Creates file based on given path.
     *
     * @param path Path to file. (path+name+.extension)
     */
    private static synchronized void createFile(String path) throws FileNotFoundException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        workbook.createSheet("Experimental Data");
        FileOutputStream outFile = new FileOutputStream(new File(path));
        writeAndClose(workbook, outFile);
    }

    /**
     * Writes experiment data to file.
     *
     * @param sheet     Sheet to work with.
     * @param methods   Methods number.
     * @param arrLength Experimental array length.
     * @param time      Experiment time.
     */
    private static synchronized void writeData(XSSFSheet sheet, int methods, int arrLength, long time) {
        if (sheet.getRow(sheet.getLastRowNum()) == null) {
            Cell cell0 = sheet.createRow(sheet.getLastRowNum()).createCell(0);
            cell0.setCellValue(arrLength);
            Cell cell1 = sheet.getRow(cell0.getRowIndex()).createCell(1);
            cell1.setCellValue(time);
        } else {
            if (sheet.getRow(sheet.getLastRowNum()).getLastCellNum() < (methods + 1)) {
                Cell cell1 = sheet.getRow(sheet.getLastRowNum())
                        .createCell((int) sheet.getRow(sheet.getLastRowNum()).getLastCellNum());
                cell1.setCellValue(time);
            } else {
                Cell cell0 = sheet.createRow(sheet.getLastRowNum() + 1).createCell(0);
                cell0.setCellValue(arrLength);
                Cell cell1 = sheet.getRow(cell0.getRowIndex()).createCell(1);
                cell1.setCellValue(time);
            }
        }
    }

    /**
     * Writes heading to the top of the file. Heading contains column names for
     * Excel table.
     *
     * @param sheet     Sheet to work with.
     * @param methods   Methods number.
     * @param annotated Sorting classes.
     */
    private static synchronized void writeHeading(XSSFSheet sheet, int methods, Set<Class<?>> annotated) {
        Cell cell0 = sheet.createRow(0).createCell(0);
        cell0.setCellValue("Array length");
        while (sheet.getRow(0).getLastCellNum() < (methods + 1)) {
            for (int i = 0; i < annotated.size(); i++) {
                Cell cell1 = sheet.getRow(0).createCell((int) sheet.getRow(0).getLastCellNum());
                cell1.setCellValue(setElements(annotated)[i]);
                sheet.autoSizeColumn(i);
            }
        }
    }

    /**
     * Returns set of classes as an array of their names.
     *
     * @param set Set to convert.
     * @return Set as array.
     */
    private static synchronized String[] setElements(Set<Class<?>> set) {
        return set.stream().map(Class::getSimpleName).collect(Collectors.toSet()).toArray(new String[set.size()]);
    }

    /**
     * Writes data to file and closes output stream
     *
     * @param workbook workbook to write to
     * @param fileOut  file to write to
     */
    private static synchronized void writeAndClose(Workbook workbook, OutputStream fileOut) {
        try {
            workbook.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
