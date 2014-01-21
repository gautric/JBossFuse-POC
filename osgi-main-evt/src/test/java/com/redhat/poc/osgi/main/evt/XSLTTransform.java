package com.redhat.poc.osgi.main.evt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.StringBufferInputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XSLTTransform {
	public static String xml = "<ns2:event xmlns:ns2=\"urn:redhat.com:poc/class\">"

			+ "<geo>MAR</geo>" + "</ns2:event>";

	public static void main(String[] args) throws TransformerException,
			FileNotFoundException {
		TransformerFactory factory = TransformerFactory.newInstance();

//		factory.setAttribute("debug", true);

		StreamSource xslStream = new StreamSource(
				new FileInputStream(
						"./src/main/resources/META-INF/geoCodingTransformation-function.xslt"));
		Transformer transformer = factory.newTransformer(xslStream);
		StreamSource in = new StreamSource(new StringBufferInputStream(xml));
		StreamResult out = new StreamResult(System.out);
		transformer.setParameter("geoDecoding", "Mon Inport");

		transformer.transform(in, out);
	}
}
