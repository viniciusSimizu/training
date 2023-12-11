# Spring API

## Como rodar a aplicação

### Método 1: Docker

```docker
sudo docker compose up -d --build
```

### Método 2: Java + Postgres Drive

#### Execute o script sql no banco Postgres

```shell
src/main/resources/migrations/build_database.sql
```

```java
java -jar target/training-0.0.1-SNAPSHOT.jar
```

## Endpoints

### Curso

    - Criar Curso:
        POST /course
        DATA
        {
            "name":"",
            "description":"",
            "duration": 0
        }

    - Atualizar Curso:
        PUT /course/{código}
        DATA
        {
            "name":"",
            "description":"",
            "duration": 0
        }


    - Listar Cursos:
        GET /course

    - Deletar Curso:
        DELETE /course/{código}

### Turma

    - Criar Turma:
        POST /class
        DATA
        {
            "startDate":"dd-MM-yyyy",
            "endDate":"dd-MM-yyyy",
            "location":"",
            "courseCode": 0
        }

    - Atualizar Turma:
        PUT /class/{código}
        DATA
        {
            "startDate":"dd-MM-yyyy",
            "endDate":"dd-MM-yyyy",
            "location":""
        }


    - Listar Turmas por Curso:
        GET /class/{código_do_curso}

    - Deletar Turma:
        DELETE /class/{código}

    - Criar Participante na Turma
        POST /class/participant
        DATA
        {
            "classCode":0,
            "employeeCode":0
        }

    - Deletar Participante da Turma
        DELETE /class/participant/{código_da_participante}

    - Listar Participantes da Turma
        GET /class/participant/{código_da_turma}
