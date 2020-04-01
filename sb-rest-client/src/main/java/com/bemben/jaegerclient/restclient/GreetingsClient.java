package com.bemben.jaegerclient.restclient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bemben.jaegerclient.restclient.util.Greeting;
import com.bemben.jaegerclient.restclient.util.TracingUtil;

import io.opentracing.Span;
import io.opentracing.Tracer;

@RestController
public class GreetingsClient {

	@GetMapping("/callgreeting")
    public Greeting getAll() {


    	final String uri = "http://sbrest-servise:9083/greeting?name=ApplicationClient";
        
        RestTemplate restTemplate = new RestTemplate();
        Greeting result = restTemplate.getForObject(uri, Greeting.class);
    	
    	Tracer tracer = TracingUtil.initTracer2("Greetings Applcation Client");
        Tracer.SpanBuilder builder = tracer.buildSpan("Get greetings");
        Span span = builder.start();
        span.setTag("greetings", "application");
        span.log(System.currentTimeMillis(), "call for greetings");
        span.finish();

        return result;
    }

}
