DELETE FROM turma_participante AS participante
USING turma
WHERE participante.funcionario_id = ?
	AND turma.inicio > NOW()
	AND participante.turma_id = turma.codigo

