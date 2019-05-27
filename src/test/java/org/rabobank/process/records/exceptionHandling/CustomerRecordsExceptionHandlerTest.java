/**
 * 
 */
package org.rabobank.process.records.exceptionHandling;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

/**
 * @author diksha.sahni
 *
 */
public class CustomerRecordsExceptionHandlerTest {

	boolean testParam = true;
	private CustomerRecordsExceptionHandler handler = new CustomerRecordsExceptionHandler();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link org.rabobank.process.records.exceptionHandling.CustomerRecordsExceptionHandler#handleCustomIOException(org.rabobank.process.records.exceptionHandling.CustomFileIOException)}.
	 */
	@Test
	public void testHandleCustomIOException() {

		ResponseEntity<Object> result = handler
				.handleCustomIOException(new CustomFileIOException("Error finding file Path", null, "TEST_EX_001"));
		assertEquals(result.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
		assertNotNull(result.getBody());
	}

	/**
	 * Test method for
	 * {@link org.rabobank.process.records.exceptionHandling.CustomerRecordsExceptionHandler}
	 */
	@Test
	public void testHandleCsvRequiredFieldEmptyException() {
		ResponseEntity<Object> result = handler.handleCsvRequiredFieldEmptyException(new CsvRequiredFieldEmptyException());
		assertEquals(result.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
		assertNotNull(result.getBody());
	}

	/**
	 * Test method for
	 * {@link org.rabobank.process.records.exceptionHandling.CustomerRecordsExceptionHandler}
	 */
	@Test
	public void testHandleCsvDataTypeMismatchException() {
		ResponseEntity<Object> result = handler.handleCsvDataTypeMismatchException(new CsvDataTypeMismatchException());
		assertEquals(result.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
		assertNotNull(result.getBody());
	}

}
