package org.rabobank.process.records.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.rabobank.process.records.model.Records.Record;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RecordsValidatorTest {

	private final RecordsValidator validator = new RecordsValidator();
	Record testRecord;

	@Test
	public void testValidateEachRecord_Valid() {

		testRecord = new Record();
		testRecord.setEndBalance(new BigDecimal("106.8"));
		testRecord.setMutation(new BigDecimal("15.57"));
		testRecord.setStartBalance(new BigDecimal("91.23"));
		testRecord.setReference("112806");
		assertTrue(validator.validateEachRecord(testRecord));
	}

	@Test
	public void testValidateEachRecord_InValidReference() {

		testRecord = new Record();
		testRecord.setEndBalance(new BigDecimal("106.8"));
		testRecord.setMutation(new BigDecimal("15.57"));
		testRecord.setStartBalance(new BigDecimal("91.23"));
		testRecord.setReference("abcde");
		assertFalse(validator.validateEachRecord(testRecord));
	}
	
	@Test
	public void testValidateEachRecord_InValidBalance() {

		testRecord = new Record();
		testRecord.setEndBalance(new BigDecimal("00"));
		testRecord.setMutation(new BigDecimal("15.57"));
		testRecord.setStartBalance(new BigDecimal("91.23"));
		testRecord.setReference("abcde");
		assertFalse(validator.validateEachRecord(testRecord));
	}
}
