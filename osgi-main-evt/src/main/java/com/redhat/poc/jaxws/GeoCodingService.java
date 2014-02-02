package com.redhat.poc.jaxws;

import com.redhat.poc.jaxws.exception.UnknownCodeException;

public interface GeoCodingService {

	public String convertCodeToName(String ISO3166_1alpha_3)
			throws UnknownCodeException;
}
