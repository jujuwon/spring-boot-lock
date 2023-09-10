FROM mysql

ENV MYSQL_ROOT_PASSWORD=1234
ENV MYSQL_DATABASE=example

WORKDIR /
ADD ./init-db.sql /docker-entrypoint-initdb.d

CMD ["mysqld"]
