DROP DATABASE IF EXISTS shiro;
create DATABASE shiro;
use shiro;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS roles_permissions;
create table users(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
)ENGINE = InnoDB DEFAULT CHARSET = utf8;
INSERT INTO users VALUES (DEFAULT ,'Mark','123456');

CREATE TABLE user_roles(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  role_name VARCHAR(255) NOT NULL ,
  username VARCHAR(255) NOT NULL
)ENGINE InnoDB DEFAULT CHARSET = utf8;
INSERT INTO user_roles VALUES (DEFAULT ,'admin','Mark');

CREATE TABLE roles_permissions(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  role_name VARCHAR(255) NOT NULL ,
  permission VARCHAR(255) NOT NULL
)ENGINE InnoDB DEFAULT CHARSET = utf8;
INSERT INTO roles_permissions VALUES (DEFAULT ,'admin','user:delete');

DROP TABLE IF EXISTS test_users;
DROP TABLE IF EXISTS test_user_roles;
DROP TABLE IF EXISTS test_roles_permissions;
create table test_users(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
)ENGINE = InnoDB DEFAULT CHARSET = utf8;
INSERT INTO test_users VALUES (DEFAULT ,'xiaoming','123456');

CREATE TABLE test_user_roles(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  role_name VARCHAR(255) NOT NULL ,
  username VARCHAR(255) NOT NULL
)ENGINE InnoDB DEFAULT CHARSET = utf8;
INSERT INTO test_user_roles VALUES (DEFAULT ,'admin','xiaoming');

CREATE TABLE test_roles_permissions(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  role_name VARCHAR(255) NOT NULL ,
  permission VARCHAR(255) NOT NULL
)ENGINE InnoDB DEFAULT CHARSET = utf8;
INSERT INTO test_roles_permissions VALUES (DEFAULT ,'admin','user:update');