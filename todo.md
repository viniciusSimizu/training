# Treinamento

## Rotas

- Curso:

  [X] LIST
  [X] POST
  [X] PUT
  [X] DELETE

- Funcionário:

  [X] POST
  [ ] PUT
  [X] PUT campo ativo
  [ ] LIST by status
  [ ] DELETE
  [ ] DELETE from Turmas

- Turma:

  [X] POST
  [ ] PUT
  [ ] DELETE
  [X] LIST by Curso
  [ ] LIST by range of Inicio and Fim
  [ ] FIND by Curso and Funcionario

  - Participante:
    [X] POST
    [X] DELETE
    [X] LIST by turma

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

[ ] Todas as operações que alteram o banco de dados, devem ser feitas usando transações
[ ] Todos os endpoints devem registrar um log no nivel INFO no formato "O serviço <serviço> foi chamado", usando o log4j
[ ] Todos os endpoints devem registrar um log no nivel DEBUG com o SQL rodado, usando o log4j
[ ] Todos os endpoints devem verificar se o cabeçalho "Authorization" tem o valor "Bearer token_exercicio_rest", caso as condições não sejam atendidas, retornar HTTP CODE 403
[ ] Criar testes usando JUnit
[ ] Documentar a API usando Swagger

- Curso:

  [X] Quando o Curso é deletado, verificar se tem Turmas atreladas a ele, caso tenha, marcar o campo Inativo para True
  [X] Quando o Curso é deletado com o parâmetro "force=true", deletar o Curso e suas Turmas (não usar o atributo CASCADE do sql; usar transações do sql)
  [ ] Quando os Cursos forem listados, trazer o nome; duração; Inativo; QuantidadeDeTurmas; DataDaTurmaMaisProxima;

- Turma:
  [ ] Quando as Turmas forem listadas, filtrar pelo parâmetro Código do Curso da rota, ordenar por DataDeInicio e DataDeFim em ordem CRESCENTE
  [ ] Quando a Turma for buscada com o Código do Curso e Funcionario, tentar retornar a Turma com a maior DataDeInicio, caso encontrado, retornar Código; DataDeInicio; DataDeFim; Local;
  [ ] Quando as Turmas forem listadas pelo intervalo de DataDeInicio e DataDeFim, trazer Turmas que estão dentro desse intervalo e ordena-los pelo Nome do Curso

  - Funcionario:
    [ ] Quando o Funcionario for deletado das Turmas, verificar se o Funcionario está Inativo, caso esteja, deleta-lo de todas as Turmas que ainda estão para serem iniciadas

  - Participante:
    [ ] Quando os Participantes da Turma fores listados, caso for fornecido o parâmetro opcional de "active", filtrar pelo Status do Funcionario, ordenar pelo Nome do Funcionario
