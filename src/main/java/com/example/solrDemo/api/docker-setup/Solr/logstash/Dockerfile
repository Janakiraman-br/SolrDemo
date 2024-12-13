FROM docker.elastic.co/logstash/logstash:8.4.2

# install dependency
RUN /usr/share/logstash/bin/logstash-plugin install logstash-integration-jdbc
RUN /usr/share/logstash/bin/logstash-plugin install logstash-filter-aggregate
RUN /usr/share/logstash/bin/logstash-plugin install logstash-filter-mutate

# copy lib database jdbc jars
COPY ./postgresql-42.5.0.jar /usr/share/logstash/logstash-core/lib/jars/postgresql.jar