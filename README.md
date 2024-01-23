# API de treinamentos para a LinkedRH

## Pre-Requisitos
- [ ] Docker instalado

## Configuração

Dotenv
- DB_HOST - Endereço de host do banco de dados (Default: db)
- DB_USER - Nome de usuário do banco de dados (Default: root)
- DB_PASSW - Senha de usuário do banco de dados (Default: senha)
- DB_NAME - Nome do banco de dados (Default: training)

## Rodar projeto
```sh
sudo docker compose up -d
```

## Documentação dos Endpoints

### Swagger em arquivo
Acesse no seu navegador, a URI (http://127.0.0.1:3000)

### Swagger gerado
Acesse no seu navegador, a URI (http://127.0.0.1:8080/swagger-ui/index.html)

## Teste
```sh
./mvnw test
```
