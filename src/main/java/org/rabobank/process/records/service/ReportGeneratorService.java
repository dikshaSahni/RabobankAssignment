package org.rabobank.process.records.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.rabobank.process.records.exceptionHandling.CustomFileIOException;
import org.rabobank.process.records.exceptionHandling.ErrorCodes;
import org.rabobank.process.records.model.Records.Record;
import org.springframework.stereotype.Service;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReportGeneratorService {

	private static final String FILE_HEADER = "Reference, Description";

	/**
	 * @param records  List<FailedRecords>
	 * @param filePath String
	 * @throws CsvRequiredFieldEmptyException
	 * @throws CsvDataTypeMismatchException
	 * @throws CustomFileIOException
	 */
	public void writeFile(List<Record> records, String filePath)
			throws CustomFileIOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

		try (Writer writer = createFile(filePath)) {

			ColumnPositionMappingStrategy<Record> mappingStrategy = new ColumnPositionMappingStrategy<>();
			mappingStrategy.setType(Record.class);

			// Arrange column name as provided in below array.
			String[] columns = new String[] { FILE_HEADER };
			mappingStrategy.setColumnMapping(columns);
			
			// Creating StatefulBeanToCsv object
			StatefulBeanToCsv beanWriter = new StatefulBeanToCsvBuilder<>(writer)
					.withSeparator(CSVWriter.DEFAULT_SEPARATOR).build();

			if (!(records.isEmpty() || records == null)) {
				beanWriter.write(records);
			}
		} catch (IOException ioException) {
			throw new CustomFileIOException("Error Writing Output File", ioException,
					ErrorCodes.ERROR_WRITING_OUTPUT_FILE);
		}
	}

	/**
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	protected FileWriter createFile(String filePath) throws IOException {
		return new FileWriter(filePath + "/report_" + System.currentTimeMillis() + ".csv");
	}

}
