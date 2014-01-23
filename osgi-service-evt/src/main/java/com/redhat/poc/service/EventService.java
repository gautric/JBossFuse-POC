package com.redhat.poc.service;

import java.util.List;

import com.redhat.poc.vo.Event;

public interface EventService {
	void create(Event event);
	
	Event select(String uuid);

	void delete(String uuid);

	List<Event> list();

	void purge();

	void generate(int n);

	Event autogenerate();
}
