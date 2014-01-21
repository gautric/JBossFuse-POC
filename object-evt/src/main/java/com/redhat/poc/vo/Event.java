package com.redhat.poc.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@NamedQueries(value = {
		@NamedQuery(name = "event.per.state", query = "SELECT e FROM Event e WHERE e.state = :state"),
		@NamedQuery(name = "new.event", query = "SELECT e FROM Event e WHERE e.state = com.redhat.poc.vo.State.NEW") })
// Openjpa compliant
// @NamedQuery(name = "new.event", query =
// "SELECT e FROM Event e WHERE e.state = 'NEW'") }) Hibernate compliant
@XmlRootElement(namespace = "urn:redhat.com:poc/class")
public class Event {
	@Id
	@GeneratedValue(generator = "uuid-string")
	@GenericGenerator(name = "uuid-string", strategy = "uuid2")
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

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getGeo() {
		return geo;
	}

	public void setGeo(String geo) {
		this.geo = geo;
	}

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
