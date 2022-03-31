CREATE TABLE venda (
	codigo BIGINT PRIMARY KEY AUTO_INCREMENT,
	data DATE NOT NULL,
	ativo BOOLEAN NOT NULL,
	codigo_cliente BIGINT NOT NULL,
	codigo_vendedor BIGINT NOT NULL,
	FOREIGN KEY (codigo_cliente) REFERENCES cliente(codigo),
	FOREIGN KEY (codigo_vendedor) REFERENCES vendedor(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

INSERT INTO venda(data,ativo, codigo_cliente, codigo_vendedor) VALUES ('2021-01-02',true, 1, 1);
INSERT INTO venda(data,ativo, codigo_cliente, codigo_vendedor) VALUES ('2021-01-08',true, 1, 1);
INSERT INTO venda(data,ativo, codigo_cliente, codigo_vendedor) VALUES ('2021-01-25',true, 1, 1);
INSERT INTO venda(data,ativo, codigo_cliente, codigo_vendedor) VALUES ('2021-01-12',true, 2, 2);
INSERT INTO venda(data,ativo, codigo_cliente, codigo_vendedor) VALUES ('2021-01-12',true, 3, 2);
INSERT INTO venda(data,ativo, codigo_cliente, codigo_vendedor) VALUES ('2021-01-30',true, 3, 1);
INSERT INTO venda(data,ativo, codigo_cliente, codigo_vendedor) VALUES ('2021-02-02',true, 4, 1);
INSERT INTO venda(data,ativo, codigo_cliente, codigo_vendedor) VALUES ('2021-02-02',true, 5, 2);
INSERT INTO venda(data,ativo, codigo_cliente, codigo_vendedor) VALUES ('2021-02-02',true, 7, 2);
INSERT INTO venda(data,ativo, codigo_cliente, codigo_vendedor) VALUES ('2021-02-09',true, 7, 2);
INSERT INTO venda(data,ativo, codigo_cliente, codigo_vendedor) VALUES ('2021-02-12',true, 8, 2);
INSERT INTO venda(data,ativo, codigo_cliente, codigo_vendedor) VALUES ('2021-02-15',true, 9, 1);
INSERT INTO venda(data,ativo, codigo_cliente, codigo_vendedor) VALUES ('2021-02-17',true, 9, 1);
INSERT INTO venda(data,ativo, codigo_cliente, codigo_vendedor) VALUES ('2021-02-17',true, 6, 1);