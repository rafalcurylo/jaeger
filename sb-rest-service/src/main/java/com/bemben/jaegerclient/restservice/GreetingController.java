package com.bemben.jaegerclient.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bemben.jaegerclient.restservice.util.Greeting;
import com.bemben.jaegerclient.restservice.util.TracingUtil;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.Tracer.SpanBuilder;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		
		
        //Tracer tracer = TracingUtil.initTracer("BOL-order");
        
        Tracer tracer2 = TracingUtil.initTracer2("BOL-order2");
        
        //SpanBuilder builder = tracer.buildSpan("create cart");
        SpanBuilder builder2 = tracer2.buildSpan("create cart2");
        
        
        //Span span = builder.start();
        Span span2 = builder2.start();
        
        //span.setTag("Bemben", "TEST");
        span2.setTag("Bemben2 were", "TEST2 test");
        
        //span.log(System.currentTimeMillis(), "LOGTEST");
        span2.log(System.currentTimeMillis(), "LOGTEST2 test");
                
        //span.finish();
        span2.finish();
        
		
		
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}
