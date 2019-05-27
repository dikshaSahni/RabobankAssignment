package org.rabobank.process.records.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Stream;

import org.rabobank.process.records.model.Records.Record;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReportGeneratorService {

	private static final String[] FILE_HEADER = {"Reference", "Description"};

	public void writeFile(List<Record> recordList, String filePath)
			throws DocumentException, FileNotFoundException {
		
		Document document = createDocument(filePath);
		document.open();
		
		PdfPTable table = new PdfPTable(2);
		addTableHeader(table);
		
		for(Record record : recordList) {
			addRows(table, record);
		}
		document.add(table);
		document.close();
	}

	private Document createDocument(String filePath) throws FileNotFoundException, DocumentException {
		Document document = new Document();
		String reportfilename = filePath + "/report_" + System.currentTimeMillis() + ".pdf";
		PdfWriter.getInstance(document, new FileOutputStream(reportfilename));
		return document;
	}

	private void addTableHeader(PdfPTable table) {
		Stream.of(FILE_HEADER).forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}

	private void addRows(PdfPTable table, Record record) {
		table.addCell(String.valueOf(record.getReference()));
		table.addCell(record.getDescription());
		table.completeRow();
	}

}
