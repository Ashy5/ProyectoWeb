CREATE TABLE IF NOT EXISTS roles
(
    id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE
);

INSERT IGNORE INTO roles (nombre)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN'),
       ('ROLE_SUPER');