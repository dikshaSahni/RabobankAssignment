package org.rabobank.process.records.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.rabobank.process.records.exceptionHandling.CustomFileIOException;
import org.rabobank.process.records.exceptionHandling.ErrorCodes;
import org.rabobank.process.records.model.ObjectFactory;
import org.rabobank.process.records.model.Records;
import org.rabobank.process.records.model.Records.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class XMLFileProcessor {

	@Autowired
	RecordsValidator recordsValidator;

	/**
	 * This method processes Input XML file and generate list of failed records
	 * 
	 * @param path      Path
	 * @param recordList List<FailedRecords>
	 * @return recordList List<FailedRecords>
	 * @throws JAXBException
	 * @throws CustomFileIOException
	 */
	public List<Record> processXmlRecords(Path xmlPath, List<Record> recordList)
			throws CustomFileIOException, JAXBException {

		try {
			Unmarshaller jaxbUnmarshaller = getJAXBInstance().createUnmarshaller();
			JAXBElement<Records> rootItem = jaxbUnmarshaller.unmarshal(new StreamSource(getInputStream(xmlPath)),
					Records.class);

			List<Record> records = rootItem.getValue().getRecord();
			for (Record xmlRecord : records) {
				if (!(recordsValidator.validateEachRecord(xmlRecord)))
					recordList.add(xmlRecord);
			}
			return recordList;
		} catch (IOException ioException) {
			throw new CustomFileIOException("Error Parsing Input XML File", ioException,
					ErrorCodes.ERROR_PARSING_INPUT_XML);
		}
	}

	protected InputStream getInputStream(Path xmlPath) throws IOException {
		return Files.newInputStream(xmlPath);
	}

	protected JAXBContext getJAXBInstance() throws JAXBException {
		return JAXBContext.newInstance(ObjectFactory.class);
	}
}
