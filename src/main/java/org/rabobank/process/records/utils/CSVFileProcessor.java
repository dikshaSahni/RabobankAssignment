package org.rabobank.process.records.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.rabobank.process.records.exceptionHandling.CustomFileIOException;
import org.rabobank.process.records.exceptionHandling.ErrorCodes;
import org.rabobank.process.records.model.Records.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Component
public class CSVFileProcessor {

	@Autowired
	RecordsValidator recordsValidator;

	/**
	 * This method processes all Input CSV files and generate list of failed records
	 * 
	 * @param csvPath Path
	 * @param recordList    List<FailedRecords>
	 * @return reordList List<FailedRecords>
	 * @throws CustomFileIOException CustomFileIOException
	 */
	public List<Record> processCsvRecords(Path csvPath, List<Record> recordList) throws CustomFileIOException {

		try {
			CsvToBean<Record> csvToBeanRecord = new CsvToBeanBuilder<Record>(Files.newBufferedReader(csvPath))
					.withType(Record.class).build();
			for (Record csvRecord : csvToBeanRecord.parse()) {
				if (!(recordsValidator.validateEachRecord(csvRecord))) {
					recordList.add(csvRecord);
				}
			}
			return recordList;
		} catch (IOException ioException) {
			throw new CustomFileIOException("Error Parsing Input Excel File", ioException,
					ErrorCodes.ERROR_PARSING_INPUT_EXCEL);
		}
	}
}
