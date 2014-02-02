package com.redhat.poc.jaxws.exception;

import java.io.Serializable;

import javax.xml.ws.WebFault;

@WebFault(targetNamespace="urn:redhat.com:poc/exception")
public class UnknownCodeException extends Exception implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 355919820232757641L;

	public UnknownCodeException() {
		super();
	}
}
