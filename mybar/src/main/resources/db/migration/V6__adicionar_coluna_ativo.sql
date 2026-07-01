-- Adiciona a coluna de soft delete nas tabelas de domínio
ALTER TABLE tipo_item ADD COLUMN ativo BOOLEAN DEFAULT TRUE;
ALTER TABLE item_cardapio ADD COLUMN ativo BOOLEAN DEFAULT TRUE;