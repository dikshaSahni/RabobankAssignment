/*
 * package org.rabobank.process.records.utils;
 * 
 * import static org.junit.Assert.assertTrue;
 * 
 * import java.net.URISyntaxException; import java.nio.file.Path; import
 * java.nio.file.Paths; import java.util.ArrayList; import java.util.List;
 * 
 * import org.junit.Test; import org.mockito.InjectMocks; import
 * org.mockito.Spy; import
 * org.rabobank.process.records.exceptionHandling.CustomFileIOException; import
 * org.rabobank.process.records.model.Records.Record; import
 * org.springframework.boot.test.context.SpringBootTest;
 * 
 * @SpringBootTest public class CSVFileProcessorTest {
 * 
 * @Spy private RecordsValidator recordsValidator;
 * 
 * @InjectMocks CSVFileProcessor csvProcessor = new CSVFileProcessor();
 * 
 * @Test(expected = CustomFileIOException.class) public void
 * testProcessCsvRecords_IOException() throws CustomFileIOException,
 * URISyntaxException {
 * 
 * Path csvFilePath =
 * Paths.get(getClass().getClassLoader().getResource("testRecords.csv").toURI())
 * ;
 * 
 * List<Record> result = new ArrayList<Record>(); result =
 * csvProcessor.processCsvRecords(csvFilePath, result); }
 * 
 * @Test public void testProcessCsvRecords_Valid() throws CustomFileIOException,
 * URISyntaxException {
 * 
 * Path csvFilePath =
 * Paths.get(getClass().getClassLoader().getResource("testRecords.csv").toURI())
 * ;
 * 
 * List<Record> result = new ArrayList<Record>(); result =
 * csvProcessor.processCsvRecords(csvFilePath, result); assertTrue(result.size()
 * == 2); }
 * 
 * 
 * }
 */