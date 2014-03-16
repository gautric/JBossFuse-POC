package com.redhat.poc.mt.reception.evt;

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

import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.redhat.poc.vo.Event;

public class EventUpdate implements Processor {

	public void process(Exchange exchange) throws Exception {

		Event event = (Event)exchange.getIn().getBody(Event.class);

		event.setUpdateTime((Date)exchange.getProperty("CamelCreatedTimestamp"));
		
		exchange.getOut().setBody(event);
	}
}