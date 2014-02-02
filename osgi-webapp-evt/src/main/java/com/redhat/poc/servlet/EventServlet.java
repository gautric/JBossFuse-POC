package com.redhat.poc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.redhat.poc.service.EventService;

public class EventServlet extends GenericServlet {

	public static String JNDI_SERVICE_KEY = "com.redhat.poc.service.jndi.name"; 
	
	
	private EventService es = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		String serviceName = config.getInitParameter(JNDI_SERVICE_KEY);
		
		Context ctx;
		try {
			ctx = new InitialContext();
			es = (EventService)ctx.lookup(serviceName);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -761248587099717281L;

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		try {
			PrintWriter out = res.getWriter();
			out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\">");
			out.println("<title>Bonjour tout le monde&amp;nbsp;!</title>");
			out.println("<p>Hello world!</p>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}