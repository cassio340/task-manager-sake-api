CREATE TABLE tb_user(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR (30) NOT NULL,
    email VARCHAR(300) NOT NULL ,
    password VARCHAR(255) NOT NULL
);
CREATE TABLE tb_appointment(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR (30) NOT NULL,
    description VARCHAR(200) NOT NULL ,
    date DATE NOT NULL ,
    time TIME NOT NULL,
    user_id BIGINT,

    CONSTRAINT fk_appointment_user FOREIGN KEY (user_id) REFERENCES tb_user(id)
);