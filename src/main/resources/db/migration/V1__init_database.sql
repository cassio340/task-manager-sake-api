CREATE TABLE tb_user(
    id SERIAL PRIMARY KEY ,
    name VARCHAR (30) NOT NULL,
    email VARCHAR(300) NOT NULL ,
    password VARCHAR(20) NOT NULL
);
CREATE TABLE tb_appointment(
    id SERIAL PRIMARY KEY ,
    name VARCHAR (30) NOT NULL,
    description VARCHAR(200) NOT NULL ,
    date DATE NOT NULL ,
    time TIME NOT NULL
);