package com.redhat.poc.osgi.main.evt.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.BindingType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.poc.osgi.main.evt.exception.UnknownCodeException;
import com.redhat.poc.osgi.main.evt.service.GeoCodingService;

@WebService(targetNamespace = "urn:redhat.com:poc/ws")
@SOAPBinding
@BindingType(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class GeoCodingServiceImpl implements GeoCodingService {

	private static Logger LOG = LoggerFactory
			.getLogger(GeoCodingServiceImpl.class);

	private static final Map<String, String> convertCodeToName;
	static {
		convertCodeToName = new HashMap<String, String>();
		convertCodeToName.put("BRA", "BRASIL");
		convertCodeToName.put("CAN", "CANADA");
		convertCodeToName.put("DNK", "DANEMARK");
		convertCodeToName.put("FRA", "FRANCE");
		convertCodeToName.put("JPN", "JAPAN");
		convertCodeToName.put("MAR", "MORROCO");
		convertCodeToName.put("RUS", "RUSSIA");
	}

	@WebMethod
	public String convertCodeToName(
			@WebParam(name = "codeISO3166alpha3", partName = "codeISO3166alpha3") String ISO3166_1alpha_3)
			throws UnknownCodeException {

		LOG.debug("GeoCodingService.convertCodeToName(" + ISO3166_1alpha_3
				+ ")");
		if (!convertCodeToName.containsKey(ISO3166_1alpha_3)) {
			throw new UnknownCodeException();
		}

		return convertCodeToName.get(ISO3166_1alpha_3);
	}
}
