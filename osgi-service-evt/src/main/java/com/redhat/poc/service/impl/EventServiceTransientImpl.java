package com.redhat.poc.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.redhat.poc.service.EventService;
import com.redhat.poc.vo.Event;
import com.redhat.poc.vo.State;

public class EventServiceTransientImpl extends EventServiceAbstract implements
		EventService {

	private Map<String, Event> localdb = Collections
			.synchronizedMap(new HashMap<String, Event>());

	public void create(Event event) {
		UUID uuid = UUID.randomUUID();
		event.setId(uuid.toString());
		event.setState(State.NEW);
		localdb.put(uuid.toString(), event);
	}

	public void delete(String uuid) {
		localdb.remove(uuid);
	}

	public List<Event> list() {
		
		return (List<Event>) localdb.values();
	}

	public void purge() {
		localdb.clear();

	}

	@Override
	public Event select(String uuid) {
		return null;
	}

	
}
