#!/bin/sh

/etc/init.d/mysql start && \
mysql < /usr/sql/createphoenix.sql && \
rm -rd /usr/sql
tail -f /dev/null