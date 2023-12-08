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
    status BIT NOT NULL
);
ALTER SEQUENCE employee_code_seq
OWNED BY employee.code;

CREATE SEQUENCE classes_code_seq;
CREATE TABLE classes (
    code INTEGER NOT NULL PRIMARY KEY DEFAULT NEXTVAL('classes_code_seq'),
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    train_location VARCHAR(200),
    course_code INTEGER NOT NULL
);
ALTER SEQUENCE classes_code_seq
OWNED BY classes.code;

CREATE SEQUENCE classes_participants_code_seq;
CREATE TABLE classes_participants (
    code INTEGER NOT NULL PRIMARY KEY DEFAULT NEXTVAL('classes_participants_code_seq'),
    classes_code INTEGER NOT NULL,
    employee_code INTEGER NOT NULL
);
ALTER SEQUENCE classes_participants_code_seq
OWNED BY classes_participants.code;

ALTER TABLE classes
ADD CONSTRAINT fk_classes_course
FOREIGN KEY (course_code)
REFERENCES course (code)
ON DELETE CASCADE;

ALTER TABLE classes_participants
ADD CONSTRAINT fk_classes_participants_classes
FOREIGN KEY (classes_code)
REFERENCES classes (code);

ALTER TABLE classes_participants
ADD CONSTRAINT fk_classes_participants_employee
FOREIGN KEY (employee_code)
REFERENCES employee (code);
