-- =================== Structure =====================
CREATE TABLE curso (
	codigo SERIAL PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	descricao VARCHAR(4000),
	duracao INTEGER NOT NULL,
	ativo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE funcionario (
	codigo SERIAL PRIMARY KEY,
	nome VARCHAR(200) NOT NULL,
	cpf CHAR(11) NOT NULL,
	nascimento DATE NOT NULL,
	cargo VARCHAR(200) NOT NULL,
	admissao DATE NOT NULL,
	ativo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE turma (
	codigo SERIAL PRIMARY KEY,
	inicio DATE NOT NULL,
	fim DATE NOT NULL,
	local VARCHAR(200),
	curso_id INTEGER NOT NULL
);

CREATE TABLE turma_participante (
	codigo SERIAL PRIMARY KEY,
	turma_id INTEGER NOT NULL,
	funcionario_id INTEGER NOT NULL
);

ALTER TABLE turma
ADD CONSTRAINT fk_turma_curso
FOREIGN KEY (curso_id)
REFERENCES curso (codigo);

ALTER TABLE turma_participante
ADD CONSTRAINT fk_turma_participante_turma
FOREIGN KEY (turma_id)
REFERENCES turma (codigo);

ALTER TABLE turma_participante
ADD CONSTRAINT fk_turma_participante_funcionario
FOREIGN KEY (funcionario_id)
REFERENCES funcionario (codigo);

-- =================== Populate =====================
INSERT INTO funcionario
(nome, cpf, nascimento, cargo, admissao, ativo)
VALUES
(
    'Estevan Tiaraju',
    '12345678912',
    '2002/05/04',
    'Fotógrafo',
    '2020/08/01',
    TRUE
),
(
    'Otávio Araujo',
    '98765432198',
    '1998/02/20',
    'Gerente de logística',
    '2019/12/10',
    TRUE
),
(
    'Biel Fiuza',
    '12309845676',
    '2003/12/30',
    'Quality Assurance',
    '2021/01/01',
    FALSE
);
