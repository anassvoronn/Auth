CREATE SEQUENCE users_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE users (
    id INTEGER NOT NULL DEFAULT nextval('users_id_seq') PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL CHECK (role IN ('USER', 'ADMIN'))
);

ALTER SEQUENCE users_id_seq OWNED BY users.id;

INSERT INTO users (username, password, email, first_name, last_name, role) VALUES
('john_smith', '$2a$10$xJwL5v5Jz5UZJZ5UZJZ5Ue', 'johnsmith558@example.com', 'John', 'Smith', 'ADMIN'),
('sarah_j', '$2a$10$yKvL6w6Kz6VKvL6w6Kz6Ve', 'sarahj0hns0n@example.com', 'Sarah', 'Johnson', 'ADMIN'),
('mike_brown', '$2a$10$zLwM7x7Lz7WLwM7x7Lz7We', 'micha4lbr0wn@example.com', 'Michael', 'Brown', 'USER'),
('emily_d', '$2a$10$aNxO8y8Nz8XNxO8y8Nz8Xe', 'em1lydav1s@example.com', 'Emily', 'Davis', 'USER'),
('d_wilson', '$2a$10$bOyP9z9Oz9YOyP9z9Oz9Ye', 'davidwilsooon@example.com', 'David', 'Wilson', 'USER'),
('jess_taylor', '$2a$10$cPzQ0a0Pz0QPzQ0a0Pz0Qe', 'jtaylor@example.com', 'Jessica', 'Taylor', 'USER'),
('dan_anderson', '$2a$10$dQaR1b1Qa1RQaR1b1Qa1Re', 'danielanderson@example.com', 'Daniel', 'Anderson', 'USER');