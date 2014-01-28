package com.redhat.poc.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@Entity
@NamedQueries(value = {
		@NamedQuery(name = "event.per.state", query = "SELECT e FROM Event e WHERE e.state = :state"),
		@NamedQuery(name = "new.event", query = "SELECT e FROM Event e WHERE e.state = com.redhat.poc.vo.State.NEW") })
// Openjpa compliant
// @NamedQuery(name = "new.event", query =
// "SELECT e FROM Event e WHERE e.state = 'NEW'") }) Hibernate compliant
@XmlRootElement(namespace = "urn:redhat.com:poc/class")
@XmlType(namespace = "urn:redhat.com:poc/class", name="eventType")
public class Event {
	@Id
	// @GeneratedValue(generator = "uuid-string", strategy=)
	// @GenericGenerator(name = "uuid-string", strategy = "uuid2")
	private String id;
	
	@Column(name = "creationTime")
	private Date creationTime;
	
	@Column(name = "updateTime")
	private Date updateTime;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "system")
	private String system;
	
	@Column(name = "geo")
	private String geo;
	
	@Column(name = "state")
	@Enumerated(EnumType.STRING)
	private State state;

	@Transient
	private String _version;

	@XmlAttribute(name = "version")
	public String getVersion() {
		return _version;
	}

	public void setVersion(String _version) {
		this._version = _version;
	}

	public Event() {
		super();
	}

	@XmlElement(required = true)
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@XmlElement(required = true)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlElement(required = true)
	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	@XmlElement(required = false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlElement(required = true)
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@XmlElement(required = false)
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@XmlElement(required = false)
	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	@XmlElement(required = false)
	public String getGeo() {
		return geo;
	}

	public void setGeo(String geo) {
		this.geo = geo;
	}

	@XmlElement(required = false)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String toString() {
		return "[ID:" + id + ",author:" + author + ",State:" + state + ",Geo:"
				+ geo + "]";

	}

}
