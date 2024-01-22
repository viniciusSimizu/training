SELECT
	turma.codigo AS codigo,
	turma.inicio AS inicio,
	turma.fim AS fim,
	turma.local AS local
FROM turma
INNER JOIN turma_participante participante
	ON participante.turma_id = turma.codigo
WHERE turma.curso_id = ?
	AND participante.funcionario_id = ?
GROUP BY turma.codigo
ORDER BY inicio DESC
LIMIT 1

