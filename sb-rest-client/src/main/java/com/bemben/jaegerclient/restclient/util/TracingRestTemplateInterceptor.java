package com.bemben.jaegerclient.restclient.util;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import io.opentracing.Tracer;
import io.opentracing.propagation.Format;

public class TracingRestTemplateInterceptor implements ClientHttpRequestInterceptor {
    
    private Tracer tracer;
    
    public TracingRestTemplateInterceptor(Tracer tracer) {
    	this.tracer = tracer;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        
    	ClientHttpResponse httpResponse;
    	
    	tracer.inject(tracer.activeSpan().context(), Format.Builtin.HTTP_HEADERS, new HttpHeadersCarrier(httpRequest.getHeaders()));

    	httpResponse = execution.execute(httpRequest, body);
        
        return httpResponse;
        
    }
}
