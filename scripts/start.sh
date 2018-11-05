#!/bin/sh

asadmin start-domain
asadmin deploy /data/PRMS.war

tail -f /opt/glassfish4/glassfish/domains/domain1/logs/server.log