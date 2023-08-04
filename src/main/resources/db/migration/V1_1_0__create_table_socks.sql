CREATE TABLE IF NOT EXISTS socks
(
    id          SERIAL      NOT NULL PRIMARY KEY,
    color       VARCHAR(30) NOT NULL,
    cotton_part INT         NOT NULL CHECK ( cotton_part >= 0 ),
    quantity    INT         NOT NULL CHECK ( quantity >= 0 ),
    unique (color, cotton_part)
);