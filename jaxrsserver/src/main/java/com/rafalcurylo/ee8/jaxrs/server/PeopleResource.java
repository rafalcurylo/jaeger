package com.rafalcurylo.ee8.jaxrs.server;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.Tracer.SpanBuilder;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("people")
public class PeopleResource {

    @EJB
    PeopleService service;

    @GET
    @Produces("application/json")
    public List<Person> getAll() {
        System.out.println("getAll");

        Tracer tracer = TracingUtil.initTracer("jaxrs-server");
        SpanBuilder builder = tracer.buildSpan("getAll");
        Span span = builder.start();
        span.setTag("some tag", "tag value");
        span.log(System.currentTimeMillis(), "getAll span log");
        span.finish();

        return service.getAll();

    }

}
