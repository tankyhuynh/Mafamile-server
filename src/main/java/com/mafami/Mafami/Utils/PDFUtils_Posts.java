package com.mafami.Mafami.Utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mafami.Mafami.Entity.PostEntity;

/**
* @author root {4:49:19 PM}:
 * @version Creation time: Oct 7, 2020 4:49:19 PM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */

@Component
public class PDFUtils_Posts<T> {

	public static File fontFile = new File("/vuArial.ttf");

	
	public static <T> ByteArrayInputStream toPDF(String[] fieldName, List<PostEntity> postEntities, String site) throws Exception {
		Document document = new Document();
		document.setPageSize(PageSize.A2);
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfWriter.getInstance(document, out);
			document.open();
			
			// Add Text to PDF file ->
			BaseFont bf = BaseFont.createFont(fontFile.getPath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	        Font font = new Font(bf,15);
	        Font titleFont = new Font(bf,25);
	        titleFont.setColor(BaseColor.RED);
	        titleFont.setStyle(titleFont.BOLD);
			Paragraph para = new Paragraph("Thống kê bài viết trên " + site, titleFont);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(fieldName.length);
			table.setWidthPercentage(100);
			// Add PDF Table Header ->
			Stream.of(fieldName).forEach(headerTitle -> {
				PdfPCell header = new PdfPCell();
				Font headerFont = new Font(bf,15);
				headerFont.setStyle(headerFont.BOLD);
				header.setBackgroundColor(BaseColor.LIGHT_GRAY);
				header.setBorderWidth(2);
				header.setPadding(5);
				header.setHorizontalAlignment(Element.ALIGN_CENTER);
				header.setPhrase(new Phrase(headerTitle, headerFont));
				table.addCell(header);
			});

			
			
			for (PostEntity post : postEntities) {
				
				
				PdfPCell idCell = new PdfPCell(new Phrase(  post.getId() , font));
				idCell.setPaddingLeft(6);
				idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				idCell.setBorderWidth(2);
				table.addCell(idCell);
				
				String title = (post.getTitle() != null) ? (post.getTitle()) : " ";
				PdfPCell titleCell = new PdfPCell(new Phrase( title , font));
				titleCell.setPaddingLeft(6);
				titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				titleCell.setBorderWidth(2);
				table.addCell(titleCell);
				
				String content = (post.getContent() != null) ? (post.getContent()) : " ";
				PdfPCell contentCell = new PdfPCell(new Phrase( content , font));
				contentCell.setPaddingLeft(6);
				contentCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				contentCell.setBorderWidth(2);
				table.addCell(contentCell);

				
				PdfPCell timeCell = new PdfPCell(new Phrase(String.valueOf(post.getTime()), font));
				timeCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				timeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				timeCell.setPaddingRight(6);
				timeCell.setBorderWidth(2);
				table.addCell(timeCell);

				
				String author = (post.getAuthor().getUsername() != null) ? (post.getAuthor().getUsername()) : " ";
				PdfPCell authorCell = new PdfPCell(new Phrase( author , font));
				authorCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				authorCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				authorCell.setBorderWidth(2);
				authorCell.setPaddingRight(6);
				table.addCell(authorCell);
				
				
				String thumbnail = (post.getThumbnail() != null) ? (post.getThumbnail()) : " ";
				PdfPCell thumbnailCell = new PdfPCell(new Phrase(String.valueOf( thumbnail ), font));
				thumbnailCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				thumbnailCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				thumbnailCell.setBorderWidth(2);
				thumbnailCell.setPaddingRight(6);
				table.addCell(thumbnailCell);

				
				String preview = (post.getPreview() != null) ? (post.getPreview()) : " ";
				PdfPCell previewCell = new PdfPCell(new Phrase( preview , font ));
				previewCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				previewCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				previewCell.setBorderWidth(2);
				previewCell.setPaddingRight(6);
				table.addCell(previewCell);

				
//				PdfPCell siteCell = new PdfPCell(new Phrase(String.valueOf( post.getSite() ), font));
//				siteCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//				siteCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//				siteCell.setBorderWidth(2);
//				siteCell.setPaddingRight(6);
//				table.addCell(siteCell);
				
				//{"id", "title", "content", "time", "author", "thumbnail", "preview", "site"};

			}
			document.add(table);

			document.close();
		} catch (DocumentException e) {
			System.out.println("Xay ra Exeption "+e.toString());
		}
		

		return new ByteArrayInputStream(out.toByteArray());
	}
	
	
	public static void analyze(Object obj){
	    ReflectionUtils.doWithFields(obj.getClass(), field -> {

	        System.out.print("Field name: " + field.getName());
	        field.setAccessible(true);
	        System.out.println("\tField value: "+ field.get(obj));

	    });
	}
	
	
	
	 public static PdfPCell newCell(String text, Font font) {
		    PdfPCell pdfCell = new PdfPCell();
		    PdfPCell cell = new PdfPCell();

		    if (text == null) {
		      // create a blank cell
		      cell = new PdfPCell(new Phrase(" ", font));
		    }
		    else {
		      cell = new PdfPCell(new Phrase(text, font));
		    }

		    pdfCell = new PdfPCell(cell);

		    return pdfCell;
		  }

	public static void main(String[] args) throws DocumentException, Exception {

		
	}
	
}
