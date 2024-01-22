SELECT
	curso.codigo AS codigo,
	curso.nome AS nome,
	curso.duracao AS duracao,
	COUNT(turma.codigo) AS quantidadeTurmas
FROM curso
LEFT JOIN turma ON turma.curso_id = curso.codigo
GROUP BY curso.codigo
ORDER BY curso.nome

