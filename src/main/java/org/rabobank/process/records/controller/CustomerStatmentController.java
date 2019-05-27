/**
 * 
 */
package org.rabobank.process.records.controller;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.rabobank.process.records.exceptionHandling.CustomFileIOException;
import org.rabobank.process.records.service.CustomerRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

/**
 * @author diksha.sahni
 *
 */
@RestController
@RequestMapping(path = "/processRecords")
public class CustomerStatmentController {

	private final CustomerRecordsService customerStatmentService;

	@Autowired
	public CustomerStatmentController(final CustomerRecordsService customerStatmentService) {
		this.customerStatmentService = customerStatmentService;
	}

	@GetMapping()
	public ResponseEntity<Object> execute() throws CustomFileIOException, ParserConfigurationException, SAXException,
			CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, JAXBException {
		return customerStatmentService.processInputFiles();
	}
}
