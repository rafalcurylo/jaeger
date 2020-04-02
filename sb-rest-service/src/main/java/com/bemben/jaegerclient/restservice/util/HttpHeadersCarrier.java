package com.bemben.jaegerclient.restservice.util;

import java.util.Iterator;
import java.util.Map;

import io.opentracing.propagation.TextMap;

public class HttpHeadersCarrier implements TextMap {

    private Map<String, String> httpHeaders;

    public HttpHeadersCarrier(Map<String, String> httpHeaders)  {
        this.httpHeaders = httpHeaders;
    }

    @Override
    public void put(String key, String value) {
    	throw new UnsupportedOperationException("Should be used only with tracer#extract()");
    }

    @Override
    public Iterator<Map.Entry<String, String>> iterator() {
    	return this.httpHeaders.entrySet().iterator();
    }
}
