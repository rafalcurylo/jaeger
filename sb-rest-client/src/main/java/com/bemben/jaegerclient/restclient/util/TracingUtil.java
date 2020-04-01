package com.bemben.jaegerclient.restclient.util;


import io.jaegertracing.Configuration;
import io.jaegertracing.Configuration.ReporterConfiguration;
import io.jaegertracing.Configuration.SamplerConfiguration;
import io.jaegertracing.Configuration.SenderConfiguration;
import io.jaegertracing.internal.samplers.ConstSampler;
import io.opentracing.Tracer;


public class TracingUtil {

    /*public static Tracer initTracer(String service) {
        
        SenderConfiguration senderConfiguration = new SenderConfiguration().withEndpoint("http://localhost:16686/api/traces");
        
        ReporterConfiguration reporterConfig = ReporterConfiguration.fromEnv().withLogSpans(true).withSender(senderConfiguration);
        
        io.opentracing.Tracer tracer = new Configuration(service).withReporter(reporterConfig)
                // We need to get a builder so that we can directly inject the reporter instance.
                .getTracerBuilder()
                // This configures the tracer to send all spans, but you will probably want to use something less verbose.
                .withSampler(new ConstSampler(true))
                .build();
        
        return tracer;
    }
    */
    
	public static Tracer initTracer2(String service) {
     
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
                .withMaxQueueSize(1)
                .withSender(senderConfiguration)
                .withLogSpans(true);
        
        Configuration config = new Configuration(service).withSampler(samplerConfig).withReporter(reporterConfig);
        return config.getTracer();
    }
    
}
