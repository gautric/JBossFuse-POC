package com.redhat.poc.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.redhat.poc.service.EventService;
import com.redhat.poc.vo.Event;

public class EventServiceJPAImpl extends EventServiceAbstract implements
		EventService {

	private EntityManager em;

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	public void setEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		em = entityManagerFactory.createEntityManager();
	}

	public void create(Event event) {
		em.persist(event);
		em.flush();
	}

	public void delete(String uuid) {
		Event e = em.find(Event.class, uuid);
		em.remove(e);
		em.flush();
	}

	public List<Event> list() {
		return em.createQuery("select e from Event e", Event.class)
				.getResultList();
	}

	public void purge() {
		em.createQuery("delete from Event").executeUpdate();
		em.flush();
	}

	@Override
	public Event select(String uuid) {
		return em.find(Event.class, uuid);
	}

}
