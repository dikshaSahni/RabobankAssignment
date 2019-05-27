package org.rabobank.process.records.utils;

import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.rabobank.process.records.exceptionHandling.CustomFileIOException;
import org.rabobank.process.records.model.Records.Record;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class XMLFileProcessorTest {

	@Spy
	private RecordsValidator recordsValidator;

	@InjectMocks
	XMLFileProcessor xmlProcessor = new XMLFileProcessor() {

		@Override
		protected JAXBContext getJAXBInstance() throws JAXBException {
			throw new JAXBException("Error");
		}
	};

	@InjectMocks
	XMLFileProcessor processor = new XMLFileProcessor() {

	};

	@Test
	public void testProcessXmlRecords_Valid() throws CustomFileIOException, URISyntaxException, JAXBException {
		Path xmlFilePath = Paths.get(getClass().getClassLoader().getResource("testRecords.xml").toURI());

		List<Record> result = new ArrayList<Record>();
		result = processor.processXmlRecords(xmlFilePath, result);
		assertTrue(result.size() == 2);
	}

	@Test(expected = JAXBException.class)
	public void testProcessXmlRecords_IOException() throws CustomFileIOException, URISyntaxException, JAXBException {

		Path xmlFilePath;
		xmlFilePath = Paths.get(getClass().getClassLoader().getResource("testRecords.xml").toURI());
		List<Record> result = new ArrayList<Record>();
		xmlProcessor.processXmlRecords(xmlFilePath, result);
	}
}
