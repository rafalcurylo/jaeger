package com.rafalcurylo.ee8.jaxrs.server;

import io.jaegertracing.Configuration;
import io.jaegertracing.Configuration.ReporterConfiguration;
import io.jaegertracing.Configuration.SamplerConfiguration;
import io.jaegertracing.Configuration.SenderConfiguration;
import io.jaegertracing.internal.samplers.ConstSampler;
import io.opentracing.Tracer;

public class TracingUtil {

	public static Tracer initTracer(String service) {
     
        SamplerConfiguration samplerConfig = new SamplerConfiguration()
        .withType(ConstSampler.TYPE)
        .withParam(1);
        
        SenderConfiguration senderConfiguration = new SenderConfiguration()
                    .withAgentHost("jaeger-agent")
                    .withAgentPort(5778)
                    .withEndpoint("http://jaeger-collector:14268/api/traces");
                    //.withAuthToken(authToken)
                    //.withAuthUsername(authUsername)
                    //.withAuthPassword(authPassword);
        
        ReporterConfiguration reporterConfig = new ReporterConfiguration()
        		.withFlushInterval(1000)
                .withMaxQueueSize(10)
                .withSender(senderConfiguration)
                .withLogSpans(true);
        
        Configuration config = new Configuration(service).withSampler(samplerConfig).withReporter(reporterConfig);
        return config.getTracer();
    }
    
}
