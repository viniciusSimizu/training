FROM postgres:16.1

COPY migrations/*.sql /docker-entrypoint-initdb.d/
EXPOSE 5432
