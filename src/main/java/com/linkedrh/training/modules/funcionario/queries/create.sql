INSERT INTO funcionario
(nome, cpf, nascimento, cargo, admissao)
VALUES (?, ?, ?, ?, ?)
RETURNING codigo

