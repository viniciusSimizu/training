FROM postgres:16.1-alpine3.18

COPY /src/main/resources/migrations/* /docker-entrypoint-initdb.d/

EXPOSE 3000
