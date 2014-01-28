package com.redhat.poc.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.UUID;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.redhat.poc.vo.Event;
import com.redhat.poc.vo.State;

public class XMLTestCase {

	@Test
	public void test1() throws JAXBException {
		Event e = new Event();
		e.setId(UUID.randomUUID().toString());
		e.setState(State.NEW);
		e.setVersion("2.0");
		JAXBContext jc = JAXBContext.newInstance(Event.class);
		Marshaller marshaller = jc.createMarshaller();
		marshaller.marshal(e, System.out);

	}

	@Test
	public void test2() throws JAXBException, FileNotFoundException {
		JAXBContext jc = JAXBContext.newInstance("com.redhat.poc.vo");

		Unmarshaller marshaller = jc.createUnmarshaller();
		Event e = (Event) marshaller.unmarshal(new FileInputStream(
				"src/test/resources/event-1.xml"));
		Assert.assertEquals("Marie", e.getAuthor());
		Assert.assertEquals("1.0", e.getVersion());

	}

	@Test
	public void test3() throws JAXBException, SAXException,
			FileNotFoundException {
		JAXBContext jc = JAXBContext.newInstance("com.redhat.poc.vo");
		SchemaFactory factory = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = factory.newSchema(new File(
				"src/main/resources/META-INF/event.xsd"));

		Unmarshaller marshaller = jc.createUnmarshaller();
		marshaller.setSchema(schema);

		Event e = (Event) marshaller.unmarshal(new FileInputStream(
				"src/test/resources/event-1.xml"));
		Assert.assertEquals("Marie", e.getAuthor());
		Assert.assertEquals("1.0", e.getVersion());

	}
}
