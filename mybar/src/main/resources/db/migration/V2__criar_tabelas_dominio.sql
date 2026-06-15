CREATE TABLE cliente (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         nome VARCHAR(255) NOT NULL,
                         cpf VARCHAR(14) NOT NULL UNIQUE,
                         celular VARCHAR(20),
                         sexo VARCHAR(15)
);

CREATE TABLE tipo_item (
                           codigo INT PRIMARY KEY,
                           descricao VARCHAR(255) NOT NULL,
                           gorjeta DECIMAL(5,2) NOT NULL,
                           cozinha BOOLEAN NOT NULL
);

CREATE TABLE item_cardapio (
                               codigo INT PRIMARY KEY,
                               descricao VARCHAR(255) NOT NULL,
                               valor DECIMAL(10,2) NOT NULL,
                               tipo_item_codigo INT NOT NULL,
                               CONSTRAINT fk_item_tipo FOREIGN KEY (tipo_item_codigo) REFERENCES tipo_item(codigo)
);

CREATE TABLE configuracao (
                              id INT PRIMARY KEY AUTO_INCREMENT,
                              valor_ingresso_masc DECIMAL(10,2) NOT NULL,
                              valor_ingresso_femin DECIMAL(10,2) NOT NULL,
                              modo_operacao VARCHAR(50) NOT NULL,
                              data DATE NOT NULL,
                              hora TIME NOT NULL
);