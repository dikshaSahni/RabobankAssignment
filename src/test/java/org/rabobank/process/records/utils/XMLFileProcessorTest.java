/*
 * package org.rabobank.process.records.utils;
 * 
 * import static org.junit.Assert.assertTrue; import static
 * org.junit.Assert.fail;
 * 
 * import java.net.URISyntaxException; import java.nio.file.Path; import
 * java.nio.file.Paths; import java.util.ArrayList; import java.util.List;
 * 
 * import javax.xml.parsers.ParserConfigurationException;
 * 
 * import org.junit.Test; import
 * org.rabobank.process.records.exceptionHandling.CustomFileIOException; import
 * org.rabobank.process.records.service.FailedRecords; import
 * org.xml.sax.SAXException;
 * 
 * public class XMLFileProcessorTest {
 * 
 * @Test public void test() { fail("Not yet implemented"); }
 * 
 * @Test public void testProcessXmlRecords_Valid() throws CustomFileIOException,
 * ParserConfigurationException, SAXException, URISyntaxException {
 * 
 * Path xmlFilePath =
 * Paths.get(getClass().getClassLoader().getResource("testRecords.xml").toURI())
 * ;
 * 
 * List<FailedRecords> result = new ArrayList<FailedRecords>(); result =
 * custRecService.processXmlRecords(xmlFilePath, result);
 * assertTrue(result.size() == 2); }
 * 
 * @Test(expected = CustomFileIOException.class) public void
 * testProcessXmlRecords_IOException() throws CustomFileIOException,
 * ParserConfigurationException, SAXException, URISyntaxException {
 * 
 * Path xmlFilePath; xmlFilePath =
 * Paths.get(getClass().getClassLoader().getResource("testRecords.xml").toURI())
 * ; List<FailedRecords> result = new ArrayList<FailedRecords>(); result =
 * custRecordS.processXmlRecords(xmlFilePath, result); } }
 */