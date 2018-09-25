FROM mysql:5

RUN	mkdir /usr/sql

COPY setup.sql /usr/sql/setup.sql

COPY  createphoenix.sql /usr/sql/createphoenix.sql

COPY  my.cnf /etc/my.cnf

WORKDIR /data

COPY run.sh /data/run.sh

RUN chmod +x run.sh

RUN chmod +x /data/run.sh

CMD ["/data/run.sh"]