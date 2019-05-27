package org.rabobank.process.records.exceptionHandling;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.itextpdf.text.DocumentException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@ControllerAdvice
public class CustomerRecordsExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CustomFileIOException.class)
	public final ResponseEntity<Object> handleCustomIOException(CustomFileIOException ioException) {
		ErrorResponse responseMsg;
		if(!ioException.getErrorCode().isEmpty()) {
			responseMsg = new ErrorResponse(ioException.getErrorCode(), ioException.getMessage());
		}else {
			responseMsg = new ErrorResponse("Unexpected Exception Occured in file read/write", ioException.getMessage());
		}
		return new ResponseEntity<>(responseMsg, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CsvRequiredFieldEmptyException.class)
	public final ResponseEntity<Object> handleCsvRequiredFieldEmptyException(CsvRequiredFieldEmptyException exception) {
		ErrorResponse responseMsg;
		responseMsg = new ErrorResponse("Mandatory filed not found", exception.getMessage());
		return new ResponseEntity<>(responseMsg, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(CsvDataTypeMismatchException.class)
	public final ResponseEntity<Object> handleCsvDataTypeMismatchException(CsvDataTypeMismatchException exception) {
		ErrorResponse responseMsg;
		responseMsg = new ErrorResponse("Data Type Mismatched", exception.getMessage());
		return new ResponseEntity<>(responseMsg, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(JAXBException.class)
	public final ResponseEntity<Object> handleJAXBException(JAXBException exception) {
		ErrorResponse responseMsg;
		responseMsg = new ErrorResponse("Error with XML Document Parsing", exception.getMessage());
		return new ResponseEntity<>(responseMsg, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(FileNotFoundException.class)
	public final ResponseEntity<Object> handleFileNotFoundException(FileNotFoundException exception) {
		ErrorResponse responseMsg;
		responseMsg = new ErrorResponse("File not found", exception.getMessage());
		return new ResponseEntity<>(responseMsg, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(DocumentException.class)
	public final ResponseEntity<Object> handleDocumentException(DocumentException exception) {
		ErrorResponse responseMsg;
		responseMsg = new ErrorResponse("Error generating PDF Document", exception.getMessage());
		return new ResponseEntity<>(responseMsg, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
