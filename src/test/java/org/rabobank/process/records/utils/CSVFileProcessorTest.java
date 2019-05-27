package org.rabobank.process.records.utils;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.rabobank.process.records.exceptionHandling.CustomFileIOException;
import org.rabobank.process.records.model.Records.Record;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CSVFileProcessorTest {

	@Spy
	private RecordsValidator recordsValidator;

	@InjectMocks
	CSVFileProcessor csvProcessor = new CSVFileProcessor() {
		
		@Override
		protected BufferedReader getBufferedReader(Path csvPath) throws IOException {
			throw new IOException();
		}
	};

	@InjectMocks
	CSVFileProcessor processor = new CSVFileProcessor();
	
	@Test(expected = CustomFileIOException.class)
	public void testProcessCsvRecords_IOException() throws CustomFileIOException, URISyntaxException {

		Path csvFilePath = Paths.get(getClass().getClassLoader().getResource("testRecords.csv").toURI());
		List<Record> records = new ArrayList<>();
		csvProcessor.processCsvRecords(csvFilePath, records);
	}

	@Test
	public void testProcessCsvRecords_Valid() throws CustomFileIOException, URISyntaxException {

		Path csvFilePath = Paths.get(getClass().getClassLoader().getResource("testRecords.csv").toURI());

		List<Record> result = new ArrayList<Record>();
		result = processor.processCsvRecords(csvFilePath, result);
		assertTrue(result.size() == 2);
	}

}
