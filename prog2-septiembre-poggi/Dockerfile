FROM mysql:8.0

ENV MYSQL_ROOT_PASSWORD=admin
ENV MYSQL_DATABASE=PROG2_SE26_Poggi

COPY init.sql /docker-entrypoint-initdb.d/

EXPOSE 3306

CMD ["mysqld"]