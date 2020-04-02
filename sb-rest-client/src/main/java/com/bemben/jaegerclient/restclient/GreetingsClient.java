package com.bemben.jaegerclient.restclient;

import java.util.Collections;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bemben.jaegerclient.restclient.util.Greeting;
import com.bemben.jaegerclient.restclient.util.TracingRestTemplateInterceptor;
import com.bemben.jaegerclient.restclient.util.TracingUtil;

import io.opentracing.Span;
import io.opentracing.Tracer;

@RestController
public class GreetingsClient {
	
	private Tracer tracer = TracingUtil.initTracer("Client");
	
	private RestTemplate restTemplate = new RestTemplate();
	
	{
		restTemplate.setInterceptors(Collections.singletonList(new TracingRestTemplateInterceptor(tracer)));
	}

	@GetMapping("/callgreeting")
    public Greeting getAll(@RequestParam(value = "orderId", defaultValue = "777") String orderId) {

    	Tracer.SpanBuilder builder = tracer.buildSpan("creating order");
        
        Span span = builder.start();
        
        tracer.activateSpan(span);
        
        span.setTag("REST Communication", "Yes");
        
        span.log(System.currentTimeMillis(), "Creating an order");
        
        span.log(System.currentTimeMillis(), "Sending on order to service");
        
        final String uri = "http://localhost:9083/greeting?name="+orderId;
        
        Greeting result = restTemplate.getForObject(uri, Greeting.class);
        
        span.log(System.currentTimeMillis(), "Received order processed by service");
        
        span.finish();

        return result;
    }

	
	/*@GetMapping("/callgreetingOld")
    public Greeting getAllOld(@RequestParam(value = "orderId", defaultValue = "World") String orderId) {

    	Tracer tracer = TracingUtil.initTracer("Client");
    	
        Tracer.SpanBuilder builder = tracer.buildSpan("creating order");
        
        Span span = builder.start();
        
        span.setTag("REST Communication", "Yes");
        
        span.log(System.currentTimeMillis(), "Creating an order");
        
        span.log(System.currentTimeMillis(), "Sending on order to service");
        
        final String uri = "http://localhost:9083/greeting?name="+orderId;
        
        RestTemplate restTemplate = new RestTemplate();
        Greeting result = restTemplate.getForObject(uri, Greeting.class);
        
        span.log(System.currentTimeMillis(), "Received order processed by service");
        
        span.finish();

        return result;
    }*/
	
}
