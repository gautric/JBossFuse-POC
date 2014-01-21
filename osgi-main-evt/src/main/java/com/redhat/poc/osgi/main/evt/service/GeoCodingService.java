package com.redhat.poc.osgi.main.evt.service;

import com.redhat.poc.osgi.main.evt.exception.UnknownCodeException;

public interface GeoCodingService {

	public String convertCodeToName(String ISO3166_1alpha_3)
			throws UnknownCodeException;
}
