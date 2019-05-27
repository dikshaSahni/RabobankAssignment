package org.rabobank.process.records.exceptionHandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

	// General error message about nature of error
	private String message;

	// Specific errors in API request processing
	private String details;

}
