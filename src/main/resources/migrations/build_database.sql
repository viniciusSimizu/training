-- =================== Structure =====================
CREATE SEQUENCE course_code_seq;
CREATE TABLE course (
    code INTEGER NOT NULL PRIMARY KEY DEFAULT NEXTVAL('course_code_seq'),
    name VARCHAR(100) NOT NULL,
    description VARCHAR(4000) NOT NULL,
    duration INTEGER NOT NULL
);
ALTER SEQUENCE course_code_seq
OWNED BY course.code;

CREATE SEQUENCE employee_code_seq;
CREATE TABLE employee (
    code INTEGER NOT NULL PRIMARY KEY DEFAULT NEXTVAL('employee_code_seq'),
    name VARCHAR(200) NOT NULL,
    cpf CHAR(11) NOT NULL,
    birth_Day DATE NOT NULL,
    role VARCHAR(200) NOT NULL,
    admission DATE NOT NULL,
    status BOOLEAN NOT NULL
);
ALTER SEQUENCE employee_code_seq
OWNED BY employee.code;

CREATE SEQUENCE class_code_seq;
CREATE TABLE class (
    code INTEGER NOT NULL PRIMARY KEY DEFAULT NEXTVAL('class_code_seq'),
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    location VARCHAR(200),
    course_code INTEGER NOT NULL
);
ALTER SEQUENCE class_code_seq
OWNED BY class.code;

CREATE SEQUENCE classes_participants_code_seq;
CREATE TABLE classes_participants (
    code INTEGER NOT NULL PRIMARY KEY DEFAULT NEXTVAL('classes_participants_code_seq'),
    class_code INTEGER NOT NULL,
    employee_code INTEGER NOT NULL
);
ALTER SEQUENCE classes_participants_code_seq
OWNED BY classes_participants.code;

ALTER TABLE class
ADD CONSTRAINT fk_class_course
FOREIGN KEY (course_code)
REFERENCES course (code)
ON DELETE CASCADE;

ALTER TABLE classes_participants
ADD CONSTRAINT fk_classes_participants_class
FOREIGN KEY (class_code)
REFERENCES class (code)
ON DELETE CASCADE;

ALTER TABLE classes_participants
ADD CONSTRAINT fk_classes_participants_employee
FOREIGN KEY (employee_code)
REFERENCES employee (code);

-- =================== Populate =====================
INSERT INTO employee
(name, cpf, birth_day, role, admission, status)
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
