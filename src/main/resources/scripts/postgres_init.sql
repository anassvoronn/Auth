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
('john_smith', 'john_smith', 'johnsmith558@example.com', 'John', 'Smith', 'ADMIN'),
('sarah_j', 'sarah_j', 'sarahj0hns0n@example.com', 'Sarah', 'Johnson', 'ADMIN'),
('mike_brown', 'mike_brown', 'micha4lbr0wn@example.com', 'Michael', 'Brown', 'USER'),
('emily_d', 'emily_d', 'em1lydav1s@example.com', 'Emily', 'Davis', 'USER'),
('d_wilson', 'd_wilson', 'davidwilsooon@example.com', 'David', 'Wilson', 'USER'),
('jess_taylor', 'jess_taylor', 'jtaylor@example.com', 'Jessica', 'Taylor', 'USER'),
('dan_anderson', 'dan_anderson', 'danielanderson@example.com', 'Daniel', 'Anderson', 'USER');