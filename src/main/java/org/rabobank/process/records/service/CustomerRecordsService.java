/**
 * 
 */
package org.rabobank.process.records.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.rabobank.process.records.exceptionHandling.CustomFileIOException;
import org.rabobank.process.records.exceptionHandling.ErrorCodes;
import org.rabobank.process.records.model.Records.Record;
import org.rabobank.process.records.utils.CSVFileProcessor;
import org.rabobank.process.records.utils.XMLFileProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.itextpdf.text.DocumentException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author diksha.sahni
 *
 */
@Service
@Slf4j
public class CustomerRecordsService {

	@Value("${csvFileDirectory.path}")
	private String pathOfExcelRecord;
	@Value("${xmlFileDirectory.path}")
	private String pathOfXmlRecord;
	@Value("${outputDirectory.path}")
	private String pathOfOutputRecord;

	private List<Record> failedRecordsList;

	@Autowired
	ReportGeneratorService reportGenerator;

	@Autowired
	CSVFileProcessor csvProcessor;

	@Autowired
	XMLFileProcessor xmlProcessor;

	/**
	 * This method processes both the input files and generates failure report
	 * 
	 * @throws CustomFileIOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws JAXBException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws CsvDataTypeMismatchException
	 * @throws IllegalStateException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws DocumentException 
	 * @throws FileNotFoundException 
	 */
	public ResponseEntity<Object> processInputFiles()
			throws CustomFileIOException, JAXBException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException,
			FileNotFoundException, DocumentException {

		failedRecordsList = new ArrayList<>();
		try {

			// process CSV files
			Files.list(Paths.get(pathOfExcelRecord)).filter(path -> path.toString().endsWith(".csv"))
					.forEach(csvPath -> {
						try {
							failedRecordsList = csvProcessor.processCsvRecords(csvPath, failedRecordsList);
						} catch (CustomFileIOException exception) {
							throw new RuntimeException(exception);
						}
					});

			// process XML files
			Files.list(Paths.get(pathOfXmlRecord)).filter(path -> path.toString().endsWith(".xml"))
					.forEach(xmlPath -> {
						try {
							failedRecordsList = xmlProcessor.processXmlRecords(xmlPath, failedRecordsList);
						} catch (CustomFileIOException | JAXBException exception) {
							throw new RuntimeException(exception);
						}
					});

		} catch (IOException ioException) {
			throw new CustomFileIOException("Error finding file Path", ioException,
					ErrorCodes.ERROR_RETRIEVING_FILE_PATH);
		}

		// generateFinalReport
		reportGenerator.writeFile(failedRecordsList, pathOfOutputRecord);
		
		return new ResponseEntity<>("Files processed and generated successfully", HttpStatus.OK);
	}
}
