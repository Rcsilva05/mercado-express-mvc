-- Dados de exemplo carregados automaticamente no perfil H2 (memoria),
-- para que a tela de listagem ja apareca preenchida em uma demonstracao
-- ou no deploy publico.

INSERT INTO TDS_MVC_TB_MERCADO (ID, NOME, TIPO, SETOR, TAMANHO, PRECO) VALUES (1, 'Meia Cano Alto', 'Vestuario', 'Bazar', 'Unico', 12.90);
INSERT INTO TDS_MVC_TB_MERCADO (ID, NOME, TIPO, SETOR, TAMANHO, PRECO) VALUES (2, 'Detergente Neutro', 'Limpeza', 'Higiene', '500ml', 3.49);
INSERT INTO TDS_MVC_TB_MERCADO (ID, NOME, TIPO, SETOR, TAMANHO, PRECO) VALUES (3, 'Maca Fuji', 'Alimento', 'Hortifruti', 'Kg', 8.99);
INSERT INTO TDS_MVC_TB_MERCADO (ID, NOME, TIPO, SETOR, TAMANHO, PRECO) VALUES (4, 'Sabao em Po 1Kg', 'Limpeza', 'Bazar', '1kg', 14.50);
INSERT INTO TDS_MVC_TB_MERCADO (ID, NOME, TIPO, SETOR, TAMANHO, PRECO) VALUES (5, 'Banana Prata', 'Alimento', 'Hortifruti', 'Kg', 5.49);

-- IMPORTANTE: os INSERTs acima gravam os IDs 1 a 5 explicitamente, mas a
-- sequence criada pelo Hibernate comeca do 1. Sem o ajuste abaixo, o primeiro
-- produto cadastrado pela tela tentaria usar o ID 1 e o banco recusaria por
-- violacao de chave primaria. Reiniciando a sequence a partir do 6, os novos
-- cadastros continuam a numeracao normalmente.
ALTER SEQUENCE SEQ_TDS_MVC_TB_MERCADO RESTART WITH 6;
