CREATE TABLE conta (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       numero INT NOT NULL UNIQUE,
                       status VARCHAR(50) NOT NULL,
                       data_abertura DATE NOT NULL,
                       hora_abertura TIME NOT NULL,
                       cliente_id BIGINT,
                       CONSTRAINT fk_conta_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

CREATE TABLE pagamento (
                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                           forma VARCHAR(50) NOT NULL,
                           valor DECIMAL(10,2) NOT NULL,
                           ativo BOOLEAN NOT NULL DEFAULT TRUE,
                           conta_id BIGINT NOT NULL,
                           quem_lancou_pg_codigo INT NOT NULL,
                           quem_excluiu_pg_codigo INT,
                           CONSTRAINT fk_pagamento_conta FOREIGN KEY (conta_id) REFERENCES conta(id),
                           CONSTRAINT fk_pagamento_lancou FOREIGN KEY (quem_lancou_pg_codigo) REFERENCES usuario(codigo),
                           CONSTRAINT fk_pagamento_excluiu FOREIGN KEY (quem_excluiu_pg_codigo) REFERENCES usuario(codigo)
);

CREATE TABLE itens_da_conta (
                                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                quantidade INT NOT NULL,
                                ativo BOOLEAN NOT NULL DEFAULT TRUE,
                                conta_id BIGINT NOT NULL,
                                item_cardapio_codigo INT NOT NULL,
                                quem_lancou_codigo INT NOT NULL,
                                quem_removeu_codigo INT,
                                data_solicitacao DATE NOT NULL,
                                hora_solicitacao TIME NOT NULL,
                                data_recebimento_cozinha DATE,
                                hora_recebimento_cozinha TIME,
                                data_entrega_cozinha DATE,
                                hora_entrega_cozinha TIME,
                                data_recebimento_bar DATE,
                                hora_recebimento_bar TIME,
                                data_entrega_bar DATE,
                                hora_entrega_bar TIME,
                                CONSTRAINT fk_itens_conta FOREIGN KEY (conta_id) REFERENCES conta(id),
                                CONSTRAINT fk_itens_cardapio FOREIGN KEY (item_cardapio_codigo) REFERENCES item_cardapio(codigo),
                                CONSTRAINT fk_itens_lancou FOREIGN KEY (quem_lancou_codigo) REFERENCES usuario(codigo),
                                CONSTRAINT fk_itens_removeu FOREIGN KEY (quem_removeu_codigo) REFERENCES usuario(codigo)
);