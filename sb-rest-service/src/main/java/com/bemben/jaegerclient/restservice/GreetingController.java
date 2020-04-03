package com.bemben.jaegerclient.restservice;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bemben.jaegerclient.restservice.util.Greeting;
import com.bemben.jaegerclient.restservice.util.HttpHeadersCarrier;
import com.bemben.jaegerclient.restservice.util.TracingUtil;

import io.opentracing.Span;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import io.opentracing.propagation.Format;

@RestController
public class GreetingController {

	private static final String template = "I've processed order: %s";
	private final AtomicLong counter = new AtomicLong();
	private Tracer tracer = TracingUtil.initTracer("Service");
	
	@GetMapping("/greeting")
	public Greeting greeting(@RequestHeader Map<String, String> headers, @RequestParam(value = "name", defaultValue = "XXXX") String orderId) {
		
		Tracer.SpanBuilder spanBuilder = getSpanBuilder(headers);
		
		Span span = spanBuilder.start();
        
        span.setTag("REST Communication", "Yes");
        
        span.log(System.currentTimeMillis(), "I'm processing received order");
                
        span.log(System.currentTimeMillis(), "I'm sending order back");
        
        span.finish();
		
		return new Greeting(counter.incrementAndGet(), String.format(template, orderId));
	}

	private Tracer.SpanBuilder getSpanBuilder(Map<String, String> headers) {
		Tracer.SpanBuilder spanBuilder;
	    try {
	    	
	    	SpanContext parentSpan = tracer.extract(Format.Builtin.HTTP_HEADERS, new HttpHeadersCarrier(headers));
	        
	        if (parentSpan == null) {
	            spanBuilder = tracer.buildSpan("procesing order");
	        } else {
	            spanBuilder = tracer.buildSpan("procesing order").asChildOf(parentSpan);
	        }
	    } catch (IllegalArgumentException e) {
	        spanBuilder = tracer.buildSpan("procesing order");
	    }
		return spanBuilder;
	}
	
}
