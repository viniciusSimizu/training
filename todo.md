# Treinamento

## Rotas

- Curso:

  [X] LIST
  [X] LIST by range of Inicio and Fim
  [X] POST
  [X] PUT
  [X] DELETE

- Funcionário:

  [X] POST
  [X] PUT
  [X] PUT campo ativo
  [X] LIST where ativo

- Turma:

  [X] POST
  [X] PUT
  [X] DELETE
  [X] LIST by Curso
  [X] FIND by Curso and Funcionario

  - Participante:
    [X] POST
    [X] DELETE by Funcionario
    [X] LIST by turma
    [X] DELETE by Funcionario and Turma

## Data

- Curso:

  - Código: int
  - Nome: String
  - Descrição: String
  - Duração: int
  - Inativo: boolean

  - Turmas: List<Turma>

  [//]: # "Virtual"

  - QuantidadeDeTurmas: int
  - DataDaTurmaMaisProxima: LocalDate

- Funcionario:

  - Código: int
  - Nome: String
  - CPF: String
  - Nascimento: LocalDate
  - Cargo: String
  - Admissão: LocalDate
  - Status: boolean

  - Turmas: List<Turma>

- Turma:

  - Codigo: int
  - DataDeInicio: LocalDate
  - DataDeFim: LocalDate
  - Local: String

  - final Curso: Curso

  - Participantes: List<Funcionario>

  [//]: # "Virtual"

  - QuantidadeDeParticipantes: int

## Regras de Negócio

[X] Todas as operações que alteram o banco de dados, devem ser feitas usando transações
[X] Todos os endpoints devem registrar um log no nivel INFO no formato "O serviço <serviço> foi chamado", usando o log4j
[X] Todos os endpoints devem registrar um log no nivel DEBUG com o SQL rodado, usando o log4j
[X] Todos os endpoints devem verificar se o cabeçalho "Authorization" tem o valor "Bearer token_exercicio_rest", caso as condições não sejam atendidas, retornar HTTP CODE 403
[X] Criar testes usando JUnit
[X] Documentar a API usando Swagger

- Curso:

  [X] Quando o Curso é deletado, verificar se tem Turmas atreladas a ele, caso tenha, marcar o campo Inativo para True
  [X] Quando o Curso é deletado com o parâmetro "force=true", deletar o Curso e suas Turmas (não usar o atributo CASCADE do sql; usar transações do sql)
  [X] Quando os Cursos forem listados, trazer o nome; duração; Inativo; QuantidadeDeTurmas; DataDaTurmaMaisProxima;

- Turma:
  [X] Quando as Turmas forem listadas, filtrar pelo parâmetro Código do Curso na rota, ordenar por DataDeInicio e DataDeFim em ordem CRESCENTE
  [X] Quando a Turma for buscada com o Código do Curso e Funcionario, tentar retornar a Turma com a maior DataDeInicio, caso encontrado, retornar Código; DataDeInicio; DataDeFim; Local;
  [X] Quando as Turmas forem listadas pelo intervalo de DataDeInicio e DataDeFim, trazer Turmas que estão dentro desse intervalo e ordena-los pelo Nome do Curso

  - Participante:
    [X] Quando os Participantes da Turma fores listados, caso for fornecido o parâmetro opcional de "active", filtrar pelo Status do Funcionario, ordenar pelo Nome do Funcionario
