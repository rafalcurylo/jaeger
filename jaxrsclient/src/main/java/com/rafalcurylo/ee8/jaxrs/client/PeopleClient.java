package com.rafalcurylo.ee8.jaxrs.client;

import io.opentracing.Span;
import io.opentracing.Tracer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import java.util.List;

@Path("people")
public class PeopleClient {

    @GET
    @Produces("application/json")
    public List<Person> getAll() {

        Client client = ClientBuilder.newClient();
        System.out.println("calling server");
        List<Person> list = client.target("http://jaxrs-server:8080/jaxrs-server-4jaeger")
                .path("resources/people")
                .queryParam("sd", "sd")
                .request()
                .get(new GenericType<List<Person>>() {});

        System.out.println("got results:" + list.size());

        Tracer tracer = TracingUtil.initTracer("jaxrs-client");
        Tracer.SpanBuilder builder = tracer.buildSpan("getAll");
        Span span = builder.start();
        span.setTag("some tag", "tag value");
        span.log(System.currentTimeMillis(), "getAll span log");
        span.finish();

        return list;
    }

}
