package com.sharko.daniel.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.charts.AxisCrosses;
import org.apache.poi.ss.usermodel.charts.AxisPosition;
import org.apache.poi.ss.usermodel.charts.ChartAxis;
import org.apache.poi.ss.usermodel.charts.ChartDataSource;
import org.apache.poi.ss.usermodel.charts.DataSources;
import org.apache.poi.ss.usermodel.charts.LegendPosition;
import org.apache.poi.ss.usermodel.charts.LineChartData;
import org.apache.poi.ss.usermodel.charts.ValueAxis;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.charts.XSSFChartLegend;

/**
 * 
 * @author Sharko Daniel
 *         <p>
 *         Class containing methods for working with Excel.
 *         </p>
 *
 */
public class MyExcelUtils {

	/**
	 * Writes data to Excel (.xls) file. File is named after the array filling
	 * type method.
	 * 
	 * @param fileName
	 *            Name of file to create.
	 * @param arrLength
	 *            Length of experimental array.
	 * @param time
	 *            Time of sorting.
	 * @param annotated
	 *            Set with sorting classes.
	 * @throws IOException
	 */
	protected static void toExcelWriterXLSX(String fileName, int arrLength, long time, Set<Class<?>> annotated)
			throws IOException {
		try {

			String path = fileName + ".xlsx";
			File file = new File(path);
			if (!file.exists()) {
				createFile(path);
			}

			FileInputStream inp = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(inp);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Cell cell0 = null;
			Cell cell1 = null;

			writeHeading(sheet, cell0, cell1, annotated.size(), annotated);

			writeData(sheet, cell0, cell1, annotated.size(), arrLength, time);

			inp.close();

			FileOutputStream outFile = new FileOutputStream(new File(path));
			try {
				workbook.write(outFile);
				workbook.close();
				outFile.close();
			} finally {
				try {
					outFile.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Creates line charts in every file.
	 * 
	 * @throws IOException
	 */
	protected static void createCharts(int exNumber) throws IOException {
		createChart("SORTED", exNumber);
		createChart("RANDOM_SORTED", exNumber);
		createChart("NEARLY_SORTED", exNumber);
		createChart("REVERSE_SORTED", exNumber);
	}

	/**
	 * Creates line chart in already existing excel file.
	 * 
	 * @param name
	 *            file name
	 * @throws IOException
	 */
	private static void createChart(String name, int exNumber) throws IOException {
		FileInputStream inp = new FileInputStream(name + ".xlsx");
		XSSFWorkbook my_workbook = new XSSFWorkbook(inp);
		XSSFSheet my_worksheet = my_workbook.getSheetAt(0);

		/*
		 * At the end of this step, we have a worksheet with test data, that we
		 * want to write into a chart
		 */
		/* Create a drawing canvas on the worksheet */
		XSSFDrawing xlsx_drawing = my_worksheet.createDrawingPatriarch();
		/* Define anchor points in the worksheet to position the chart */
		XSSFClientAnchor anchor = xlsx_drawing.createAnchor(7, 7, 7, 7, 7, 5, 20, 25);
		/* Create the chart object based on the anchor point */
		XSSFChart my_line_chart = xlsx_drawing.createChart(anchor);
		/*
		 * Define legends for the line chart and set the position of the legend
		 */
		XSSFChartLegend legend = my_line_chart.getOrCreateLegend();
		legend.setPosition(LegendPosition.BOTTOM);
		/* Create data for the chart */
		LineChartData data = my_line_chart.getChartDataFactory().createLineChartData();
		/* Define chart AXIS */
		ChartAxis bottomAxis = my_line_chart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);
		ValueAxis leftAxis = my_line_chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
		leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);
		/* Define Data sources for the chart */
		/* Set the right cell range that contain values for the chart */
		/* Pass the worksheet and cell range address as inputs */
		/*
		 * Cell Range Address is defined as First row, last row, first column,
		 * last column
		 */
		ChartDataSource<Number> xs = DataSources.fromNumericCellRange(my_worksheet,
				new CellRangeAddress(0, exNumber, 0, 0));
		ChartDataSource<Number> ys1 = DataSources.fromNumericCellRange(my_worksheet,
				new CellRangeAddress(0, exNumber, 1, 1));
		ChartDataSource<Number> ys2 = DataSources.fromNumericCellRange(my_worksheet,
				new CellRangeAddress(0, exNumber, 2, 2));
		ChartDataSource<Number> ys3 = DataSources.fromNumericCellRange(my_worksheet,
				new CellRangeAddress(0, exNumber, 3, 3));
		ChartDataSource<Number> ys4 = DataSources.fromNumericCellRange(my_worksheet,
				new CellRangeAddress(0, exNumber, 4, 4));
		ChartDataSource<Number> ys5 = DataSources.fromNumericCellRange(my_worksheet,
				new CellRangeAddress(0, exNumber, 5, 5));
		ChartDataSource<Number> ys6 = DataSources.fromNumericCellRange(my_worksheet,
				new CellRangeAddress(0, exNumber, 6, 6));
		/* Add chart data sources as data to the chart */
		data.addSeries(xs, ys1);
		data.addSeries(xs, ys2);
		data.addSeries(xs, ys3);
		data.addSeries(xs, ys4);
		data.addSeries(xs, ys5);
		data.addSeries(xs, ys6);
		/* Plot the chart with the inputs from data and chart axis */
		my_line_chart.plot(data, new ChartAxis[] { bottomAxis, leftAxis });
		/* Finally define FileOutputStream and write chart information */
		FileOutputStream fileOut = new FileOutputStream(name + ".xlsx");
		try {
			my_workbook.write(fileOut);
			my_workbook.close();
			fileOut.close();
		} finally {
			try {
				fileOut.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Creates file based on given path.
	 * 
	 * @param path
	 *            Path to file. (path+name+.extention)
	 * @throws FileNotFoundException
	 */
	private static void createFile(String path) throws FileNotFoundException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		workbook.createSheet("Experimetnal Data");
		FileOutputStream outFile = null;
		try {
			outFile = new FileOutputStream(new File(path));
			workbook.write(outFile);
			workbook.close();
			outFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Writes experiment data to file.
	 * 
	 * @param sheet
	 *            Sheet to work with.
	 * @param cell0
	 *            1st cell.
	 * @param cell1
	 *            2nd cell.
	 * @param methods
	 *            Methods number.
	 * @param arrLength
	 *            Experimental array length.
	 * @param time
	 *            Experiment time.
	 */
	private static void writeData(XSSFSheet sheet, Cell cell0, Cell cell1, int methods, int arrLength, long time) {
		if (sheet.getRow(sheet.getLastRowNum()) == null) {
			cell0 = sheet.createRow(sheet.getLastRowNum()).createCell(0);
			cell0.setCellValue(arrLength);
			cell1 = sheet.getRow(cell0.getRowIndex()).createCell(1);
			cell1.setCellValue(time);
		} else {
			if (sheet.getRow(sheet.getLastRowNum()).getLastCellNum() < (methods + 1)) {
				// cell0 = sheet.getRow(sheet.getLastRowNum())
				// .createCell((int)
				// sheet.getRow(sheet.getLastRowNum()).getLastCellNum());
				// Weird thing. ^createCell(short) is deprecated.
				// createCell(int) is recommended.
				// cell0.setCellValue(arrLength);
				cell1 = sheet.getRow(/* cell0.getRowIndex() */sheet.getLastRowNum())
						.createCell((int) sheet.getRow(sheet.getLastRowNum()).getLastCellNum());
				cell1.setCellValue(time);
			} else {
				cell0 = sheet.createRow(sheet.getLastRowNum() + 1).createCell(0);
				cell0.setCellValue(arrLength);
				cell1 = sheet.getRow(cell0.getRowIndex()).createCell(1);
				cell1.setCellValue(time);
			}
		}
	}

	/**
	 * Writes heading to the top of the file. Heading contains column names for
	 * Excel table.
	 * 
	 * @param sheet
	 *            Sheet to work with.
	 * @param cell0
	 *            1st cell.
	 * @param cell1
	 *            2nd cell.
	 * @param methods
	 *            Methods number.
	 * @param annotated
	 *            Sorting classes.
	 */
	private static void writeHeading(XSSFSheet sheet, Cell cell0, Cell cell1, int methods, Set<Class<?>> annotated) {
		cell0 = sheet.createRow(0).createCell(0);
		cell0.setCellValue("Array length");
		while (sheet.getRow(0).getLastCellNum() < (methods + 1)) {
			for (int i = 0; i < annotated.size(); i++) {
				cell1 = sheet.getRow(0).createCell((int) sheet.getRow(0).getLastCellNum());
				cell1.setCellValue(setElements(annotated)[i]);
				// sheet.autoSizeColumn(i);
			}
		}
	}

	/**
	 * Returns set as an array.
	 * 
	 * @param annotated
	 *            Set to convert.
	 * @return Set as array.
	 */
	private static String[] setElements(Set<Class<?>> annotated) {
		String[] arr = new String[annotated.size()];
		Iterator<Class<?>> it = annotated.iterator();
		// Having created an instance of Iterator we'll have another element of
		// set for every "for" loop
		for (int i = 0; i < annotated.size(); i++) {
			arr[i] = it.next().getSimpleName();
		}
		return arr;
	}

}
