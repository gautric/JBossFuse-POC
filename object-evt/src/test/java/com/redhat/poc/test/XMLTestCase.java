package com.redhat.poc.test;

import java.io.StringBufferInputStream;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Assert;
import org.junit.Test;

import com.redhat.poc.vo.Event;
import com.redhat.poc.vo.State;

public class XMLTestCase {

	@Test
	public void test1() throws JAXBException {
		Event e = new Event();
		e.setId(UUID.randomUUID().toString());
		e.setState(State.NEW);
		JAXBContext jc = JAXBContext.newInstance(Event.class);
		Marshaller marshaller = jc.createMarshaller();
		marshaller.marshal(e, System.out);

	}

	public static String xml = "<ns2:event xmlns:ns2=\"urn:redhat.com:poc/class\">"
			+ "<author>Marie</author>"
			+ "<date>2014-01-18T20:15:25.889+01:00</date>"
			+ "<state>NEW</state>" + "<system>Externe</system>"
			+ "</ns2:event>";

	@Test
	public void test2() throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance("com.redhat.poc.vo");
		
		Unmarshaller marshaller = jc.createUnmarshaller();
		Event e = (Event)marshaller.unmarshal(new StringBufferInputStream(xml));
		Assert.assertEquals("Marie",e.getAuthor());
		

	}
}
