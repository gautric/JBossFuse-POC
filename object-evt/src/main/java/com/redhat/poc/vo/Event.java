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
import javax.xml.bind.annotation.XmlType;

@Entity
@NamedQueries(value = {
		@NamedQuery(name = "event.per.state", query = "SELECT e FROM Event e WHERE e.state = :state"),
		@NamedQuery(name = "new.event", query = "SELECT e FROM Event e WHERE e.state = com.redhat.poc.vo.State.NEW"),
		@NamedQuery(name = "select.event", query = "SELECT e FROM Event e WHERE e.state = :state AND e.creationTime = :creationTime") })
@XmlRootElement(namespace = "urn:redhat.com:poc/class")
@XmlType(namespace = "urn:redhat.com:poc/class", name = "eventType")
public class Event {

	@Id
	// @GeneratedValue(generator = "uuid-string", strategy=)
	// @GenericGenerator(name = "uuid-string", strategy = "uuid2")
	private String id;

	@Transient
	private String _url;

	@Transient
	private String _version;

	@Column(name = "author")
	private String author;

	@Column(name = "creationTime")
	private Date creationTime;

	@Column(name = "geo")
	private String geo;

	@Column(name = "message")
	private String message;

	@Column(name = "state")
	@Enumerated(EnumType.STRING)
	private State state;

	@Column(name = "system")
	private String system;

	@Column(name = "type")
	private String type;

	@Column(name = "updateTime")
	private Date updateTime;

	public Event() {
		super();
	}

	@XmlElement(required = true)
	public String getAuthor() {
		return author;
	}

	@XmlElement(required = true)
	public Date getCreationTime() {
		return creationTime;
	}

	@XmlElement(required = false)
	public String getGeo() {
		return geo;
	}

	@XmlElement(required = true)
	public String getId() {
		return id;
	}

	@XmlElement(required = false)
	public String getMessage() {
		return message;
	}

	@XmlElement(required = true)
	public State getState() {
		return state;
	}

	@XmlElement(required = false)
	public String getSystem() {
		return system;
	}

	@XmlElement(required = false)
	public String getType() {
		return type;
	}

	@XmlElement(required = false)
	public Date getUpdateTime() {
		return updateTime;
	}

	@XmlAttribute(name = "url")
	public String getUrl() {
		return _url;
	}

	@XmlAttribute(name = "version")
	public String getVersion() {
		return _version;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public void setGeo(String geo) {
		this.geo = geo;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setState(State state) {
		this.state = state;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setUrl(String _url) {
		this._url = _url;
	}

	public void setVersion(String _version) {
		this._version = _version;
	}

	public String toString() {
		return "[ID:" + id + ",author:" + author + ",State:" + state + ",Geo:"
				+ geo + "]";
	}

}
