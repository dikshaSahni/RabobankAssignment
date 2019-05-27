/**
 * 
 */
package org.rabobank.process.records.controller;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import org.rabobank.process.records.exceptionHandling.CustomFileIOException;
import org.rabobank.process.records.service.CustomerRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

/**
 * @author diksha.sahni
 *
 */
@RestController
@RequestMapping(path = "/processRecords")
public class CustomerRecordsController {

	private final CustomerRecordsService customerStatmentService;

	@Autowired
	public CustomerRecordsController(final CustomerRecordsService customerStatmentService) {
		this.customerStatmentService = customerStatmentService;
	}

	@GetMapping()
	public ResponseEntity<Object> execute()
			throws CustomFileIOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException,
			InstantiationException, IllegalAccessException, IllegalStateException, JAXBException, FileNotFoundException, DocumentException {
		return customerStatmentService.processInputFiles();
	}
}
