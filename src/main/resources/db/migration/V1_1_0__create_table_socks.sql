CREATE TABLE IF NOT EXISTS socks
(
    id          INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    color       VARCHAR(30) NOT NULL,
    cotton_part INT         NOT NULL CHECK ( cotton_part >= 0 ),
    quantity    INT         NOT NULL CHECK ( quantity >= 0 )
);

ALTER TABLE socks
    ADD UNIQUE unique_socks (color, cotton_part);