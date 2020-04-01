CREATE TABLE role (
    id int NOT NULL,
    name varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user (
    id bigint NOT NULL AUTO_INCREMENT,
    username varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    first_name varchar(255),
    last_name varchar(255),
    role_id int,
    CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES role(id),
    PRIMARY KEY (id)
);

CREATE TABLE department (
    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE doctor (
    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    post varchar(255),
    department_id bigint,
    office varchar(255),
    phone_number varchar(255) NOT NULL,
    CONSTRAINT fk_department_id FOREIGN KEY (department_id) REFERENCES department(id),
    PRIMARY KEY (id)
);

CREATE TABLE ticket (
    id bigint NOT NULL AUTO_INCREMENT,
    doctor_id bigint NOT NULL,
    time TIMESTAMP NOT NULL,
    user_id bigint,
    CONSTRAINT fk_specialist_id FOREIGN KEY (doctor_id) REFERENCES doctor(id),
    PRIMARY KEY (id)
);

-- CREATE TABLE patient (
--     id bigint NOT NULL AUTO_INCREMENT,
--     name varchar(255) NOT NULL,
--     address varchar(255),
--     phone_number varchar(255),
--     PRIMARY KEY (id)
-- );

