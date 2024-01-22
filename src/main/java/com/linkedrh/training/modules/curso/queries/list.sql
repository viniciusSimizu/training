SELECT
	curso.codigo AS codigo,
	curso.nome AS nome,
	curso.duracao AS duracao,
	curso.ativo AS ativo,
	COUNT(turma.codigo) AS quantidadeTurmas,
	MIN(turma.inicio) FILTER(WHERE turma.inicio >= NOW()) AS dataInicioMaisProxima
FROM curso
LEFT JOIN turma ON turma.curso_id = curso.codigo
GROUP BY curso.codigo

