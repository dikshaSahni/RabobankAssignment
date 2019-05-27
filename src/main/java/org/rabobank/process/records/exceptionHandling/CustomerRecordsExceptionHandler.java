package org.rabobank.process.records.exceptionHandling;

import javax.xml.bind.JAXBException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@RestControllerAdvice
public class CustomerRecordsExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CustomFileIOException.class)
	public final ResponseEntity<Object> handleCustomIOException(CustomFileIOException ioException) {
		ErrorResponse responseMsg;
		if(!ioException.getErrorCode().isEmpty()) {
			responseMsg = new ErrorResponse(ioException.getErrorCode(), ioException.getMessage());
		}else {
			responseMsg = new ErrorResponse("Exception Occured in file read/write", ioException.getMessage());
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
}
