package com.redhat.poc.service.impl;

import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.redhat.poc.vo.Event;

public class EventServiceUtil {

	public static String returnXML(Event event) {
		Writer w = new StringWriter();
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(Event.class);
			Marshaller unmarshaller = jc.createMarshaller();
			unmarshaller.marshal(event, w);
		} catch (JAXBException e) {
		}
		return w.toString();
	}
}
