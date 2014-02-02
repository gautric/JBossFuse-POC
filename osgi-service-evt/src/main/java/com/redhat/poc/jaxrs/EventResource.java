package com.redhat.poc.jaxrs;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.redhat.poc.service.EventService;
import com.redhat.poc.vo.Event;

@Path("/")
public class EventResource {

	@Context
	UriInfo uriInfo;

	@Context
	Request request;

	private EventService service = null;

	@GET
	@Path("/")
	@Produces("application/json")
	public Response list() {

		List<Event> ret = service.list();

		if (ret == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		for (Event iter : ret) {
			iter.setUrl(uriInfo.getBaseUriBuilder()
					.path(EventResource.class, "select").build(iter.getId())
					.toASCIIString());
		}

		GenericEntity<List<Event>> entity = new GenericEntity<List<Event>>(ret) {
		};

		return Response.ok().entity(entity).build();
	}

	@DELETE
	@Path("")
	public Response delete() {

		service.purge();

		return Response.status(Status.NO_CONTENT).build();
	}

	@POST
	@Path("/generation")
	@Produces("application/json")
	public void generation(@DefaultValue(value = "1") @FormParam("nb") int nb) {
		service.generate(nb);
		Response.status(Status.ACCEPTED);
	}

	@POST
	@Consumes("application/json")
	@Path("/event")
	@Produces("application/json")
	public Response create(Event e) {

		service.create(e);

		return Response
				.created(
						uriInfo.getBaseUriBuilder()
								.path(EventResource.class, "select")
								.build(e.getId())).entity(e).build();
	}

	@GET
	@Path("/event/{uuid}")
	@Produces("application/json")
	public Response select(@PathParam("uuid") String uuid) {

		Event ret = service.select(uuid);

		if (ret == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		ret.setUrl(uriInfo.getBaseUriBuilder()
				.path(EventResource.class, "select").build(ret.getId())
				.toASCIIString());
		return Response.ok().entity(ret).build();
	}

	@POST
	@Path("/event/{uuid}")
	@Produces("application/json")
	public Response select(@PathParam("uuid") String uuid, Event e) {

		if (e.getId() != null || e.getId().length() == 0) {
			return Response.status(Status.PRECONDITION_FAILED).build();
		}

		if (e.getId().compareTo(uuid) != 0) {
			return Response.status(Status.PRECONDITION_FAILED).build();
		}

		Event ret = service.modify(e);

		if (ret == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		ret.setUrl(uriInfo.getBaseUriBuilder()
				.path(EventResource.class, "select").build(ret.getId())
				.toASCIIString());
		return Response.ok().entity(ret).build();
	}

	@DELETE
	@Path("/event/{uuid}")
	@Produces("application/json")
	public Response delete(@PathParam("uuid") String uuid) {

		service.delete(uuid);

		return Response.status(Status.GONE).build();
	}

	public EventService getService() {
		return service;
	}

	public void setService(EventService service) {
		this.service = service;
	}

}