SELECT
	codigo, nome, cpf, nascimento, cargo, admissao, ativo
FROM funcionario
%s
ORDER BY nome ASC
