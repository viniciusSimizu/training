DELETE FROM turma_participante AS participante
USING turma
WHERE
	participante.turma_id = turma.codigo
	AND turma.curso_id = ?

