package com.bilgeadam.boost.java.course02.lesson072.pdfbox;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;

import lombok.Cleanup;

public class PDFBoxExample {

	public static void main(String[] args) {
		PDFBoxExample pdfBox = new PDFBoxExample();

		try {
//			pdfBox.createPDF();
//			pdfBox.createPDFWithImage();
			pdfBox.readPDF();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void readPDF() throws Exception {
		String documentPath = "C:\\Users\\babur.somer\\OneDrive - BilgeAdam\\Boost\\10 - Examples\\Ödev.pdf";
		PDDocument doc = PDDocument.load(new File(documentPath));
		PDFTextStripper stripper = new PDFTextStripper();
		String text = stripper.getText(doc);
		System.out.println(text);
	}

	private void createPDFWithImage() throws Exception {
		
		String imagePath = "C:\\Users\\babur.somer\\OneDrive - BilgeAdam\\Boost\\Big Data and more.jpg";
		@Cleanup
		PDDocument pdf  = new PDDocument(); 
		PDPage     page = new PDPage();    
		pdf.addPage(page); 
		
		PDImageXObject image = PDImageXObject.createFromFile(imagePath, pdf);

		@Cleanup
		PDPageContentStream content = new PDPageContentStream(pdf, page);
		content.drawImage(image, 20f, 20f, image.getWidth()/2, image.getHeight()/3);

		content.beginText();
		content.setFont(PDType1Font.COURIER_BOLD, 14);
		content.setLeading(14.5f);
		content.newLineAtOffset(20, 750);
		content.showText("Bir gün okula giderken...");
		content.newLine();
		content.setFont(PDType1Font.COURIER, 12);
		content.showText("Herseye dikkat ederken...");
		content.newLine();
		content.showText("Bir kiz cikti karsima...");
		content.endText();
		content.close();
		pdf.save(new File("C:\\Users\\babur.somer\\OneDrive - BilgeAdam\\Boost\\PDFBOX Image Example.pdf"));
	}

	private void createPDF() throws Exception {
		@Cleanup
		PDDocument pdf  = new PDDocument(); // bir PDF belgesi yarattık
		PDPage     page = new PDPage();     // bir sayfa yarattık
		pdf.addPage(page); // yarattığımız sayfayı belgeye ekledik

		@Cleanup
		PDPageContentStream content = new PDPageContentStream(pdf, page);
		content.beginText();

		content.setFont(PDType1Font.COURIER_BOLD, 14);
		content.setLeading(14.5f);
		content.newLineAtOffset(20, 750);
		content.showText("Bir gün okula giderken...");
		content.newLine();
		content.setFont(PDType1Font.COURIER, 12);
		content.showText("Herseye dikkat ederken...");
		content.newLine();
		content.showText("Bir kiz cikti karsima...");
		content.endText();
		content.close();
		pdf.save(new File("C:\\Users\\babur.somer\\OneDrive - BilgeAdam\\Boost\\PDFBOX Example.ppp"));
	}

}
