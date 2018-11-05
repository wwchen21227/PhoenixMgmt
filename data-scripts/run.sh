#!/bin/sh

/etc/init.d/mysql start && \
mysql < /usr/sql/createphoenix.sql && \
mysql < /usr/sql/setup.sql && \
rm -rd /usr/sql
tail -f /dev/null