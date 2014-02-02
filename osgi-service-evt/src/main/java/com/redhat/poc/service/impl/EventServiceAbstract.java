package com.redhat.poc.service.impl;

import java.util.Calendar;
import java.util.UUID;

import com.redhat.poc.service.EventService;
import com.redhat.poc.util.Referentiel;
import com.redhat.poc.vo.Event;
import com.redhat.poc.vo.State;

abstract public class EventServiceAbstract implements EventService {

	public Event autogenerate() {
		Event ret = new Event();
		ret.setId(UUID.randomUUID().toString());
		ret.setAuthor(Referentiel.randomName());
		ret.setSystem("AutoGenerate");
		ret.setMessage("Auto message");
		ret.setState(State.TRANSIENT);
		ret.setGeo(Referentiel.randomISO3166_1alpha_3());
		ret.setCreationTime(Calendar.getInstance().getTime());
		ret.setVersion(Referentiel.randomVersion());
		return ret;

	}

	public void generate(int n) {
		Event e = null;
		for (int i = 0; i < n; i++) {
			e = autogenerate();
			e.setMessage("Event " + i);
			create(e);
		}
	}
}
