INSERT INTO loja (cep, cnpj, complemento, nome, numero)
VALUES
    ('12345-678', '05.534.388/0001-24', 'Sala 101', 'Loja A', 101),
    ('54321-876', '11.222.333/0001-44', 'Apto 202', 'Loja B', 202),
    ('98765-432', '99.888.777/0001-55', 'Fundos', 'Loja C', 303),
    ('24680-135', '12.345.678/0001-90', 'Loja de Esquina', 'Loja D', 404),
    ('13579-024', '98.765.432/0001-21', 'Térreo', 'Loja E', 505),
    ('86420-246', '54.321.098/0001-76', 'Loja Principal', 'Loja F', 606),
    ('36912-369', '01.234.567/0001-30', 'Loja Central', 'Loja G', 707),
    ('25874-258', '23.456.789/0001-23', 'Loja 24 Horas', 'Loja H', 808),
    ('74125-987', '32.109.876/0001-98', 'Loja do Centro', 'Loja I', 909),
    ('95132-579', '78.901.234/0001-23', 'Mezanino', 'Loja J', 1010);

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

INSERT INTO modelo (codigo, nome, categoria_id, tipo_id)
VALUES
    ('AB123', 'Sapatênis Casual', 1, 1),
    ('CD456', 'Sandalia Conforto', 2, 2),
    ('EF789', 'Bota Adventure', 3, 3),
    ('GH012', 'Sapato Social', 4, 4),
    ('IJ345', 'Tênis Esportivo', 5, 5),
    ('KL678', 'Rasteirinha Feminina', 1, 2),
    ('MN901', 'Bota Montaria', 2, 3),
    ('OP234', 'Chinelo Slide', 3, 4),
    ('QR567', 'Scarpin Clássico', 4, 5),
    ('ST890', 'Tênis Skate', 5, 1),
    ('UV123', 'Bota Cano Curto', 1, 1),
    ('WX456', 'Tênis Casual', 2, 2),
    ('YZ789', 'Sandália Anabela', 3, 3),
    ('AB012', 'Sapato Oxford', 4, 4),
    ('CD345', 'Rasteira Gladiadora', 5, 5),
    ('EF678', 'Bota Coturno', 1, 2),
    ('GH901', 'Chinelo Havaianas', 2, 3),
    ('IJ234', 'Scarpin Salto Alto', 3, 4),
    ('KL567', 'Tênis Corrida', 4, 5),
    ('MN890', 'Sandália Plataforma', 5, 1);

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

INSERT INTO ETP (produto_id, loja_id, tamanho_id, quantidade)
VALUES
    (1, 1, 1, 5), (2, 1, 2, 5), (3, 1, 3, 5), (4, 1, 4, 5), (5, 1, 5, 5),
    (1, 2, 1, 5), (2, 2, 2, 5), (3, 2, 3, 5), (4, 2, 4, 5), (5, 2, 5, 5),
    (1, 3, 1, 5), (2, 3, 2, 5), (3, 3, 3, 5), (4, 3, 4, 5), (5, 3, 5, 5),
    (1, 4, 1, 5), (2, 4, 2, 5), (3, 4, 3, 5), (4, 4, 4, 5), (5, 4, 5, 5),
    (1, 5, 1, 5), (2, 5, 2, 5), (3, 5, 3, 5), (4, 5, 4, 5), (5, 5, 5, 5);

INSERT INTO ETP (produto_id, loja_id, tamanho_id, quantidade)
VALUES
    (6, 1, 1, 7), (7, 1, 2, 8), (8, 1, 3, 9), (9, 1, 4, 10), (10, 1, 5, 11),
    (6, 2, 1, 7), (7, 2, 2, 8), (8, 2, 3, 9), (9, 2, 4, 10), (10, 2, 5, 11),
    (6, 3, 1, 7), (7, 3, 2, 8), (8, 3, 3, 9), (9, 3, 4, 10), (10, 3, 5, 11),
    (6, 4, 1, 7), (7, 4, 2, 8), (8, 4, 3, 9), (9, 4, 4, 10), (10, 4, 5, 11),
    (6, 5, 1, 7), (7, 5, 2, 8), (8, 5, 3, 9), (9, 5, 4, 10), (10, 5, 5, 11);

INSERT INTO Tipo_venda (id, tipo, desconto)
VALUES
    (1, 'Varejo', 0),
    (2, 'Atacado', 20);

INSERT INTO Tipo_Pagamento(metodo)
VALUES
    ('Crédito'),
    ('Débito'),
    ('Dinheiro');



-- Inserção na tabela StatusVenda
INSERT INTO Status_Venda (status)
VALUES
    ('PENDENTE'),
    ('FINALIZADA'),
    ('CANCELADA');


-- Inserção na tabela Venda
INSERT INTO Venda (data_Hora, desconto, valor_Total, status_Venda_id, tipo_Venda_id, usuario_id)
VALUES
    ('2024-05-20 00:22:54', 0.00, 150.00, 1, 1, 1),
    ('2024-05-20 01:15:32', 10.00, 200.00, 2, 2, 1),
    ('2024-05-20 02:45:10', 5.00, 100.00, 1, 2, 1),
    ('2024-05-20 00:22:54', 0.00, 150.00, 1, 1, 1),
    ('2024-05-20 01:15:32', 10.00, 200.00, 2, 2, 1),
    ('2024-05-20 02:45:10', 5.00, 100.00, 1, 1, 1),
    ('2024-05-21 10:00:00', 0.00, 250.00, 1, 1, 1),
    ('2024-05-21 11:30:45', 15.00, 300.00, 2, 2, 1),
    ('2024-05-22 09:15:30', 5.00, 175.00, 1, 2, 1);


INSERT INTO Produto_Venda (valor_Unitario, quantidade, desconto, item_Promocional, venda_id, etp_id)
VALUES
    (100.00, 2, 0.00, 0, 1, 1),
    (120.00, 1, 10.00, 1, 2, 2),
    (120.00, 3, 0.00, 0, 2, 2),
    (125.00, 4, 0.00, 0, 2, 2),
    (180.00, 2, 0.00, 1, 5, 5),
    (130.00, 3, 0.00, 0, 6, 6),
    (170.00, 2, 0.00, 0, 7, 7),
    (120.00, 1, 0.00, 1, 8, 8),
    (110.00, 3, 0.00, 1, 9, 9);



INSERT INTO Status_Historico_Produto (status)
VALUES
    ('VENDIDO'),
    ('DEVOLVIDO'),
    ('ABATIDO');



INSERT INTO Historico_Produto (data_Hora, produto_Venda_id, status_Historico_Produto_id)
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


INSERT INTO transferencia (data_Hora, quantidade_Solicitada, quantidade_Liberada, status, coletor_id, liberador_id, etp_id)
VALUES
    ('2024-05-24 19:02:19', 10, 8, false, 1, 2, 1),
    ('2024-05-24 19:05:00', 15, 15, true, 2, 3, 2),
    ('2024-05-24 19:10:00', 20, 18, false, 3, 4, 3),
    ('2024-05-24 19:15:00', 5, 5, true, 4, 5, 4),
    ('2024-05-24 19:20:00', 8, 7, false, 5, 6, 5),
    ('2024-05-24 19:25:00', 12, 12, true, 6, 7, 6),
    ('2024-05-24 19:30:00', 10, 9, false, 7, 8, 7),
    ('2024-05-24 19:35:00', 7, 7, true, 8, 9, 8),
    ('2024-05-24 19:40:00', 14, 13, false, 9, 10, 9),
    ('2024-05-24 19:45:00', 9, 9, true, 8, 10, 10),
    ('2024-05-24 19:50:00', 6, 6, true, 7, 9, 11),
    ('2024-05-24 19:55:00', 11, 10, false, 6, 2, 12),
    ('2024-05-24 20:00:00', 13, 12, false, 1, 8, 13),
    ('2024-05-24 20:05:00', 15, 14, true, 3, 1, 14),
    ('2024-05-24 20:10:00', 20, 18, true, 1, 4, 15),
    ('2024-05-24 20:15:00', 18, 17, false, 6, 5, 16),
    ('2024-05-24 20:20:00', 22, 21, true, 5, 2, 17),
    ('2024-05-24 20:25:00', 25, 24, false, 4, 1, 18),
    ('2024-05-24 20:30:00', 30, 29, true, 3, 1, 19),
    ('2024-05-24 20:35:00', 28, 27, false, 2, 1, 20);