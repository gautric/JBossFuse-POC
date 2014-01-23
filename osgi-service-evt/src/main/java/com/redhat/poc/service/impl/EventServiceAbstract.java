package com.redhat.poc.service.impl;

import java.util.UUID;

import com.redhat.poc.service.EventService;
import com.redhat.poc.vo.Event;
import com.redhat.poc.vo.State;

abstract public class EventServiceAbstract implements EventService {

	public Event autogenerate() {
		Event ret = new Event();
		ret.setId(UUID.randomUUID().toString());
		ret.setAuthor("n/a");
		ret.setSystem("autogenerate");
		ret.setMessage("Auto message");
		ret.setState(State.TRANSIENT);
		return ret;

	}

	public void generate(int n) {
		Event e = null;
		for (int i = 0; i < n; i++) {
			e = autogenerate();
			e.setAuthor("OSGI");
			e.setMessage("Event " + i);
			create(e);
		}
	}
}
