Jaeger hello world example, with example applications, sending traced to jaeger. All run with docker-compose.
Project constists of:

-jaeger with cassandra database
-Java EE8 client and server REST application, run on jboss wildfly, talking to each other and sending traces to jaeger
-SpringBoot, client and server REST applications, talking to each other and sending traces to jaeger and sending jaeger correlation ID as headers between client and server
OR springboot apps).
-.net application
