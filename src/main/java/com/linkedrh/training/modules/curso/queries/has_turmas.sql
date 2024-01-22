SELECT 1
FROM curso
INNER JOIN turma ON turma.curso_id = curso.codigo
WHERE curso.codigo = ?
LIMIT 1

