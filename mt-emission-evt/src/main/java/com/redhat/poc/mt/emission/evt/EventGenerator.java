package com.redhat.poc.mt.emission.evt;

/**
 * Copyright (c) Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.Calendar;
import java.util.UUID;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang.StringUtils;

import com.redhat.poc.vo.Event;
import com.redhat.poc.vo.State;

public class EventGenerator implements Processor {

	private static String SOURCE_GENERATION = "source.generation";
	private static String EVENT_AUTHOR = "event.author";
	private static String EVENT_MESSAGE = "event.message";

	public void process(Exchange exchange) throws Exception {
		Event event = new Event();
		String author = null;
		String message = null;

		exchange.getOut().copyFrom(exchange.getIn());
		String body = exchange.getIn().getBody(String.class);

		event.setId(UUID.randomUUID().toString());

		author = (String) exchange.getIn().getHeader(EVENT_AUTHOR);
		message = (String) exchange.getIn().getHeader(EVENT_MESSAGE);

		if (author == null || "".compareTo(author) == 0) {
			event.setAuthor(Referentiel.randomName());
		} else {
			event.setAuthor(author);
		}

		if (message != null) {
			event.setMessage(message);
		}
		event.setSystem(Referentiel.randomSystem());
		event.setCreationTime(Calendar.getInstance().getTime());
		event.setGeo(Referentiel.randomISO3166_1alpha_3());
		event.setVersion(Referentiel.randomVersion());
		event.setState(State.NEW);

		event.setType(((String) exchange.getIn().getHeader(SOURCE_GENERATION)));

		if (StringUtils.isNotBlank(body)) {
			event.setMessage(body);
		}

		exchange.getOut().setBody(event);
	}
}