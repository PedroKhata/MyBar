-- 1. Desliga temporariamente a trava de chaves estrangeiras
SET FOREIGN_KEY_CHECKS = 0;

-- 2. Remove o AUTO_INCREMENT mantendo a coluna como INT NOT NULL
ALTER TABLE usuario MODIFY codigo INT NOT NULL;

-- 3. Religa a trava de chaves estrangeiras (MUITO IMPORTANTE)
SET FOREIGN_KEY_CHECKS = 1;