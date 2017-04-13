USE winapi_handbook;

DROP TABLE IF EXISTS winapi_class ;

CREATE TABLE winapi_class (
  id          BIGINT        NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name        CHAR(30)      NOT NULL,
  description VARCHAR(1000) NOT NULL
);

CREATE UNIQUE INDEX classx
  ON winapi_class (id);

CREATE TABLE winapi_function (
  id          BIGINT        NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name        CHAR(30)      NOT NULL,
  description VARCHAR(1000) NOT NULL,
  class_id    BIGINT        NOT NULL,
  FOREIGN KEY (class_id)
  REFERENCES winapi_class (id)
    ON DELETE CASCADE
);

CREATE UNIQUE INDEX functionx
  ON winapi_function (id);

CREATE TABLE winapi_parameter (
  id          BIGINT   NOT NULL PRIMARY KEY AUTO_INCREMENT,
  type        CHAR(30) NOT NULL,
  name        CHAR(30) NOT NULL,
  function_id BIGINT   NOT NULL,
  FOREIGN KEY (function_id) REFERENCES winapi_function (id)
    ON DELETE CASCADE
);

CREATE UNIQUE INDEX paramx
  ON winapi_parameter (id);