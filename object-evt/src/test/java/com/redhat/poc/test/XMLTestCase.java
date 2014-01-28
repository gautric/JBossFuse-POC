package com.redhat.poc.test;

import java.io.File;
import java.io.StringBufferInputStream;
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

	public static String xml = "<ns2:event xmlns:ns2=\"urn:redhat.com:poc/class\" version=\"1.0\">"
			+ "<author>Marie</author>"
			+ "<creationTime>2014-01-18T20:15:25.889+01:00</creationTime>"
			+ "<id>23ff4ffd-6ebe-4a40-9736-13262d04d579</id>"
			+ "<state>NEW</state>"
			+ "<system>Externe</system>"
			+ "</ns2:event>";

	@Test
	public void test2() throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance("com.redhat.poc.vo");

		Unmarshaller marshaller = jc.createUnmarshaller();
		Event e = (Event) marshaller
				.unmarshal(new StringBufferInputStream(xml));
		Assert.assertEquals("Marie", e.getAuthor());
		Assert.assertEquals("1.0", e.getVersion());

	}

	@Test
	public void test3() throws JAXBException, SAXException {
		JAXBContext jc = JAXBContext.newInstance("com.redhat.poc.vo");
		SchemaFactory factory = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = factory.newSchema(new File(
				"src/main/resources/META-INF/event.xsd"));

		Unmarshaller marshaller = jc.createUnmarshaller();
		marshaller.setSchema(schema);

		Event e = (Event) marshaller
				.unmarshal(new StringBufferInputStream(xml));
		Assert.assertEquals("Marie", e.getAuthor());
		Assert.assertEquals("1.0", e.getVersion());

	}
}
