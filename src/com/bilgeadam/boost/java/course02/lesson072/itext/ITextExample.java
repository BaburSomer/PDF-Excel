package com.bilgeadam.boost.java.course02.lesson072.itext;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ITextExample {
	private final String FONT = "C:\\Windows\\Fonts\\l_10646.ttf";
	private Document     pdf;
	private Font         font;
	private PdfWriter    writer;

	public static void main(String[] args) {
		ITextExample iText = new ITextExample();
		try {
			iText.createPDFFile();
			iText.pdf.open();
			iText.createPDF();
			iText.createImage();
			iText.createTable();
			iText.pdf.close();
			System.out.println("PDF başarılı bir şekilde yaratıldı");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void createTable() throws DocumentException {
		Font font = new Font(FontFamily.TIMES_ROMAN, 10f);
		
		PdfPTable table = new PdfPTable(2);
		table.setTotalWidth(new float[] {160f, 120f});
		table.setLockedWidth(true);
		
		PdfPCell cell = new PdfPCell(new Phrase("Ay text"));
		cell.setFixedHeight(30f);
		cell.setBorder(Rectangle.BOX);
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(cell);
		
		Barcode128 barCode = new Barcode128();
		barCode.setCodeType(Barcode128.CODE128);
		barCode.setCode("Babur Somer");
		PdfContentByte cb = this.writer.getDirectContent();
		Image img = barCode.createImageWithBarcode(cb, BaseColor.RED, null);
		
		cell = new PdfPCell(img);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Ay ay ay", font));
		cell.setFixedHeight(50f);
		cell.setBorder(Rectangle.BOX);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(cell);

		table.setSpacingBefore(10);
		table.setHorizontalAlignment(10);
		this.pdf.add(table);
	}

	private void createImage() throws MalformedURLException, IOException, DocumentException {
		String imagePath = "C:\\Users\\babur.somer\\OneDrive - BilgeAdam\\Boost\\Big Data and more.jpg";
		Image image = Image.getInstance(imagePath);
		image.setAlignment(Image.ALIGN_CENTER);
		image.setBorder(Image.BOX);
		image.setBorderColor(BaseColor.ORANGE);
		image.setBorderWidth(19.5f);
		image.scalePercent(30, 30);
		
		float width = image.getWidth() * 0.30f;
		float heigth = image.getHeight() * 0.30f;
		
		float pageWidth = PageSize.A4.getWidth();
		float pageHeight = PageSize.A4.getHeight();
		
		float xCoord = (pageWidth - width) / 2;
		float xYoord = (pageHeight - heigth) / 2;
		
		image.setAbsolutePosition(xCoord, xYoord);
		this.pdf.add(image);
	}

	private void createPDF() throws DocumentException {
		this.pdf.add(new Paragraph("Birgün okula giderken..."));
		this.pdf.add(new Paragraph("Herşeye dikkat ederken...", this.getFont()));
		this.pdf.add(new Paragraph("Bir kız çıktı karşıma...", this.getFont()));
		this.pdf.add(new Paragraph("", this.getFont()));

		this.pdf.addAuthor("Babür Somer");
		this.pdf.addCreationDate();
		this.pdf.addCreator("Automatically created by a program using IText package");
		this.pdf.addKeywords("pdf; java; bilgeadam");
	}

	private void createPDFFile() throws Exception {
		this.pdf = new Document(PageSize.A4, 20, 20, 20, 20);

		FileOutputStream fos = new FileOutputStream(
				"C:\\Users\\babur.somer\\OneDrive - BilgeAdam\\Boost\\IText Example.pdf");
		this.writer = PdfWriter.getInstance(this.pdf, fos);
//		this.writer.setEncryption("gizli".getBytes(), "mizli".getBytes(), 
//				PdfWriter.ALLOW_PRINTING |PdfWriter.ALLOW_COPY , 
//				PdfWriter.ENCRYPTION_AES_128);
		
	}

	private Font getFont() {
		if (this.font == null) {
			this.font = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			this.font.setSize(14.4f);
			this.font.setStyle("BOLD");
		}
		return this.font;
	}
}
