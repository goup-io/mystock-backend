INSERT INTO loja (cep, cnpj, complemento, nome, numero)
VALUES
    ('12345-678', '05.534.388/0001-24', 'Sala 101', 'Loja A', 101),
    ('54321-876', '11.222.333/0001-44', 'Apto 202', 'Loja B', 202),
    ('98765-432', '99.888.777/0001-55', 'Fundos', 'Loja C', 303);

INSERT INTO acesso_loja (descricao, tipo)
VALUES ('Caixa', 0), ('Área de Vendas', 1);

INSERT INTO loja_login (senha, username, acesso_loja_id, loja_id)
VALUES
    ('$2a$10$HV6jRjCZGmm.lKw3zYcEo.PkQihGcEmo3v4WJWJUpnBs4h/T5bmZK', 'vendasLoja', 2, 1),
    ('$2a$10$HV6jRjCZGmm.lKw3zYcEo.PkQihGcEmo3v4WJWJUpnBs4h/T5bmZK', 'caixaLoja', 1, 1);

insert into cargo
values (default, 'Vendedor', 'Vendedor'),(default, 'Gerente', 'Gerente'),(default, 'Admin', 'Admin');

INSERT INTO usuario ( nome, cargo_id, email, telefone, loja_id, codigo_venda)
VALUES
    ('Joao', 3, 'inaciofigueiredo13@gmail.com', '123456789', 1, 100),
    ('Pedro', 2, 'pedro@gmail.com', '(11) 94091-234', 2, 101),
    ('Afonso', 2, 'afonso@hotmail.com', '(22) 98765-4321', 3, 102),
    ('Ana', 1, 'ana@yahoo.com', '(33) 12345-6789', 1, 103),
    ('Lucas', 2, 'lucas@outlook.com', '(44) 55555-5555', 2, 104),
    ('Carla', 2, 'carla@gmail.com', '(55) 1234-5678', 2, 105),
    ('Mariana', 1, 'mariana@hotmail.com', '(66) 98765-4321', 1, 106),
    ('Gabriel', 3, 'gabriel@yahoo.com', '(77) 12345-6789', 3, 107),
    ('Juliana', 2, 'juliana@gmail.com', '(88) 55555-5555', 2, 108),
    ('Mateus', 1, 'mateus@outlook.com', '(99) 1234-5678', 1, 109),
    ('Rafaela', 1, 'rafaela@hotmail.com', '(00) 98765-4321', 1, 110),
    ('Bruno', 1, 'bruno@yahoo.com', '(11) 12345-6789', 1, 111),
    ('Fernanda', 1, 'fernanda@gmail.com', '(22) 55555-5555', 1, 112),
    ('Rodrigo', 1, 'rodrigo@outlook.com', '(33) 1234-5678', 1, 113),
    ('Amanda', 2, 'amanda@hotmail.com', '(44) 98765-4321', 2, 114);

insert into login(senha, username, usuario_id)
values
    ('$2a$10$HV6jRjCZGmm.lKw3zYcEo.PkQihGcEmo3v4WJWJUpnBs4h/T5bmZK', 'teste', 1),
    ('$2a$10$HV6jRjCZGmm.lKw3zYcEo.PkQihGcEmo3v4WJWJUpnBs4h/T5bmZK', 'gerente', 3);

INSERT INTO tipo (nome)
VALUES
    ('Sandália'),
    ('Tênis'),
    ('Bota'),
    ('Sapato'),
    ('Chinelo'),
    ('Sapatilha'),
    ('Scarpin'),
    ('Mocassim'),
    ('Rasteirinha'),
    ('Ankle boot'),
    ('Sneaker'),
    ('Oxford'),
    ('Espadrille'),
    ('Coturno'),
    ('Mule'),
    ('Alpargata'),
    ('Slip-on'),
    ('Plataforma'),
    ('Salto alto'),
    ('Salto baixo');

INSERT INTO categoria (nome)
VALUES
    ('Esportivo'),
    ('Casual'),
    ('Social'),
    ('Festa'),
    ('Trabalho'),
    ('Chinelo'),
    ('Sandália'),
    ('Tênis'),
    ('Bota'),
    ('Sapato'),
    ('Sapatilha'),
    ('Scarpin'),
    ('Mocassim'),
    ('Rasteirinha'),
    ('Ankle boot'),
    ('Sneaker'),
    ('Oxford'),
    ('Espadrille'),
    ('Coturno'),
    ('Mule');

INSERT INTO cor (nome)
VALUES
    ('Preto'),
    ('Branco'),
    ('Azul'),
    ('Vermelho'),
    ('Verde'),
    ('Amarelo'),
    ('Rosa'),
    ('Roxo'),
    ('Laranja'),
    ('Marrom'),
    ('Cinza'),
    ('Bege'),
    ('Turquesa'),
    ('Lilás'),
    ('Bordo'),
    ('Dourado'),
    ('Prata'),
    ('Champagne'),
    ('Pêssego'),
    ('Magenta');

INSERT INTO modelo (nome, categoria_id, tipo_id)
VALUES
    ('Sapatênis Casual', 1, 1),
    ('Sandalia Conforto', 2, 2),
    ('Bota Adventure', 3, 3),
    ('Sapato Social', 4, 4),
    ('Tênis Esportivo', 5, 5),
    ('Rasteirinha Feminina', 1, 2),
    ('Bota Montaria', 2, 3),
    ('Chinelo Slide', 3, 4),
    ('Scarpin Clássico', 4, 5),
    ('Tênis Skate', 5, 1),
    ('Bota Cano Curto', 1, 1),
    ('Tênis Casual', 2, 2),
    ('Sandália Anabela', 3, 3),
    ('Sapato Oxford', 4, 4),
    ('Rasteira Gladiadora', 5, 5),
    ('Bota Coturno', 1, 2),
    ('Chinelo Havaianas', 2, 3),
    ('Scarpin Salto Alto', 3, 4),
    ('Tênis Corrida', 4, 5),
    ('Sandália Plataforma', 5, 1);

INSERT INTO produto (cor_id, modelo_id, nome, valor_custo, valor_revenda)
VALUES
    (1, 1, 'Sapatênis Casual Preto', 50.00, 100.00),
    (2, 2, 'Sandalia Conforto Branca', 60.00, 110.00),
    (3, 3, 'Bota Adventure Azul', 70.00, 120.00),
    (4, 4, 'Sapato Social Vermelho', 80.00, 130.00),
    (5, 5, 'Tênis Esportivo Verde', 90.00, 140.00),
    (1, 6, 'Rasteirinha Feminina Preto', 55.00, 105.00),
    (2, 7, 'Bota Montaria Branca', 65.00, 115.00),
    (3, 8, 'Chinelo Slide Azul', 75.00, 125.00),
    (4, 9, 'Scarpin Clássico Vermelho', 85.00, 135.00),
    (5, 10, 'Tênis Skate Verde', 95.00, 145.00),
    (1, 11, 'Bota Cano Curto Preto', 60.00, 110.00),
    (2, 12, 'Tênis Casual Branco', 70.00, 120.00),
    (3, 13, 'Sandália Anabela Azul', 80.00, 130.00),
    (4, 14, 'Sapato Oxford Vermelho', 90.00, 140.00),
    (5, 15, 'Rasteira Gladiadora Verde', 100.00, 150.00),
    (1, 16, 'Bota Coturno Preto', 65.00, 115.00),
    (2, 17, 'Chinelo Havaianas Branco', 75.00, 125.00),
    (3, 18, 'Scarpin Salto Alto Azul', 85.00, 135.00),
    (4, 19, 'Tênis Corrida Vermelho', 95.00, 145.00),
    (5, 20, 'Sandália Plataforma Verde', 105.00, 155.00);

INSERT INTO tamanho (numero)
VALUES
    (33), (34), (35), (36), (37), (38), (39), (40), (41), (42), (43), (44), (45);

-- Inserção na tabela ETP sem conflitos de duplicados e distribuindo os produtos entre as lojas
INSERT INTO ETP (produto_id, loja_id, tamanho_id, item_promocional, codigo, quantidade)
VALUES
    (1, 1, 1,  'NAO', 'AB123', 20),
    (2, 1, 1,  'NAO', 'CD456', 10),
    (3, 1, 3,  'NAO', 'EF789', 10),
    (4, 1, 4,  'NAO', 'GH012', 20),
    (5, 1, 5,  'NAO', 'IJ345', 30),
    (6, 1, 1,  'NAO', 'KL678', 50),
    (7, 1, 1,  'NAO', 'MN901', 54),
    (8, 1, 3,  'NAO', 'OP234', 20),
    (9, 1, 4,  'NAO', 'QR567', 16),
    (10, 1, 5, 'NAO', 'ST890',  15),
    (11, 1, 1, 'NAO', 'UV123',  10),
    (12, 1, 2, 'NAO', 'WX456',  10),
    (13, 1, 3, 'NAO', 'YZ789',  5),
    (14, 1, 4, 'NAO', 'AB012',  5),
    (15, 2, 5, 'NAO', 'CD345',  5),
    (16, 2, 1, 'NAO', 'EF678',  5),
    (17, 2, 2, 'NAO', 'GH901',  5),
    (18, 2, 3, 'NAO', 'IJ234',  5),
    (19, 2, 4, 'NAO', 'KL567',  5),
    (20, 2, 5, 'NAO', 'MN890',  5);


INSERT INTO Tipo_venda (id, tipo, desconto)
VALUES
    (1, 'VAREJO', 0),
    (2, 'ATACADO', 20),
    (3, 'ESPECIAL', 50);

INSERT INTO tipo_pagamento(metodo)
VALUES
    ('CREDITO'),
    ('DEBITO'),
    ('PIX'),
    ('DINHEIRO');


-- Inserção na tabela StatusVenda
INSERT INTO Status_Venda (status)
VALUES
    ('PENDENTE'),
    ('FINALIZADA'),
    ('CANCELADA');

/*
-- Inserção na tabela Venda
INSERT INTO Venda (data_Hora, desconto, valor_Total, status_Venda_id, tipo_Venda_id, usuario_id)
VALUES
    ('2024-06-03 23:24:29', 0.00, 234.00, 1, 1, 1),
    ('2024-05-20 00:22:54', 0.00, 150.00, 1, 1, 1),
    ('2024-05-20 01:15:32', 10.00, 200.00, 2, 2, 1),
    ('2024-05-20 02:45:10', 5.00, 100.00, 1, 2, 1),
    ('2024-05-31 20:30:45', 15.00, 290.00, 2, 1, 3),
    ('2024-04-18 14:00:00', 8.00, 220.00, 1, 1, 1),
    ('2024-04-12 13:45:25', 12.00, 180.00, 2, 2, 1),
    ('2024-04-02 10:15:50', 5.00, 250.00, 2, 1, 2),
    ('2024-04-25 12:10:10', 7.00, 275.00, 1, 2, 3),
    ('2024-03-18 15:00:00', 8.00, 220.00, 1, 1, 1),
    ('2024-03-14 09:35:45', 10.00, 180.00, 2, 2, 1),
    ('2024-02-20 10:30:00', 8.00, 220.00, 1, 1, 1),
    ('2024-02-05 15:45:50', 12.00, 250.00, 2, 1, 2),
    ('2024-02-25 17:50:30', 7.00, 275.00, 1, 2, 3),
    ('2024-01-22 14:00:00', 8.00, 220.00, 1, 1, 1),
    ('2024-01-18 09:15:25', 10.00, 180.00, 2, 2, 1),
    ('2023-12-25 15:10:00', 8.00, 220.00, 1, 1, 1),
    ('2023-12-05 17:50:30', 7.00, 275.00, 1, 2, 3),
    ('2023-11-22 13:00:00', 8.00, 220.00, 1, 1, 1),
    ('2023-11-28 16:50:30', 7.00, 275.00, 1, 2, 3),
    ('2023-10-22 15:20:10', 6.00, 190.00, 1, 1, 3),
    ('2023-10-10 15:25:50', 10.00, 200.00, 2, 1, 3),
    ('2023-10-05 17:50:30', 7.00, 230.00, 1, 2, 3),
    ('2023-09-22 11:00:00', 5.00, 230.00, 2, 1, 3),
    ('2023-09-05 13:45:50', 9.00, 210.00, 2, 2, 3);

-- Inserção na tabela Pagamento (valores corrigidos para não exceder o valor total da venda)
INSERT INTO Pagamento (tipo_Pagamento_id, venda_id, valor, qtd_Parcelas)
VALUES
    (1, 1, 233.00, 1),  -- Corrigido para ser menor que 234.00
    (2, 2, 149.00, 3),  -- Corrigido para ser menor que 150.00
    (1, 3, 199.00, 1),  -- Corrigido para ser menor que 200.00
    (1, 4, 99.00, 1),   -- Corrigido para ser menor que 100.00
    (2, 5, 289.00, 3),  -- Corrigido para ser menor que 290.00
    (1, 6, 219.00, 1),  -- Corrigido para ser menor que 220.00
    (1, 7, 179.00, 1),  -- Corrigido para ser menor que 180.00
    (2, 8, 249.00, 3),  -- Corrigido para ser menor que 250.00
    (1, 9, 274.00, 1),  -- Corrigido para ser menor que 275.00
    (1, 10, 219.00, 1), -- Corrigido para ser menor que 220.00
    (2, 11, 179.00, 3), -- Corrigido para ser menor que 180.00
    (1, 12, 249.00, 1), -- Corrigido para ser menor que 250.00
    (1, 13, 274.00, 1), -- Corrigido para ser menor que 275.00
    (2, 14, 219.00, 3), -- Corrigido para ser menor que 220.00
    (1, 15, 219.00, 1), -- Corrigido para ser menor que 220.00
    (1, 16, 179.00, 1), -- Corrigido para ser menor que 180.00
    (2, 17, 199.00, 3), -- Corrigido para ser menor que 200.00
    (1, 18, 239.00, 1), -- Corrigido para ser menor que 240.00
    (1, 19, 259.00, 1), -- Corrigido para ser menor que 260.00
    (2, 20, 189.00, 3), -- Corrigido para ser menor que 190.00
    (1, 21, 199.00, 1), -- Corrigido para ser menor que 200.00
    (2, 22, 229.00, 3), -- Corrigido para ser menor que 230.00
    (1, 23, 209.00, 1), -- Corrigido para ser menor que 210.00
    (1, 24, 229.00, 1); -- Corrigido para ser menor que 230.00

*/
/*
-- Inserção na tabela HistoricoProduto
INSERT INTO Historico_Produto (data_Hora, produto_Venda_id, status_Historico_Produto_id)
VALUES
    ('2024-05-20 00:22:54', 1, 1),
    ('2024-05-20 01:15:32', 2, 2),
    ('2024-05-20 02:45:10', 3, 1),
    ('2024-05-20 00:22:54', 4, 1),
    ('2024-05-20 01:15:32', 5, 2),
    ('2024-05-20 02:45:10', 6, 1),
    ('2024-05-21 10:00:00', 7, 1),
    ('2024-05-21 11:30:45', 8, 2),
    ('2024-05-22 09:15:30', 9, 1),
    ('2024-05-22 12:45:00', 10, 1),
    ('2024-05-23 08:10:20', 11, 2),
    ('2024-05-23 14:30:45', 12, 1),
    ('2024-05-24 16:45:30', 13, 1),
    ('2024-05-25 09:15:50', 14, 2),
    ('2024-05-26 11:45:00', 15, 1),
    ('2024-05-27 15:30:25', 16, 1),
    ('2024-05-28 13:20:40', 17, 2),
    ('2024-05-29 17:45:10', 18, 1),
    ('2024-05-30 19:15:30', 19, 1),
    ('2024-05-31 20:30:45', 20, 2),
    ('2023-06-10 14:20:30', 21, 1),
    ('2023-07-15 16:40:50', 22, 2),
    ('2023-08-12 10:30:45', 23, 1),
    ('2023-09-20 11:45:30', 24, 1),
    ('2023-10-25 09:15:20', 25, 2);


-- Inserção na tabela Produto_Venda
INSERT INTO Produto_Venda (valor_Unitario, quantidade, desconto, item_Promocional, venda_id, etp_id, historico_Produto_id)
VALUES
    (150.00, 1, 0.00, 'SIM', 1, 1, 1),
    (200.00, 2, 10.00, 'NÃO', 2, 2, 2),
    (100.00, 1, 5.00, 'SIM', 3, 3, 3),
    (150.00, 1, 0.00, 'SIM', 4, 4, 4),
    (200.00, 2, 10.00, 'NÃO', 5, 5, 5),
    (100.00, 1, 5.00, 'SIM', 6, 6, 6),
    (250.00, 1, 0.00, 'NÃO', 7, 7, 7),
    (300.00, 3, 15.00, 'NÃO', 8, 8, 8),
    (175.00, 1, 5.00, 'SIM', 9, 9, 9),
    (225.00, 1, 0.00, 'NÃO', 10, 10, 10),
    (190.00, 3, 10.00, 'SIM', 11, 11, 11),
    (130.00, 1, 5.00, 'NÃO', 12, 12, 12),
    (210.00, 1, 0.00, 'SIM', 13, 13, 13),
    (250.00, 3, 15.00, 'NÃO', 14, 14, 14),
    (275.00, 1, 0.00, 'SIM', 15, 15, 15),
    (180.00, 1, 5.00, 'NÃO', 16, 16, 16),
    (220.00, 3, 10.00, 'SIM', 17, 17, 17),
    (240.00, 1, 0.00, 'NÃO', 18, 18, 18),
    (260.00, 1, 5.00, 'SIM', 19, 19, 19),
    (290.00, 3, 15.00, 'NÃO', 20, 20, 20),
    (220.00, 1, 0.00, 'SIM', 21, 21, 21),
    (180.00, 3, 10.00, 'NÃO', 22, 22, 22),
    (200.00, 1, 5.00, 'SIM', 23, 23, 23),
    (250.00, 1, 0.00, 'NÃO', 24, 24, 24),
    (275.00, 3, 15.00, 'SIM', 25, 25, 25),
    (220.00, 1, 0.00, 'NÃO', 26, 26, 26),
    (180.00, 3, 10.00, 'SIM', 27, 27, 27),
    (200.00, 1, 5.00, 'NÃO', 28, 28, 28),
    (250.00, 1, 0.00, 'SIM', 29, 29, 29),
    (275.00, 3, 15.00, 'NÃO', 30, 30, 30),
    (220.00, 1, 0.00, 'SIM', 31, 31, 31),
    (180.00, 3, 10.00, 'NÃO', 32, 32, 32),
    (200.00, 1, 5.00, 'SIM', 33, 33, 33),
    (250.00, 1, 0.00, 'NÃO', 34, 34, 34),
    (275.00, 3, 15.00, 'SIM', 35, 35, 35),
    (220.00, 1, 0.00, 'NÃO', 36, 36, 36),
    (180.00, 3, 10.00, 'SIM', 37, 37, 37),
    (200.00, 1, 5.00, 'NÃO', 38, 38, 38),
    (250.00, 1, 0.00, 'SIM', 39, 39, 39),
    (275.00, 3, 15.00, 'NÃO', 40, 40, 40);


INSERT INTO Historico_Produto (data_Hora, produto_venda_id, status_Historico_Produto_id)
VALUES
    ('2024-05-24 17:15:04', 1, 1),
    ('2024-05-24 17:30:00', 2, 2),
    ('2024-05-24 18:00:00', 3, 3),
    ('2024-05-24 18:30:00', 4, 1),
    ('2024-05-24 19:00:00', 5, 2),
    ('2024-05-24 19:30:00', 6, 3),
    ('2024-05-24 20:00:00', 7, 1),
    ('2024-05-24 20:30:00', 8, 2),
    ('2024-05-24 21:00:00', 9, 3);


 */

INSERT INTO Status_Historico_Produto (status)
VALUES
    ('VENDIDO'),
    ('DEVOLVIDO'),
    ('ABATIDO');

INSERT INTO STATUS_TRANSFERENCIA (status)
VALUES
    ('PENDENTE'),
    ('ACEITO'),
    ('NEGADO');
-- Inserção na tabela transferencia com a coluna liberador_id preenchida
INSERT INTO transferencia (data_Hora, quantidade_Solicitada, quantidade_Liberada, status_transferencia_id, coletor_id, liberador_id, etp_id)
VALUES
    ('2024-05-24 19:02:19', 10, 8, 1, 2, 1, 1),
    ('2024-05-24 19:05:00', 15, 15, 1, 2, 3, 2),
    ('2024-05-24 19:10:00', 20, 18, 2, 3, 4, 3),
    ('2024-05-24 19:15:00', 5, 5, 3, 4, 5, 4),
    ('2024-05-24 19:20:00', 8, 7, 1, 5, 6, 5),
    ('2024-05-24 19:25:00', 12, 12, 2, 6, 7, 6),
    ('2024-05-24 19:30:00', 10, 9, 3, 7, 8, 7),
    ('2024-05-24 19:35:00', 7, 7, 1, 8, 9, 8),
    ('2024-05-24 19:40:00', 14, 13, 2, 9, 10, 9),
    ('2024-05-24 19:45:00', 9, 9, 2, 8, 10, 10),
    ('2024-05-24 19:50:00', 6, 6, 2, 7, 9, 11),
    ('2024-05-24 19:55:00', 11, 10, 3, 6, 2, 12),
    ('2024-05-24 20:00:00', 13, 12, 1, 1, 8, 13),
    ('2024-05-24 20:05:00', 15, 14, 1, 3, 1, 14),
    ('2024-05-24 20:10:00', 20, 18, 1, 1, 4, 15),
    ('2024-05-24 20:15:00', 18, 17, 2, 6, 5, 16),
    ('2024-05-24 20:20:00', 22, 21, 1, 5, 2, 17),
    ('2024-05-24 20:25:00', 25, 24, 1, 4, 1, 18),
    ('2024-05-24 20:30:00', 30, 29, 2, 3, 1, 19),
    ('2024-05-24 20:35:00', 28, 27, 3, 2, 1, 20);


--- Dados para a configuração inicial das lojas
INSERT INTO Configuracao_Loja(loja_id, limite_Estoque_Alerta)
VALUES
    (1, 25),
    (2, 50),
    (3, 100);