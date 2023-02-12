FROM hseeberger/scala-sbt:8u222_1.3.5_2.13.1
RUN apt-get update && \
    apt-get install -y redis-server
WORKDIR /www/app
COPY ./ ./
RUN sbt compile
EXPOSE 8090
CMD /etc/init.d/redis-server start && sbt run