package com.redhat.poc.vo;

import javax.xml.bind.annotation.XmlType;

@XmlType(namespace = "urn:redhat.com:poc/class")
public enum State {
	NEW, TRANSIENT, SEND, CLOSE
}
