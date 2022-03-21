CREATE TABLE vendedor (
	codigo BIGINT PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

INSERT INTO vendedor (nome) values ('Antonio Marcos');
INSERT INTO vendedor (nome) values ('Maria');