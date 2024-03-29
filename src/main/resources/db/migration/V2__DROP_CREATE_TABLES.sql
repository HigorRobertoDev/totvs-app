DROP TABLE TELEFONE_CLIENTE;
DROP TABLE CLIENTE;

CREATE TABLE CLIENTE(
    COD_CLIENTE INT PRIMARY KEY AUTO_INCREMENT,
    NOME_CLIENTE VARCHAR(10) NOT NULL,
    ENDERECO_CLIENTE VARCHAR(100) NOT NULL,
    BAIRRO_CLIENTE VARCHAR(100) NOT NULL
);

CREATE TABLE TELEFONE_CLIENTE(
    COD_TELEFONE INT PRIMARY KEY AUTO_INCREMENT,
    NUMERO_TELEFONE VARCHAR(20) NOT NULL,
    COD_CLIENTE INT,
    FOREIGN KEY (COD_CLIENTE) REFERENCES CLIENTE(COD_CLIENTE)
);
