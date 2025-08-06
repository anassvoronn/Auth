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

-- $2a$10$5zLAAnaiquOUNhiKOxIjjeq5d.PYNuBQzwQV1S8Lra00JIdFC4zUu = password123
INSERT INTO users (username, password, email, first_name, last_name, role) VALUES
('john_smith', '$2a$10$5zLAAnaiquOUNhiKOxIjjeq5d.PYNuBQzwQV1S8Lra00JIdFC4zUu', 'johnsmith558@example.com', 'John', 'Smith', 'ADMIN'),
('sarah_j', '$2a$10$5zLAAnaiquOUNhiKOxIjjeq5d.PYNuBQzwQV1S8Lra00JIdFC4zUu', 'sarahj0hns0n@example.com', 'Sarah', 'Johnson', 'ADMIN'),
('mike_brown', '$2a$10$5zLAAnaiquOUNhiKOxIjjeq5d.PYNuBQzwQV1S8Lra00JIdFC4zUu', 'micha4lbr0wn@example.com', 'Michael', 'Brown', 'USER'),
('emily_d', '$2a$10$5zLAAnaiquOUNhiKOxIjjeq5d.PYNuBQzwQV1S8Lra00JIdFC4zUu', 'em1lydav1s@example.com', 'Emily', 'Davis', 'USER'),
('d_wilson', '$2a$10$5zLAAnaiquOUNhiKOxIjjeq5d.PYNuBQzwQV1S8Lra00JIdFC4zUu', 'davidwilsooon@example.com', 'David', 'Wilson', 'USER'),
('jess_taylor', '$2a$10$5zLAAnaiquOUNhiKOxIjjeq5d.PYNuBQzwQV1S8Lra00JIdFC4zUu', 'jtaylor@example.com', 'Jessica', 'Taylor', 'USER'),
('dan_anderson', '$2a$10$5zLAAnaiquOUNhiKOxIjjeq5d.PYNuBQzwQV1S8Lra00JIdFC4zUu', 'danielanderson@example.com', 'Daniel', 'Anderson', 'USER');