CREATE TABLE TB_APPLE (
    id BIGINT NOT NULL AUTO_INCREMENT,
    SOME_VALUE1 VARCHAR(255),
    SOME_VALUE2 INT,
    SOME_VALUE3 DATETIME,
    PRIMARY KEY (id)
);

CREATE TABLE TB_BANANA (
    id BIGINT NOT NULL AUTO_INCREMENT,
    SOME_VALUE1 VARCHAR(255),
    SOME_VALUE2 DECIMAL(19,5),
    SOME_VALUE3 DATE,
    PRIMARY KEY (id)
);