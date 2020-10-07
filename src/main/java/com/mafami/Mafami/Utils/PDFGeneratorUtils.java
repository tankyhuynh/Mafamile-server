package com.mafami.Mafami.Utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mafami.Mafami.Entity.MAFAMILE.CustomerEntity;
import com.mafami.Mafami.Entity.MAFAMILE.PostEntity;

@Component
public class PDFGeneratorUtils {

	public static ByteArrayInputStream customerPDFReport(List<PostEntity> posts) {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfWriter.getInstance(document, out);
			document.open();

			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
			Paragraph para = new Paragraph("Customer Table", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);

			PdfPTable table = new PdfPTable(5);
			// Add PDF Table Header ->
			Stream.of("ID", "Title", "Content", "Image", "Post By").forEach(headerTitle -> {
				PdfPCell header = new PdfPCell();
				Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
				header.setBackgroundColor(BaseColor.LIGHT_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_CENTER);
				header.setBorderWidth(2);
				header.setPhrase(new Phrase(headerTitle, headFont));
				table.addCell(header);
			});

			for (PostEntity post : posts) {
				PdfPCell idCell = new PdfPCell(new Phrase(post.getId().toString()));
				idCell.setPaddingLeft(6);
				idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				idCell.setBorderWidth(2);
				idCell.setUseVariableBorders(true);
				idCell.setBorderColorTop(BaseColor.GREEN);
				table.addCell(idCell);

				PdfPCell titleCell = new PdfPCell(new Phrase(post.getTitle()));
				titleCell.setPaddingLeft(6);
				titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				titleCell.setBorderWidth(2);
				titleCell.setUseVariableBorders(true);
				titleCell.setBorderColorTop(BaseColor.GREEN);
				table.addCell(titleCell);

				PdfPCell contentCell = new PdfPCell(new Phrase(String.valueOf(post.getContent())));
				contentCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				contentCell.setPaddingRight(6);
				contentCell.setBorderWidth(2);
				contentCell.setUseVariableBorders(true);
				contentCell.setBorderColorTop(BaseColor.GREEN);
				table.addCell(contentCell);

				PdfPCell imageCell = new PdfPCell(new Phrase(String.valueOf(post.getImages())));
				imageCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				imageCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				imageCell.setPaddingRight(6);
				imageCell.setBorderWidth(2);
				imageCell.setUseVariableBorders(true);
				imageCell.setBorderColorTop(BaseColor.GREEN);
				table.addCell(imageCell);

				PdfPCell usernameCell = new PdfPCell(new Phrase(String.valueOf(post.getUsername())));
				usernameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				usernameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				usernameCell.setBorderWidth(2);
				usernameCell.setPaddingRight(6);
				usernameCell.setUseVariableBorders(true);
				usernameCell.setBorderColorTop(BaseColor.GREEN);
				table.addCell(usernameCell);

			}
			document.add(table);

			document.close();
		} catch (DocumentException e) {
			System.out.println(e.toString());
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	public static void main(String[] args) throws DocumentException, Exception {

	}

}
