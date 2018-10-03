FROM frolvlad/alpine-oraclejdk8:latest

VOLUME [ "/data", "/app" ]

RUN apk update && apk add wget unzip tar apache-ant
RUN wget http://download.java.net/glassfish/4.1/release/glassfish-4.1.zip
RUN unzip glassfish-4.1.zip -d /opt 
RUN rm glassfish-4.1.zip

ENV PATH /opt/glassfish4/bin:/opt/app/bin:$PATH

WORKDIR /opt
COPY scripts/start.sh /opt/
EXPOSE 8080
EXPOSE 4848

RUN chmod +x /opt/start.sh
CMD ["/opt/start.sh"]
