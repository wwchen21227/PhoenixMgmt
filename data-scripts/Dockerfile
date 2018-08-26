FROM mysql:5

RUN	mkdir /usr/sql

ADD ["createphoenix.sql", "/usr/sql/createphoenix.sql"]

ADD ["my.cnf", "/etc/my.cnf"]

WORKDIR /data

COPY run.sh /data/run.sh

RUN chmod +x /data/run.sh

CMD ["/data/run.sh"]