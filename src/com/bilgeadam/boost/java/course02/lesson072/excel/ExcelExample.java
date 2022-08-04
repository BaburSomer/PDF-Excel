package com.bilgeadam.boost.java.course02.lesson072.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.Getter;

public class ExcelExample {
	private List<RowData> data = new ArrayList<>();

	public static void main(String[] args) {

		ExcelExample excel = new ExcelExample();
		excel.init();
		try {
			excel.create();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void init() {
		this.data.add(new RowData(1, "Gözde", LocalDate.of(1998, 11, 23), 36.7f));
		this.data.add(new RowData(21, "Cebrail", LocalDate.now(), 50.7f));
		this.data.add(new RowData(3, "Babür", LocalDate.now(), 10.7f));
	}

	private void create() throws Exception {
		@Cleanup
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet    sheet    = workbook.createSheet("Bilgiler");
		// sheet.setTabColor(PresetColor.Blue);
		sheet.setColumnWidth(3, 15 * 256);
		sheet.autoSizeColumn(2);

		XSSFRow row;
		int     rowCount = 0;
		String[] header = {"ID", "İsim", "Tarih", "Boy"};
		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setBorderBottom(BorderStyle.THICK);
		headerStyle.setBorderTop(BorderStyle.THICK);
		headerStyle.setBorderLeft(BorderStyle.THICK);
		headerStyle.setBorderRight(BorderStyle.THICK);
		headerStyle.setFillForegroundColor((short)200);
		headerStyle.setFillPattern(FillPatternType.LEAST_DOTS);
		headerStyle.setAlignment(HorizontalAlignment.CENTER);
		
		sheet.createRow(rowCount++);
		row = sheet.createRow(rowCount++);
		int     colCount = 0;
		row.createCell(colCount++);
		for (String column : header) {
			Cell cell = row.createCell(colCount++);
			cell.setCellValue(column);
			cell.setCellStyle(headerStyle);
		}
		
		CellStyle style = workbook.createCellStyle();
//		style.setDataFormat(BuiltinFormats.);
		for (RowData rowData : data) {
			row = sheet.createRow(rowCount++);
			colCount = 0;
			row.createCell(colCount++);
			Cell cell = row.createCell(colCount++);
			cell.setCellValue(rowData.getNumber());
			cell = row.createCell(colCount++);
			cell.setCellValue(rowData.getName());
			cell = row.createCell(colCount++, CellType.NUMERIC);
			Date date = Date.from(rowData.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
			cell.setCellValue(date);
			cell = row.createCell(colCount++);
			cell.setCellValue(rowData.getHeight());
		}
		
		@Cleanup
		FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\babur.somer\\OneDrive - BilgeAdam\\Boost\\my excel.xlsx"));
		workbook.write(fos);
	}

	@AllArgsConstructor
	@Getter
	private class RowData {
		private int       number;
		private String    name;
		private LocalDate date;
		private float     height;
	}
}
