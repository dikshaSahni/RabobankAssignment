package org.rabobank.process.records.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.rabobank.process.records.model.Records.Record;
import org.springframework.stereotype.Component;

@Component
public class RecordsValidator {

	private List<String> refList = new ArrayList<String>();

	/**
	 * @param record    Record
	 * @return true/false boolean
	 */
	public boolean validateEachRecord(Record record) {
		return isValidEndBalance(record.getStartBalance(), record.getMutation(), record.getEndBalance())
				&& isValidReference(record.getReference());
	}

	/**
	 * @param start    BigDecimal
	 * @param mutation BigDecimal
	 * @param end      BigDecimal
	 * @return true/false boolean
	 */
	public boolean isValidEndBalance(BigDecimal start, BigDecimal mutation, BigDecimal end) {
		if (end.setScale(2, RoundingMode.DOWN).compareTo(start.add(mutation).setScale(2, RoundingMode.DOWN)) == 0) {
			return true;
		}
		return false;
	}

	/**
	 * @param reference String
	 * @return true/false boolean
	 */
	public boolean isValidReference(String reference) {
		if (isNumeric(reference) && !((refList).contains(reference))) {
			refList.add(reference);
			return true;
		}
		return false;
	}

	/**
	 * @param strNum String
	 * @return true/false boolean
	 */
	private static boolean isNumeric(String strNum) {
		return strNum.matches("-?\\d+(\\.\\d+)?");
	}

}
