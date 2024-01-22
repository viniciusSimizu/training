SELECT
	funcionario.codigo AS codigo,
	funcionario.nome AS nome,
	funcionario.cpf AS cpf,
	funcionario.nascimento AS nascimento,
	funcionario.cargo AS cargo,
	funcionario.admissao AS admissao,
	funcionario.ativo AS ativo
FROM funcionario
INNER JOIN turma_participante participante
	ON participante.funcionario_id = funcionario.codigo
WHERE participante.turma_id = ?

