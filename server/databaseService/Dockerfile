FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD=123456

ENV MYSQL_DATABASE=localoge

COPY ./init.sql /docker-entrypoint-initdb.d/

EXPOSE 3306
