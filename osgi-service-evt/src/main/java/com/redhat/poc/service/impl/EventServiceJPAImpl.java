package com.redhat.poc.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.redhat.poc.service.EventService;
import com.redhat.poc.vo.Event;

public class EventServiceJPAImpl extends EventServiceAbstract implements
		EventService {

	private static final String SELECT_E_FROM_EVENT_E = "select e from Event e";
	private static final String DELETE_FROM_EVENT = "delete from Event";

	private EntityManager em;

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	public void setEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		em = entityManagerFactory.createEntityManager();
	}

	public Event create(Event event) {
		em.persist(event);
		em.flush();
		return event;
	}

	public void delete(String uuid) {
		Event e = em.find(Event.class, uuid);
		em.remove(e);
		em.flush();
	}

	public List<Event> list(int offset, int limit) {

		Query q = em.createQuery(SELECT_E_FROM_EVENT_E, Event.class);

		q.setFirstResult(offset);
		q.setMaxResults(limit);

		return q.getResultList();
	}

	public void purge() {
		em.createQuery(DELETE_FROM_EVENT).executeUpdate();
		em.flush();
	}

	public Event select(String uuid) {
		return em.find(Event.class, uuid);
	}

	public Event modify(Event e) {
		em.merge(e);
		return null;
	}

}
