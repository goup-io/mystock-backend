INSERT INTO loja (numero, cep, cnpj, complemento, nome) VALUES
                                                            (101, '01000-000', '12345678000190', 'Loja A', 'Pérola Calçados'),
                                                            (102, '02000-000', '23456789000101', 'Sala 202', 'Calçados Bonitos'),
                                                            (103, '03000-000', '34567890000112', '', 'Shopping Calçados'),
                                                            (104, '04000-000', '45678901000123', 'Shopping Center', 'Loja dos Tênis');

INSERT INTO configuracao_loja (limite_estoque_alerta, loja_id)
VALUES
    (20, 1),  -- Configuração para a loja 1
    (25, 2),  -- Configuração para a loja 2
    (50, 3),  -- Configuração para a loja 3
    (10, 4);  -- Configuração para a loja 4

INSERT INTO cargo (descricao, nome)
VALUES
    ('Vendedor', 'Vendedor'),
    ('Gerente', 'Gerente'),
    ('Admin', 'Admin');

INSERT INTO usuario ( nome, cargo_id, email, telefone, loja_id, codigo_venda)
VALUES
    ('Joao', 3, 'inaciofigueiredo13@gmail.com', '123456789', 1, 1000),
    ('Pedro', 2, 'pedro@gmail.com', '(11) 94091-234', 2, 1001),
    ('Afonso', 2, 'afonso@hotmail.com', '(22) 98765-4321', 3, 1002),
    ('Ana', 1, 'ana@yahoo.com', '(33) 12345-6789', 1, 1003),
    ('Lucas', 2, 'lucas@outlook.com', '(44) 55555-5555', 2, 1004),
    ('Carla', 2, 'carla@gmail.com', '(55) 1234-5678', 2, 1005),
    ('Mariana', 1, 'mariana@hotmail.com', '(66) 98765-4321', 1, 1006),
    ('Gabriel', 3, 'gabriel@yahoo.com', '(77) 12345-6789', 3, 1007),
    ('Juliana', 2, 'juliana@gmail.com', '(88) 55555-5555', 2, 1008),
    ('Mateus', 1, 'mateus@outlook.com', '(99) 1234-5678', 1, 1009),
    ('Rafaela', 1, 'rafaela@hotmail.com', '(00) 98765-4321', 1, 1010),
    ('Bruno', 1, 'bruno@yahoo.com', '(11) 12345-6789', 1, 1011),
    ('Fernanda', 1, 'fernanda@gmail.com', '(22) 55555-5555', 1, 1012),
    ('Rodrigo', 1, 'rodrigo@outlook.com', '(33) 1234-5678', 1, 1013),
    ('Amanda', 2, 'amanda@hotmail.com', '(44) 98765-4321', 2, 1014);

INSERT INTO acesso_loja (descricao, tipo)
VALUES
    ('Caixa', 0),
    ('Área de Vendas', 1);

INSERT INTO loja_login (senha, username, acesso_loja_id, loja_id)
VALUES
    ('$2a$10$HV6jRjCZGmm.lKw3zYcEo.PkQihGcEmo3v4WJWJUpnBs4h/T5bmZK', 'vendasLoja', 2, 1),
    ('$2a$10$HV6jRjCZGmm.lKw3zYcEo.PkQihGcEmo3v4WJWJUpnBs4h/T5bmZK', 'caixaLoja', 1, 1);

INSERT INTO login (senha, username, usuario_id)
VALUES
    ('$2a$10$HV6jRjCZGmm.lKw3zYcEo.PkQihGcEmo3v4WJWJUpnBs4h/T5bmZK', 'teste', 1),
    ('$2a$10$HV6jRjCZGmm.lKw3zYcEo.PkQihGcEmo3v4WJWJUpnBs4h/T5bmZK', 'gerente', 3);

INSERT INTO tamanho (numero) VALUES
                                 (33),
                                 (34),
                                 (35),
                                 (36),
                                 (37),
                                 (38),
                                 (39),
                                 (40),
                                 (41),
                                 (42),
                                 (43);

INSERT INTO categoria (nome) VALUES
                                 ('Esportivo'),
                                 ('Casual'),
                                 ('Social'),
                                 ('Festa'),
                                 ('Bota'),
                                 ('Tênis'),
                                 ('Infantil'),
                                 ('Unissex'),
                                 ('Alto'),
                                 ('Baixo'),
                                 ('Clássico'),
                                 ('Moderno'),
                                 ('Confortável'),
                                 ('Elegante'),
                                 ('Caminhada'),
                                 ('Passeio'),
                                 ('Lazer'),
                                 ('Trabalho'),
                                 ('Chinelo'),
                                 ('Sandália'),
                                 ('Sapato'),
                                 ('Sapatilha'),
                                 ('Scarpin'),
                                 ('Rasteirinha'),
                                 ('Ankle boot'),
                                 ('Sneaker'),
                                 ('Espadrille'),
                                 ('Coturno'),
                                 ('Mule');

INSERT INTO tipo (nome) VALUES
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
                            ('Salto baixo'),
                            ('Bota de Cano Alto'),
                            ('Bota de Cano Baixo'),
                            ('Tênis de Corrida'),
                            ('Tênis Casual'),
                            ('Tênis Esportivo'),
                            ('Bota de Trabalho'),
                            ('Bota de Neve'),
                            ('Bota Militar'),
                            ('Sandália Rasteira'),
                            ('Sandália de Festa'),
                            ('Tênis Slip On'),
                            ('Tênis de Skate'),
                            ('Chinelo de Dedo'),
                            ('Chinelo Slide'),
                            ('Bota de Salto'),
                            ('Bota de Coturno'),
                            ('Sapatilha de Ballet'),
                            ('Sapato Social'),
                            ('Sapato de Couro'),
                            ('Tênis de Lona'),
                            ('Tênis de Verão'),
                            ('Tênis de Inverno'),
                            ('Sapato Casual'),
                            ('Sapato Confortável'),
                            ('Sandália de Plataforma'),
                            ('Tênis de CrossFit'),
                            ('Bota de Combate'),
                            ('Sandália de Praia'),
                            ('Chinelo Comfort'),
                            ('Bota de Lã'),
                            ('Sandália de Salto'),
                            ('Tênis de Performance'),
                            ('Sapato de Condução'),
                            ('Chinelo de Praia'),
                            ('Sapato de Tecido'),
                            ('Bota de Goleiro'),
                            ('Tênis de Jogo'),
                            ('Botinha'),
                            ('Bota de Salto Alto'),
                            ('Sandália Fechada'),
                            ('Sapatinho Infantil'),
                            ('Tênis de Bebê'),
                            ('Bota Fashion'),
                            ('Chinelo de Conforto'),
                            ('Sapatilha de Festa'),
                            ('Bota de Motociclista'),
                            ('Tênis para Caminhada'),
                            ('Bota de Equitação'),
                            ('Sandália de Bebê'),
                            ('Sapato de Festa Infantil'),
                            ('Tênis para Atividades Físicas'),
                            ('Bota de Gelo'),
                            ('Bota de Inverno'),
                            ('Chinelo de Couro'),
                            ('Sandália Confortável'),
                            ('Sapatilha de Bailarina'),
                            ('Sapato de Noiva'),
                            ('Tênis de Hiking'),
                            ('Bota de Escalada'),
                            ('Mocassim de Couro'),
                            ('Alpargata de Lona'),
                            ('Sapatilha de Baile'),
                            ('Sandália de Plataforma Alta'),
                            ('Tênis para Corrida de Rua'),
                            ('Bota de Caça'),
                            ('Chinelo de Praia Confortável'),
                            ('Tênis de Treino'),
                            ('Sandália de Conforto'),
                            ('Sapatilha de Verão'),
                            ('Scarpin de Festa'),
                            ('Oxford de Couro'),
                            ('Alpargata Confortável'),
                            ('Bota de Roupas Táticas'),
                            ('Bota de Trabalho em Altura');

INSERT INTO modelo (categoria_id, tipo_id, nome) VALUES
                                                     (1, 2, 'Tênis Nike Air Max 270'),               -- Esportivo
                                                     (1, 2, 'Tênis Nike Air Zoom Pegasus'),
                                                     (1, 1, 'Tênis de Corrida Asics Gel Nimbus'),
                                                     (1, 1, 'Tênis de Caminhada New Balance 990'),
                                                     (2, 3, 'Tênis Casual Converse Chuck Taylor'),   -- Casual
                                                     (2, 3, 'Tênis Casual Vans Old Skool'),
                                                     (2, 4, 'Tênis de Lona Superga'),
                                                     (2, 4, 'Tênis Slip-On Keds'),
                                                     (3, 5, 'Sapato Social de Couro'),               -- Social
                                                     (3, 5, 'Sapato Oxford Elegante'),
                                                     (3, 5, 'Sapato de Tecido Estampado'),
                                                     (3, 5, 'Sapato Casual de Camurça'),
                                                     (4, 6, 'Sandália de Festa Arezzo'),             -- Festa
                                                     (4, 6, 'Sandália de Salto Alto Schutz'),
                                                     (4, 6, 'Sandália de Festa Vizzano'),
                                                     (4, 6, 'Sandália Elegante Santa Lolla'),
                                                     (5, 1, 'Bota de Couro Timberland'),             -- Bota
                                                     (5, 1, 'Bota Coturno Masculina'),
                                                     (5, 1, 'Bota de Salto Alto Ankle Boot'),
                                                     (5, 1, 'Bota de Montanha North Face'),
                                                     (6, 2, 'Tênis de Corrida Saucony'),             -- Tênis
                                                     (6, 2, 'Tênis de Basquete Nike LeBron'),
                                                     (6, 2, 'Tênis de Caminhada Merrell'),
                                                     (6, 2, 'Tênis de Performance Hoka One One'),
                                                     (7, 3, 'Sandália Infantil Grendene'),           -- Infantil
                                                     (7, 3, 'Tênis Infantil Adidas'),
                                                     (7, 3, 'Bota Infantil de Couro'),
                                                     (7, 3, 'Chinelo Infantil Havaianas'),
                                                     (8, 4, 'Chinelo de Couro Unissex'),             -- Unissex
                                                     (8, 4, 'Sandália Unissex Grendene'),
                                                     (8, 4, 'Tênis Unissex Vans'),
                                                     (8, 4, 'Bota Unissex Timberland'),
                                                     (9, 5, 'Sapato Alto Arezzo'),                    -- Alto
                                                     (9, 5, 'Sapato de Salto Alto'),
                                                     (9, 5, 'Sapato Elegante Vizzano'),
                                                     (9, 5, 'Sapato Feminino de Festa'),
                                                     (10, 1, 'Tênis Baixo Converse'),                 -- Baixo
                                                     (10, 1, 'Tênis Baixo Vans'),
                                                     (10, 1, 'Tênis Baixo Nike'),
                                                     (10, 1, 'Tênis Baixo Adidas'),
                                                     (11, 2, 'Sapato Clássico Masculino'),            -- Clássico
                                                     (11, 2, 'Sapato Clássico Feminino'),
                                                     (11, 2, 'Tênis Clássico Superga'),
                                                     (11, 2, 'Bota Clássica de Couro'),
                                                     (12, 3, 'Tênis Moderno Nike React'),             -- Moderno
                                                     (12, 3, 'Tênis Moderno Adidas NMD'),
                                                     (12, 3, 'Tênis Moderno Puma RS-X'),
                                                     (12, 3, 'Bota Moderna de Couro'),
                                                     (13, 4, 'Sapato Confortável Moleca'),            -- Confortável
                                                     (13, 4, 'Tênis Confortável New Balance'),
                                                     (13, 4, 'Chinelo Confortável Havaianas'),
                                                     (13, 4, 'Bota Confortável Timberland'),
                                                     (14, 5, 'Sapato Elegante de Festa'),              -- Elegante
                                                     (14, 5, 'Tênis Elegante Puma'),
                                                     (14, 5, 'Sandália Elegante Arezzo'),
                                                     (14, 5, 'Botinha Elegante Moleca'),
                                                     (15, 1, 'Tênis de Caminhada Confortável'),       -- Caminhada
                                                     (15, 1, 'Tênis de Caminhada Nike'),
                                                     (15, 1, 'Bota para Caminhada Columbia'),
                                                     (15, 1, 'Sandália de Caminhada Grendene'),
                                                     (16, 2, 'Sandália de Passeio Havaianas'),        -- Passeio
                                                     (16, 2, 'Tênis de Passeio Adidas'),
                                                     (16, 2, 'Bota de Passeio Timberland'),
                                                     (16, 2, 'Chinelo de Passeio Ipanema'),
                                                     (17, 3, 'Tênis de Lazer Nike'),                   -- Lazer
                                                     (17, 3, 'Tênis de Lazer Adidas'),
                                                     (17, 3, 'Chinelo de Lazer Havaianas'),
                                                     (17, 3, 'Bota de Lazer Colcci'),
                                                     (18, 4, 'Bota de Trabalho Caterpillar'),          -- Trabalho
                                                     (18, 4, 'Bota de Segurança'),
                                                     (18, 4, 'Sapato de Trabalho Casual'),
                                                     (18, 4, 'Tênis de Trabalho Confortável'),
                                                     (19, 5, 'Chinelo Tradicional Havaianas'),         -- Chinelo
                                                     (19, 5, 'Chinelo de Borracha'),
                                                     (19, 5, 'Chinelo de Couro'),
                                                     (19, 5, 'Chinelo de Dedo Ipanema'),
                                                     (20, 6, 'Sandália Confortável'),                  -- Sandália
                                                     (20, 6, 'Sandália de Couro'),
                                                     (20, 6, 'Sandália de Festa'),
                                                     (20, 6, 'Sandália de Lona');

INSERT INTO cor (nome) VALUES
                           ('Preto'),
                           ('Branco'),
                           ('Cinza'),
                           ('Vermelho'),
                           ('Azul'),
                           ('Verde'),
                           ('Amarelo'),
                           ('Laranja'),
                           ('Rosa'),
                           ('Roxo'),
                           ('Bege'),
                           ('Marrom'),
                           ('Turquesa'),
                           ('Bordo'),
                           ('Dourado'),
                           ('Prata'),
                           ('Ciano'),
                           ('Magenta'),
                           ('Lavanda'),
                           ('Pêssego'),
                           ('Salmon'),
                           ('Oliva'),
                           ('Limão'),
                           ('Café'),
                           ('Caramelo'),
                           ('Creme'),
                           ('Índigo'),
                           ('Aquamarine'),
                           ('Petróleo'),
                           ('Café Claro'),
                           ('Cinza Claro'),
                           ('Cinza Escuro'),
                           ('Coral'),
                           ('Sépia'),
                           ('Vinho'),
                           ('Caqui'),
                           ('Fúcsia'),
                           ('Champanhe'),
                           ('Zinco'),
                           ('Ametista'),
                           ('Carmim'),
                           ('Ciano Claro'),
                           ('Verde Limão'),
                           ('Verde Menta'),
                           ('Verde Musgo'),
                           ('Verde Oliva'),
                           ('Azul Marinho'),
                           ('Azul Claro'),
                           ('Azul Escuro'),
                           ('Azul Turquesa'),
                           ('Vermelho Claro'),
                           ('Vermelho Escuro'),
                           ('Rosa Claro'),
                           ('Rosa Escuro'),
                           ('Laranja Claro'),
                           ('Laranja Escuro'),
                           ('Amarelo Claro'),
                           ('Amarelo Escuro'),
                           ('Preto Fosco'),
                           ('Preto Brilhante'),
                           ('Branco Fosco'),
                           ('Branco Brilhante'),
                           ('Roxo Claro'),
                           ('Roxo Escuro'),
                           ('Burgundy'),
                           ('Safira'),
                           ('Topázio'),
                           ('Gelo'),
                           ('Pele'),
                           ('Carbônico'),
                           ('Eletro'),
                           ('Nácar'),
                           ('Petróleo Claro'),
                           ('Café Escuro'),
                           ('Caramelo Claro'),
                           ('Cor de Rosa Chiclete'),
                           ('Lavanda Claro'),
                           ('Lavanda Escuro'),
                           ('Lima'),
                           ('Cinza Prata'),
                           ('Cinza Grafite'),
                           ('Ciano Profundo'),
                           ('Turquesa Claro'),
                           ('Turquesa Escuro'),
                           ('Preto Azulado'),
                           ('Branco Perolado'),
                           ('Cinnamon'),
                           ('Cinnamon Claro'),
                           ('Burnt Orange'),
                           ('Coral Claro'),
                           ('Coral Escuro'),
                           ('Dark Olive Green'),
                           ('Medium Sea Green'),
                           ('Light Sea Green'),
                           ('Saddle Brown'),
                           ('Rosado'),
                           ('Slate Blue'),
                           ('Slate Gray'),
                           ('Light Gray'),
                           ('Medium Violet Red'),
                           ('Medium Orchid'),
                           ('Medium Purple'),
                           ('Deep Pink'),
                           ('Light Coral'),
                           ('Gold'),
                           ('Honeydew'),
                           ('Ivory'),
                           ('Lavender Blush'),
                           ('Lemon Chiffon'),
                           ('Light Goldenrod Yellow'),
                           ('Light Pink'),
                           ('Light Salmon'),
                           ('Light Yellow');

INSERT INTO produto (cor_id, modelo_id, valor_custo, valor_revenda, nome) VALUES
                                                                              (1, 1, 150.00, 300.00, 'Tênis Nike Air Max 270 Preto'),
                                                                              (2, 2, 120.00, 240.00, 'Tênis Casual Converse Chuck Taylor Branco'),
                                                                              (3, 3, 200.00, 400.00, 'Sapato Social de Couro Marrom'),
                                                                              (4, 4, 90.00, 180.00, 'Sandália de Festa Arezzo Rosa'),
                                                                              (5, 5, 250.00, 500.00, 'Bota de Couro Timberland Verde'),
                                                                              (6, 6, 140.00, 280.00, 'Tênis de Corrida Asics Gel Nimbus Azul'),
                                                                              (7, 7, 110.00, 220.00, 'Chinelo de Couro Unissex Preto'),
                                                                              (8, 8, 130.00, 260.00, 'Sandália Unissex Grendene Laranja'),
                                                                              (9, 9, 175.00, 350.00, 'Bota Coturno Masculina Bordô'),
                                                                              (10, 10, 95.00, 190.00, 'Tênis Casual Vans Old Skool Cinza'),
                                                                              (11, 1, 80.00, 160.00, 'Sapato Casual de Camurça Beige'),  -- Modelo 1
                                                                              (12, 2, 300.00, 600.00, 'Sapato de Salto Alto Arezzo Preto Brilhante'),  -- Modelo 2
                                                                              (13, 3, 70.00, 140.00, 'Sapatilha de Lona Rosa Claro'),  -- Modelo 3
                                                                              (14, 4, 85.00, 170.00, 'Bota de Salto Alto Ankle Boot Caramelo'),  -- Modelo 4
                                                                              (15, 5, 100.00, 200.00, 'Tênis de Caminhada New Balance 990 Verde'),  -- Modelo 5
                                                                              (16, 6, 60.00, 120.00, 'Rasteirinha Elegante Santa Lolla Amarelo'),  -- Modelo 6
                                                                              (17, 7, 140.00, 280.00, 'Mocassim de Couro Preto'),  -- Modelo 7
                                                                              (18, 8, 200.00, 400.00, 'Alpargata Comfort Rosa'),  -- Modelo 8
                                                                              (19, 9, 150.00, 300.00, 'Chinelo de Dedo Ipanema Verde'),  -- Modelo 9
                                                                              (20, 10, 90.00, 180.00, 'Sneaker Casual Branco'),  -- Modelo 10
                                                                              (11, 3, 125.00, 250.00, 'Tênis Casual Converse Chuck Taylor Vermelho'),  -- Modelo 3
                                                                              (12, 4, 205.00, 410.00, 'Sapato Social de Couro Preto'),  -- Modelo 4
                                                                              (13, 5, 95.00, 190.00, 'Sandália de Festa Arezzo Azul'),  -- Modelo 5
                                                                              (14, 6, 255.00, 510.00, 'Bota de Couro Timberland Bege'),  -- Modelo 6
                                                                              (15, 7, 145.00, 290.00, 'Tênis de Corrida Asics Gel Nimbus Verde'),  -- Modelo 7
                                                                              (16, 8, 115.00, 230.00, 'Chinelo de Couro Unissex Azul'),  -- Modelo 8
                                                                              (17, 9, 135.00, 270.00, 'Sandália Unissex Grendene Vermelha'),  -- Modelo 9
                                                                              (18, 10, 180.00, 360.00, 'Bota Coturno Masculina Preto'),  -- Modelo 10
                                                                              (19, 1, 100.00, 200.00, 'Tênis Casual Vans Old Skool Preto'),  -- Modelo 1
                                                                              (20, 2, 165.00, 330.00, 'Tênis Nike Air Max 270 Verde'),  -- Modelo 2
                                                                              (21, 3, 135.00, 270.00, 'Tênis Casual Converse Chuck Taylor Laranja'),  -- Modelo 3
                                                                              (22, 4, 215.00, 430.00, 'Sapato Social de Couro Vermelho'),  -- Modelo 4
                                                                              (23, 5, 105.00, 210.00, 'Sandália de Festa Arezzo Preto'),  -- Modelo 5
                                                                              (24, 6, 265.00, 530.00, 'Bota de Couro Timberland Marrom Claro'),  -- Modelo 6
                                                                              (25, 7, 155.00, 310.00, 'Tênis de Corrida Asics Gel Nimbus Cinza'),  -- Modelo 7
                                                                              (26, 8, 125.00, 250.00, 'Chinelo de Couro Unissex Amarelo'),  -- Modelo 8
                                                                              (27, 9, 145.00, 290.00, 'Sandália Unissex Grendene Rosa'),  -- Modelo 9
                                                                              (28, 10, 190.00, 380.00, 'Bota Coturno Masculina Azul');  -- Modelo 10

INSERT INTO etp (loja_id, produto_id, quantidade, tamanho_id, codigo, item_promocional) VALUES
                                                                                            (1, 1, 10, 1, '001', 'NAO'),
                                                                                            (1, 2, 5, 2, '0021', 'SIM'),
                                                                                            (1, 3, 8, 3, '003', 'NAO'),
                                                                                            (1, 4, 15, 4, '004', 'SIM'),
                                                                                            (1, 5, 7, 5, '0000000000005', 'NAO'),
                                                                                            (1, 6, 12, 6, '006', 'SIM'),
                                                                                            (1, 7, 9, 7, '0078', 'NAO'),
                                                                                            (1, 8, 5, 8, '008', 'SIM'),
                                                                                            (1, 9, 3, 9, '0000000000009', 'NAO'),
                                                                                            (1, 10, 6, 10, '010', 'SIM'),
                                                                                            (1, 11, 20, 11, '0111', 'NAO'),
                                                                                            (2, 1, 11, 1, '012', 'SIM'),
                                                                                            (2, 2, 13, 2, '0131', 'NAO'),
                                                                                            (2, 3, 9, 3, '014', 'SIM'),
                                                                                            (2, 4, 14, 4, '015', 'NAO'),
                                                                                            (2, 5, 6, 5, '0000000000006', 'SIM'),
                                                                                            (2, 6, 10, 6, '016', 'NAO'),
                                                                                            (2, 7, 5, 7, '0171', 'SIM'),
                                                                                            (2, 8, 8, 8, '018', 'NAO'),
                                                                                            (2, 9, 5, 9, '019', 'SIM'),
                                                                                            (2, 10, 7, 10, '020', 'NAO'),
                                                                                            (2, 11, 12, 11, '021', 'SIM'),
                                                                                            (3, 1, 9, 1, '022', 'NAO'),
                                                                                            (3, 2, 15, 2, '0231', 'SIM'),
                                                                                            (3, 3, 8, 3, '024', 'NAO'),
                                                                                            (3, 4, 10, 4, '025', 'SIM'),
                                                                                            (3, 5, 12, 5, '00000000000112', 'NAO'),
                                                                                            (3, 6, 5, 6, '026', 'SIM'),
                                                                                            (3, 7, 7, 7, '027', 'NAO'),
                                                                                            (3, 8, 3, 8, '028', 'SIM'),
                                                                                            (3, 9, 11, 9, '029', 'NAO'),
                                                                                            (3, 10, 6, 10, '030', 'SIM'),
                                                                                            (3, 11, 10, 11, '031', 'NAO'),
                                                                                            (4, 1, 11, 1, '032', 'SIM'),
                                                                                            (4, 2, 9, 2, '0331', 'NAO'),
                                                                                            (4, 3, 5, 3, '034', 'SIM'),
                                                                                            (4, 4, 8, 4, '035', 'NAO'),
                                                                                            (4, 5, 15, 5, '00000000000512', 'SIM'),
                                                                                            (4, 6, 10, 6, '036', 'NAO'),
                                                                                            (4, 7, 7, 7, '037', 'SIM'),
                                                                                            (4, 8, 5, 8, '038', 'NAO'),
                                                                                            (4, 9, 12, 9, '039', 'SIM'),
                                                                                            (4, 10, 6, 10, '040', 'NAO'),
                                                                                            (4, 11, 10, 11, '041', 'SIM'),
                                                                                            (1, 12, 10, 1, '042', 'NAO'),
                                                                                            (1, 13, 5, 2, '0431', 'SIM'),
                                                                                            (1, 14, 8, 3, '044', 'NAO'),
                                                                                            (1, 15, 15, 4, '045', 'SIM'),
                                                                                            (1, 16, 7, 5, '00000000000016', 'NAO'),
                                                                                            (1, 17, 12, 6, '046', 'SIM'),
                                                                                            (1, 18, 9, 7, '0478', 'NAO'),
                                                                                            (1, 19, 5, 8, '048', 'SIM'),
                                                                                            (1, 20, 3, 9, '049', 'NAO'),
                                                                                            (1, 21, 6, 10, '050', 'SIM'),
                                                                                            (1, 22, 20, 11, '0511', 'NAO'),
                                                                                            (2, 12, 11, 1, '052', 'SIM'),
                                                                                            (2, 13, 13, 2, '0531', 'NAO'),
                                                                                            (2, 14, 9, 3, '054', 'SIM'),
                                                                                            (2, 15, 14, 4, '055', 'NAO'),
                                                                                            (2, 16, 6, 5, '00000000000116', 'SIM'),
                                                                                            (2, 17, 10, 6, '057', 'NAO'),
                                                                                            (2, 18, 5, 7, '0581', 'SIM'),
                                                                                            (2, 19, 8, 8, '059', 'NAO'),
                                                                                            (2, 20, 5, 9, '060', 'SIM'),
                                                                                            (2, 21, 7, 10, '061', 'NAO'),
                                                                                            (2, 22, 12, 11, '0621', 'SIM'),
                                                                                            (3, 12, 9, 1, '063', 'NAO'),
                                                                                            (3, 13, 15, 2, '0641', 'SIM'),
                                                                                            (3, 14, 8, 3, '065', 'NAO'),
                                                                                            (3, 15, 10, 4, '066', 'SIM'),
                                                                                            (3, 16, 12, 5, '0000000001234', 'NAO'),
                                                                                            (3, 17, 5, 6, '067', 'SIM'),
                                                                                            (3, 18, 7, 7, '068', 'NAO'),
                                                                                            (3, 19, 3, 8, '069', 'SIM'),
                                                                                            (3, 20, 11, 9, '070', 'NAO'),
                                                                                            (3, 21, 6, 10, '071', 'SIM'),
                                                                                            (3, 22, 10, 11, '072', 'NAO'),
                                                                                            (4, 12, 11, 1, '073', 'SIM'),
                                                                                            (4, 13, 9, 2, '0741', 'NAO'),
                                                                                            (4, 14, 5, 3, '075', 'SIM'),
                                                                                            (4, 15, 8, 4, '076', 'NAO'),
                                                                                            (4, 16, 15, 5, '00000000000015', 'SIM'),
                                                                                            (4, 17, 10, 6, '078', 'NAO'),
                                                                                            (4, 18, 7, 7, '079', 'SIM'),
                                                                                            (4, 19, 5, 8, '080', 'NAO'),
                                                                                            (4, 20, 12, 9, '081', 'SIM'),
                                                                                            (4, 21, 6, 10, '082', 'NAO'),
                                                                                            (4, 22, 10, 11, '0831', 'SIM'),
                                                                                            (1, 23, 10, 1, '084', 'NAO'),
                                                                                            (1, 24, 5, 2, '0851', 'SIM'),
                                                                                            (1, 25, 8, 3, '086', 'NAO'),
                                                                                            (1, 26, 15, 4, '087', 'SIM'),
                                                                                            (1, 27, 7, 5, '00000000000027', 'NAO'),
                                                                                            (1, 28, 12, 6, '088', 'SIM'),
                                                                                            (1, 29, 9, 7, '089', 'NAO'),
                                                                                            (1, 30, 5, 8, '090', 'SIM'),
                                                                                            (1, 31, 3, 9, '091', 'NAO'),
                                                                                            (1, 32, 6, 10, '092', 'SIM'),
                                                                                            (1, 33, 20, 11, '0931', 'NAO');

INSERT INTO alertas_estoque (etp_id, data_hora, descricao, titulo)
VALUES
    (1, '2024-10-23 10:30:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (1, '2024-10-23 11:00:00', 'Estoque do produto "Tênis Nike Air" de tamanho 40 está em 5!', 'Alerta de Estoque Crítico'),
    (2, '2024-10-23 12:15:00', 'Estoque do produto "Sandália Feminina" de tamanho 36 está em 2!', 'Alerta de Reposição Necessária'),
    (2, '2024-10-23 13:00:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (3, '2024-10-23 14:00:00', 'Estoque do produto "Bota Masculina" de tamanho 42 está em 3!', 'Alerta de Estoque Crítico'),
    (3, '2024-10-23 15:30:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (4, '2024-10-23 16:45:00', 'Estoque do produto "Sapatilha" de tamanho 37 está em 4!', 'Alerta de Reposição Necessária'),
    (5, '2024-10-23 17:00:00', 'Estoque do produto "Tênis Adidas" de tamanho 39 está em 1!', 'Alerta de Estoque Crítico'),
    (5, '2024-10-23 18:15:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (6, '2024-10-23 19:30:00', 'Estoque do produto "Botina" de tamanho 44 está em 2!', 'Alerta de Reposição Necessária'),
    (7, '2024-10-23 20:00:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (8, '2024-10-23 21:15:00', 'Estoque do produto "Tênis Puma" de tamanho 41 está em 6!', 'Alerta de Estoque Crítico'),
    (9, '2024-10-23 22:30:00', 'Estoque do produto "Sandália Rasteira" de tamanho 35 está em 3!', 'Alerta de Reposição Necessária'),
    (10, '2024-10-23 23:45:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (1, '2024-10-24 08:30:00', 'Estoque do produto "Tênis Converse" de tamanho 38 está em 0!', 'Alerta de Estoque Crítico'),
    (2, '2024-10-24 09:15:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (3, '2024-10-24 10:00:00', 'Estoque do produto "Bota Feminina" de tamanho 39 está em 5!', 'Alerta de Reposição Necessária'),
    (4, '2024-10-24 11:30:00', 'Estoque do produto "Tênis Asics" de tamanho 40 está em 1!', 'Alerta de Estoque Crítico'),
    (5, '2024-10-24 12:45:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (6, '2024-10-24 14:00:00', 'Estoque do produto "Sandália de Salto" de tamanho 37 está em 2!', 'Alerta de Reposição Necessária'),
    (7, '2024-10-24 15:30:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (8, '2024-10-24 16:45:00', 'Estoque do produto "Tênis Mizuno" de tamanho 43 está em 4!', 'Alerta de Estoque Crítico'),
    (9, '2024-10-24 17:15:00', 'Estoque do produto "Sapatilha Bico Fino" de tamanho 36 está em 3!', 'Alerta de Reposição Necessária'),
    (10, '2024-10-24 18:30:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (1, '2024-10-25 09:00:00', 'Estoque do produto "Tênis New Balance" de tamanho 42 está em 2!', 'Alerta de Estoque Crítico'),
    (2, '2024-10-25 10:15:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (3, '2024-10-25 11:30:00', 'Estoque do produto "Botas de Inverno" de tamanho 41 está em 3!', 'Alerta de Reposição Necessária'),
    (4, '2024-10-25 12:45:00', 'Estoque do produto "Sandália Anabela" de tamanho 38 está em 0!', 'Alerta de Estoque Crítico'),
    (5, '2024-10-25 14:00:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (6, '2024-10-25 15:30:00', 'Estoque do produto "Tênis Olympikus" de tamanho 40 está em 1!', 'Alerta de Reposição Necessária'),
    (7, '2024-10-25 16:45:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (8, '2024-10-25 18:00:00', 'Estoque do produto "Bota Cano Alto" de tamanho 39 está em 5!', 'Alerta de Estoque Crítico'),
    (9, '2024-10-25 19:15:00', 'Estoque do produto "Tênis Reebok" de tamanho 44 está em 2!', 'Alerta de Reposição Necessária'),
    (10, '2024-10-25 20:30:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (1, '2024-10-26 09:00:00', 'Estoque do produto "Sandália de Couro" de tamanho 37 está em 3!', 'Alerta de Estoque Crítico'),
    (2, '2024-10-26 10:15:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (3, '2024-10-26 11:30:00', 'Estoque do produto "Sapatilha de Festa" de tamanho 36 está em 0!', 'Alerta de Reposição Necessária'),
    (4, '2024-10-26 12:45:00', 'Estoque do produto "Tênis de Corrida" de tamanho 41 está em 2!', 'Alerta de Estoque Crítico'),
    (5, '2024-10-26 14:00:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (6, '2024-10-26 15:30:00', 'Estoque do produto "Botas de Segurança" de tamanho 42 está em 4!', 'Alerta de Reposição Necessária'),
    (7, '2024-10-26 16:45:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (8, '2024-10-26 18:00:00', 'Estoque do produto "Tênis Casual" de tamanho 39 está em 1!', 'Alerta de Estoque Crítico'),
    (9, '2024-10-26 19:15:00', 'Estoque do produto "Sandália Plataforma" de tamanho 38 está em 5!', 'Alerta de Reposição Necessária'),
    (10, '2024-10-26 20:30:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (1, '2024-10-27 09:00:00', 'Estoque do produto "Tênis Salomon" de tamanho 40 está em 3!', 'Alerta de Estoque Crítico'),
    (2, '2024-10-27 10:15:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (3, '2024-10-27 11:30:00', 'Estoque do produto "Bota de Couro" de tamanho 41 está em 2!', 'Alerta de Reposição Necessária'),
    (4, '2024-10-27 12:45:00', 'Estoque do produto "Sandália de Verão" de tamanho 38 está em 4!', 'Alerta de Estoque Crítico'),
    (5, '2024-10-27 14:00:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (6, '2024-10-27 15:30:00', 'Estoque do produto "Tênis de Skate" de tamanho 39 está em 1!', 'Alerta de Reposição Necessária'),
    (7, '2024-10-27 16:45:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (8, '2024-10-27 18:00:00', 'Estoque do produto "Bota de Neve" de tamanho 42 está em 2!', 'Alerta de Estoque Crítico'),
    (9, '2024-10-27 19:15:00', 'Estoque do produto "Sapatilha de Baile" de tamanho 37 está em 3!', 'Alerta de Reposição Necessária'),
    (10, '2024-10-27 20:30:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (1, '2024-10-28 09:00:00', 'Estoque do produto "Tênis de Lona" de tamanho 38 está em 0!', 'Alerta de Estoque Crítico'),
    (2, '2024-10-28 10:15:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (3, '2024-10-28 11:30:00', 'Estoque do produto "Bota de Chuva" de tamanho 40 está em 3!', 'Alerta de Reposição Necessária'),
    (4, '2024-10-28 12:45:00', 'Estoque do produto "Sandália de Festa" de tamanho 39 está em 2!', 'Alerta de Estoque Crítico'),
    (5, '2024-10-28 14:00:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (6, '2024-10-28 15:30:00', 'Estoque do produto "Tênis de Moda" de tamanho 41 está em 5!', 'Alerta de Reposição Necessária'),
    (7, '2024-10-28 16:45:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (8, '2024-10-28 18:00:00', 'Estoque do produto "Botas de Festa" de tamanho 38 está em 0!', 'Alerta de Estoque Crítico'),
    (9, '2024-10-28 19:15:00', 'Estoque do produto "Tênis Fashion" de tamanho 39 está em 4!', 'Alerta de Reposição Necessária'),
    (10, '2024-10-28 20:30:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (1, '2024-10-29 09:00:00', 'Estoque do produto "Sapatilha de Noiva" de tamanho 37 está em 1!', 'Alerta de Estoque Crítico'),
    (2, '2024-10-29 10:15:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (3, '2024-10-29 11:30:00', 'Estoque do produto "Bota de Montanha" de tamanho 42 está em 2!', 'Alerta de Reposição Necessária'),
    (4, '2024-10-29 12:45:00', 'Estoque do produto "Tênis de Atividade" de tamanho 40 está em 3!', 'Alerta de Estoque Crítico'),
    (5, '2024-10-29 14:00:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (6, '2024-10-29 15:30:00', 'Estoque do produto "Sandália de Conforto" de tamanho 38 está em 4!', 'Alerta de Reposição Necessária'),
    (7, '2024-10-29 16:45:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (8, '2024-10-29 18:00:00', 'Estoque do produto "Tênis de Couro" de tamanho 41 está em 1!', 'Alerta de Estoque Crítico'),
    (9, '2024-10-29 19:15:00', 'Estoque do produto "Bota de Inverno" de tamanho 39 está em 0!', 'Alerta de Reposição Necessária'),
    (10, '2024-10-29 20:30:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (1, '2024-10-30 09:00:00', 'Estoque do produto "Tênis para Caminhada" de tamanho 42 está em 3!', 'Alerta de Estoque Crítico'),
    (2, '2024-10-30 10:15:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (3, '2024-10-30 11:30:00', 'Estoque do produto "Sapatilha de Balé" de tamanho 38 está em 5!', 'Alerta de Reposição Necessária'),
    (4, '2024-10-30 12:45:00', 'Estoque do produto "Bota de Salto" de tamanho 40 está em 1!', 'Alerta de Estoque Crítico'),
    (5, '2024-10-30 14:00:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (6, '2024-10-30 15:30:00', 'Estoque do produto "Tênis de Trabalho" de tamanho 39 está em 4!', 'Alerta de Reposição Necessária'),
    (7, '2024-10-30 16:45:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (8, '2024-10-30 18:00:00', 'Estoque do produto "Sandália de Verniz" de tamanho 38 está em 0!', 'Alerta de Estoque Crítico'),
    (9, '2024-10-30 19:15:00', 'Estoque do produto "Bota de Trabalho" de tamanho 42 está em 3!', 'Alerta de Reposição Necessária'),
    (10, '2024-10-30 20:30:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (1, '2024-10-31 09:00:00', 'Estoque do produto "Tênis Casual" de tamanho 39 está em 2!', 'Alerta de Estoque Crítico'),
    (2, '2024-10-31 10:15:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (3, '2024-10-31 11:30:00', 'Estoque do produto "Sandália de Festa" de tamanho 37 está em 1!', 'Alerta de Reposição Necessária'),
    (4, '2024-10-31 12:45:00', 'Estoque do produto "Bota de Couro" de tamanho 42 está em 2!', 'Alerta de Estoque Crítico'),
    (5, '2024-10-31 14:00:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (6, '2024-10-31 15:30:00', 'Estoque do produto "Tênis de Inverno" de tamanho 41 está em 0!', 'Alerta de Reposição Necessária'),
    (7, '2024-10-31 16:45:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (8, '2024-10-31 18:00:00', 'Estoque do produto "Sandália de Festa" de tamanho 39 está em 3!', 'Alerta de Estoque Crítico'),
    (9, '2024-10-31 19:15:00', 'Estoque do produto "Bota de Salto Alto" de tamanho 40 está em 4!', 'Alerta de Reposição Necessária'),
    (10, '2024-10-31 20:30:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (1, '2024-11-01 09:00:00', 'Estoque do produto "Tênis para Corrida" de tamanho 38 está em 2!', 'Alerta de Estoque Crítico'),
    (2, '2024-11-01 10:15:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (3, '2024-11-01 11:30:00', 'Estoque do produto "Sandália de Salto Baixo" de tamanho 37 está em 1!', 'Alerta de Reposição Necessária'),
    (4, '2024-11-01 12:45:00', 'Estoque do produto "Bota de Cano Curto" de tamanho 42 está em 3!', 'Alerta de Estoque Crítico'),
    (5, '2024-11-01 14:00:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (6, '2024-11-01 15:30:00', 'Estoque do produto "Tênis de Correr" de tamanho 40 está em 0!', 'Alerta de Reposição Necessária'),
    (7, '2024-11-01 16:45:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (8, '2024-11-01 18:00:00', 'Estoque do produto "Sandália de Conforto" de tamanho 39 está em 5!', 'Alerta de Estoque Crítico'),
    (9, '2024-11-01 19:15:00', 'Estoque do produto "Bota de Festa" de tamanho 41 está em 2!', 'Alerta de Reposição Necessária'),
    (10, '2024-11-01 20:30:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (1, '2024-11-02 09:00:00', 'Estoque do produto "Tênis Casual" de tamanho 42 está em 1!', 'Alerta de Estoque Crítico'),
    (2, '2024-11-02 10:15:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (3, '2024-11-02 11:30:00', 'Estoque do produto "Bota de Couro" de tamanho 40 está em 2!', 'Alerta de Reposição Necessária'),
    (4, '2024-11-02 12:45:00', 'Estoque do produto "Sandália de Festa" de tamanho 39 está em 3!', 'Alerta de Estoque Crítico'),
    (5, '2024-11-02 14:00:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (6, '2024-11-02 15:30:00', 'Estoque do produto "Tênis de Verão" de tamanho 41 está em 0!', 'Alerta de Reposição Necessária'),
    (7, '2024-11-02 16:45:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (8, '2024-11-02 18:00:00', 'Estoque do produto "Sandália de Conforto" de tamanho 42 está em 2!', 'Alerta de Estoque Crítico'),
    (9, '2024-11-02 19:15:00', 'Estoque do produto "Bota de Inverno" de tamanho 41 está em 3!', 'Alerta de Reposição Necessária'),
    (10, '2024-11-02 20:30:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (1, '2024-11-03 09:00:00', 'Estoque do produto "Tênis para Todos" de tamanho 39 está em 2!', 'Alerta de Estoque Crítico'),
    (2, '2024-11-03 10:15:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (3, '2024-11-03 11:30:00', 'Estoque do produto "Bota de Montanha" de tamanho 42 está em 1!', 'Alerta de Reposição Necessária'),
    (4, '2024-11-03 12:45:00', 'Estoque do produto "Sandália de Festa" de tamanho 40 está em 3!', 'Alerta de Estoque Crítico'),
    (5, '2024-11-03 14:00:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (6, '2024-11-03 15:30:00', 'Estoque do produto "Tênis Fashion" de tamanho 41 está em 2!', 'Alerta de Reposição Necessária'),
    (7, '2024-11-03 16:45:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (8, '2024-11-03 18:00:00', 'Estoque do produto "Bota de Salto" de tamanho 42 está em 4!', 'Alerta de Estoque Crítico'),
    (9, '2024-11-03 19:15:00', 'Estoque do produto "Sandália de Conforto" de tamanho 38 está em 3!', 'Alerta de Reposição Necessária'),
    (10, '2024-11-03 20:30:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (1, '2024-11-04 09:00:00', 'Estoque do produto "Tênis Confortável" de tamanho 40 está em 5!', 'Alerta de Estoque Crítico'),
    (2, '2024-11-04 10:15:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (3, '2024-11-04 11:30:00', 'Estoque do produto "Bota de Trabalho" de tamanho 42 está em 3!', 'Alerta de Reposição Necessária'),
    (4, '2024-11-04 12:45:00', 'Estoque do produto "Sandália para Festas" de tamanho 39 está em 4!', 'Alerta de Estoque Crítico'),
    (5, '2024-11-04 14:00:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (6, '2024-11-04 15:30:00', 'Estoque do produto "Tênis para o Dia a Dia" de tamanho 41 está em 2!', 'Alerta de Reposição Necessária'),
    (7, '2024-11-04 16:45:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (8, '2024-11-04 18:00:00', 'Estoque do produto "Bota de Estilo" de tamanho 39 está em 3!', 'Alerta de Estoque Crítico'),
    (9, '2024-11-04 19:15:00', 'Estoque do produto "Sandália Casual" de tamanho 38 está em 1!', 'Alerta de Reposição Necessária'),
    (10, '2024-11-04 20:30:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (1, '2024-11-05 09:00:00', 'Estoque do produto "Tênis de Caminhada" de tamanho 40 está em 0!', 'Alerta de Estoque Crítico'),
    (2, '2024-11-05 10:15:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (3, '2024-11-05 11:30:00', 'Estoque do produto "Bota de Salto Alto" de tamanho 42 está em 1!', 'Alerta de Reposição Necessária'),
    (4, '2024-11-05 12:45:00', 'Estoque do produto "Sandália de Baile" de tamanho 41 está em 3!', 'Alerta de Estoque Crítico'),
    (5, '2024-11-05 14:00:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (6, '2024-11-05 15:30:00', 'Estoque do produto "Tênis Casual" de tamanho 39 está em 4!', 'Alerta de Reposição Necessária'),
    (7, '2024-11-05 16:45:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (8, '2024-11-05 18:00:00', 'Estoque do produto "Bota de Trabalho" de tamanho 40 está em 2!', 'Alerta de Estoque Crítico'),
    (9, '2024-11-05 19:15:00', 'Estoque do produto "Sandália de Verão" de tamanho 41 está em 1!', 'Alerta de Reposição Necessária'),
    (10, '2024-11-05 20:30:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (1, '2024-11-06 09:00:00', 'Estoque do produto "Tênis de Inverno" de tamanho 39 está em 1!', 'Alerta de Estoque Crítico'),
    (2, '2024-11-06 10:15:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (3, '2024-11-06 11:30:00', 'Estoque do produto "Bota de Salto" de tamanho 42 está em 0!', 'Alerta de Reposição Necessária'),
    (4, '2024-11-06 12:45:00', 'Estoque do produto "Sandália de Conforto" de tamanho 39 está em 2!', 'Alerta de Estoque Crítico'),
    (5, '2024-11-06 14:00:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (6, '2024-11-06 15:30:00', 'Estoque do produto "Tênis Fashion" de tamanho 41 está em 3!', 'Alerta de Reposição Necessária'),
    (7, '2024-11-06 16:45:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (8, '2024-11-06 18:00:00', 'Estoque do produto "Bota de Estilo" de tamanho 40 está em 2!', 'Alerta de Estoque Crítico'),
    (9, '2024-11-06 19:15:00', 'Estoque do produto "Sandália de Festa" de tamanho 41 está em 3!', 'Alerta de Reposição Necessária'),
    (10, '2024-11-06 20:30:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (1, '2024-11-07 09:00:00', 'Estoque do produto "Tênis para Corrida" de tamanho 38 está em 2!', 'Alerta de Estoque Crítico'),
    (2, '2024-11-07 10:15:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (3, '2024-11-07 11:30:00', 'Estoque do produto "Bota de Montanha" de tamanho 41 está em 1!', 'Alerta de Reposição Necessária'),
    (4, '2024-11-07 12:45:00', 'Estoque do produto "Sandália de Festa" de tamanho 40 está em 3!', 'Alerta de Estoque Crítico'),
    (5, '2024-11-07 14:00:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (6, '2024-11-07 15:30:00', 'Estoque do produto "Tênis Casual" de tamanho 39 está em 0!', 'Alerta de Reposição Necessária'),
    (7, '2024-11-07 16:45:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (8, '2024-11-07 18:00:00', 'Estoque do produto "Bota de Estilo" de tamanho 38 está em 1!', 'Alerta de Estoque Crítico'),
    (9, '2024-11-07 19:15:00', 'Estoque do produto "Sandália de Festa" de tamanho 37 está em 2!', 'Alerta de Reposição Necessária'),
    (10, '2024-11-07 20:30:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (1, '2024-11-08 09:00:00', 'Estoque do produto "Tênis de Confortável" de tamanho 42 está em 1!', 'Alerta de Estoque Crítico'),
    (2, '2024-11-08 10:15:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (3, '2024-11-08 11:30:00', 'Estoque do produto "Bota de Salto Alto" de tamanho 41 está em 3!', 'Alerta de Reposição Necessária'),
    (4, '2024-11-08 12:45:00', 'Estoque do produto "Sandália de Conforto" de tamanho 39 está em 2!', 'Alerta de Estoque Crítico'),
    (5, '2024-11-08 14:00:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (6, '2024-11-08 15:30:00', 'Estoque do produto "Tênis Casual" de tamanho 38 está em 4!', 'Alerta de Reposição Necessária'),
    (7, '2024-11-08 16:45:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (8, '2024-11-08 18:00:00', 'Estoque do produto "Bota de Inverno" de tamanho 39 está em 3!', 'Alerta de Estoque Crítico'),
    (9, '2024-11-08 19:15:00', 'Estoque do produto "Sandália de Festa" de tamanho 40 está em 2!', 'Alerta de Reposição Necessária'),
    (10, '2024-11-08 20:30:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (1, '2024-11-09 09:00:00', 'Estoque do produto "Tênis para Corrida" de tamanho 42 está em 1!', 'Alerta de Estoque Crítico'),
    (2, '2024-11-09 10:15:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (3, '2024-11-09 11:30:00', 'Estoque do produto "Bota de Montanha" de tamanho 40 está em 0!', 'Alerta de Reposição Necessária'),
    (4, '2024-11-09 12:45:00', 'Estoque do produto "Sandália de Festa" de tamanho 39 está em 3!', 'Alerta de Estoque Crítico'),
    (5, '2024-11-09 14:00:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (6, '2024-11-09 15:30:00', 'Estoque do produto "Tênis Fashion" de tamanho 38 está em 5!', 'Alerta de Reposição Necessária'),
    (7, '2024-11-09 16:45:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (8, '2024-11-09 18:00:00', 'Estoque do produto "Bota de Estilo" de tamanho 41 está em 2!', 'Alerta de Estoque Crítico'),
    (9, '2024-11-09 19:15:00', 'Estoque do produto "Sandália de Conforto" de tamanho 39 está em 3!', 'Alerta de Reposição Necessária'),
    (10, '2024-11-09 20:30:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (1, '2024-11-10 09:00:00', 'Estoque do produto "Tênis de Confortável" de tamanho 40 está em 0!', 'Alerta de Estoque Crítico'),
    (2, '2024-11-10 10:15:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (3, '2024-11-10 11:30:00', 'Estoque do produto "Bota de Salto" de tamanho 39 está em 2!', 'Alerta de Reposição Necessária'),
    (4, '2024-11-10 12:45:00', 'Estoque do produto "Sandália de Festa" de tamanho 38 está em 4!', 'Alerta de Estoque Crítico'),
    (5, '2024-11-10 14:00:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (6, '2024-11-10 15:30:00', 'Estoque do produto "Tênis Casual" de tamanho 38 está em 1!', 'Alerta de Reposição Necessária'),
    (7, '2024-11-10 16:45:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (8, '2024-11-10 18:00:00', 'Estoque do produto "Bota de Estilo" de tamanho 42 está em 2!', 'Alerta de Estoque Crítico'),
    (9, '2024-11-10 19:15:00', 'Estoque do produto "Sandália de Verão" de tamanho 39 está em 3!', 'Alerta de Reposição Necessária'),
    (10, '2024-11-10 20:30:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (1, '2024-11-11 09:00:00', 'Estoque do produto "Tênis para Todos" de tamanho 40 está em 3!', 'Alerta de Estoque Crítico'),
    (2, '2024-11-11 10:15:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (3, '2024-11-11 11:30:00', 'Estoque do produto "Bota de Trabalho" de tamanho 38 está em 1!', 'Alerta de Reposição Necessária'),
    (4, '2024-11-11 12:45:00', 'Estoque do produto "Sandália de Festa" de tamanho 41 está em 2!', 'Alerta de Estoque Crítico'),
    (5, '2024-11-11 14:00:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (6, '2024-11-11 15:30:00', 'Estoque do produto "Tênis Fashion" de tamanho 41 está em 3!', 'Alerta de Reposição Necessária'),
    (7, '2024-11-11 16:45:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (8, '2024-11-11 18:00:00', 'Estoque do produto "Bota de Estilo" de tamanho 39 está em 1!', 'Alerta de Estoque Crítico'),
    (9, '2024-11-11 19:15:00', 'Estoque do produto "Sandália de Festa" de tamanho 40 está em 0!', 'Alerta de Reposição Necessária'),
    (10, '2024-11-11 20:30:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (1, '2024-11-12 09:00:00', 'Estoque do produto "Tênis Casual" de tamanho 42 está em 0!', 'Alerta de Estoque Crítico'),
    (2, '2024-11-12 10:15:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (3, '2024-11-12 11:30:00', 'Estoque do produto "Bota de Montanha" de tamanho 42 está em 1!', 'Alerta de Reposição Necessária'),
    (4, '2024-11-12 12:45:00', 'Estoque do produto "Sandália de Festa" de tamanho 39 está em 3!', 'Alerta de Estoque Crítico'),
    (5, '2024-11-12 14:00:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (6, '2024-11-12 15:30:00', 'Estoque do produto "Tênis Fashion" de tamanho 41 está em 1!', 'Alerta de Reposição Necessária'),
    (7, '2024-11-12 16:45:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (8, '2024-11-12 18:00:00', 'Estoque do produto "Bota de Salto" de tamanho 38 está em 2!', 'Alerta de Estoque Crítico'),
    (9, '2024-11-12 19:15:00', 'Estoque do produto "Sandália de Festa" de tamanho 41 está em 1!', 'Alerta de Reposição Necessária'),
    (10, '2024-11-12 20:30:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (1, '2024-11-13 09:00:00', 'Estoque do produto "Tênis para Corrida" de tamanho 39 está em 1!', 'Alerta de Estoque Crítico'),
    (2, '2024-11-13 10:15:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (3, '2024-11-13 11:30:00', 'Estoque do produto "Bota de Trabalho" de tamanho 42 está em 0!', 'Alerta de Reposição Necessária'),
    (4, '2024-11-13 12:45:00', 'Estoque do produto "Sandália de Festa" de tamanho 40 está em 2!', 'Alerta de Estoque Crítico'),
    (5, '2024-11-13 14:00:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (6, '2024-11-13 15:30:00', 'Estoque do produto "Tênis Fashion" de tamanho 41 está em 3!', 'Alerta de Reposição Necessária'),
    (7, '2024-11-13 16:45:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (8, '2024-11-13 18:00:00', 'Estoque do produto "Bota de Estilo" de tamanho 42 está em 2!', 'Alerta de Estoque Crítico'),
    (9, '2024-11-13 19:15:00', 'Estoque do produto "Sandália de Conforto" de tamanho 41 está em 1!', 'Alerta de Reposição Necessária'),
    (10, '2024-11-13 20:30:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (1, '2024-11-14 09:00:00', 'Estoque do produto "Tênis Casual" de tamanho 39 está em 2!', 'Alerta de Estoque Crítico'),
    (2, '2024-11-14 10:15:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (3, '2024-11-14 11:30:00', 'Estoque do produto "Bota de Salto" de tamanho 40 está em 1!', 'Alerta de Reposição Necessária'),
    (4, '2024-11-14 12:45:00', 'Estoque do produto "Sandália de Festa" de tamanho 39 está em 3!', 'Alerta de Estoque Crítico'),
    (5, '2024-11-14 14:00:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (6, '2024-11-14 15:30:00', 'Estoque do produto "Tênis Fashion" de tamanho 41 está em 1!', 'Alerta de Reposição Necessária'),
    (7, '2024-11-14 16:45:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo'),
    (8, '2024-11-14 18:00:00', 'Estoque do produto "Bota de Estilo" de tamanho 38 está em 3!', 'Alerta de Estoque Crítico'),
    (9, '2024-11-14 19:15:00', 'Estoque do produto "Sandália de Conforto" de tamanho 42 está em 2!', 'Alerta de Reposição Necessária'),
    (10, '2024-11-14 20:30:00', 'Alerta estoque com quantidade abaixo do ideal!', 'Alerta de Estoque Baixo');

INSERT INTO status_transferencia (status) VALUES
                                              ('PENDENTE'),
                                              ('ACEITO'),
                                              ('NEGADO');

INSERT INTO status_venda (status) VALUES
                                      ('PENDENTE'),
                                      ('FINALIZADA'),
                                      ('CANCELADA');

INSERT INTO tipo_venda (desconto, tipo) VALUES
                                            (0.0, 'VAREJO'),
                                            (0.05, 'ATACADO'),  -- 5% de desconto
                                            (0.10, 'ESPECIAL'); -- 10% de desconto

INSERT INTO status_historico_produto (status) VALUES
                                                  ('VENDIDO'),
                                                  ('DEVOLVIDO'),
                                                  ('ABATIDO');

INSERT INTO tipo_pagamento (metodo) VALUES
                                        ('CREDITO'),
                                        ('DEBITO'),
                                        ('PIX'),
                                        ('DINHEIRO');

INSERT INTO venda (desconto, status_venda_id, tipo_venda_id, usuario_id, valor_total, data_hora) VALUES
                                                                                                     (0.0, 2, 1, 1, 150.00, '2024-10-01 09:00:00'),  -- FINALIZADA
                                                                                                     (0.05, 1, 1, 2, 200.00, '2024-10-01 10:15:00'),  -- PENDENTE
                                                                                                     (0.0, 2, 2, 3, 300.00, '2024-10-01 11:30:00'),  -- FINALIZADA
                                                                                                     (0.10, 3, 1, 4, 120.00, '2024-10-01 12:45:00'),  -- CANCELADA
                                                                                                     (0.0, 2, 2, 5, 80.00, '2024-10-01 13:00:00'),   -- FINALIZADA
                                                                                                     (0.05, 1, 3, 6, 220.00, '2024-10-01 14:15:00'),  -- PENDENTE
                                                                                                     (0.15, 3, 2, 7, 180.00, '2024-10-01 15:30:00'),  -- FINALIZADA
                                                                                                     (0.0, 2, 1, 8, 350.00, '2024-10-01 16:00:00'),   -- FINALIZADA
                                                                                                     (0.20, 3, 3, 9, 400.00, '2024-10-01 17:00:00'),  -- CANCELADA
                                                                                                     (0.0, 2, 1, 10, 500.00, '2024-10-01 18:00:00'),  -- FINALIZADA
                                                                                                     (0.10, 1, 1, 11, 85.00, '2024-10-02 09:15:00'),   -- FINALIZADA
                                                                                                     (0.0, 2, 1, 12, 130.00, '2024-10-02 10:30:00'),  -- PENDENTE
                                                                                                     (0.05, 2, 3, 13, 90.00, '2024-10-02 11:45:00'),   -- FINALIZADA
                                                                                                     (0.0, 1, 2, 14, 186.50, '2024-10-02 13:00:00'),  -- FINALIZADA
                                                                                                     (0.15, 3, 3, 15, 250.00, '2024-10-02 14:15:00'),  -- CANCELADA
                                                                                                     (0.0, 2, 2, 1, 300.00, '2024-10-02 15:30:00'),   -- FINALIZADA
                                                                                                     (0.05, 1, 1, 2, 400.00, '2024-10-02 16:45:00'),  -- PENDENTE
                                                                                                     (0.10, 3, 1, 3, 200.00, '2024-10-02 18:00:00'),  -- FINALIZADA
                                                                                                     (0.0, 2, 2, 4, 220.00, '2024-10-03 09:00:00'),   -- FINALIZADA
                                                                                                     (0.20, 3, 3, 5, 160.00, '2024-10-03 10:15:00'),  -- CANCELADA
                                                                                                     (0.0, 1, 1, 6, 90.00, '2024-10-03 11:30:00'),    -- FINALIZADA
                                                                                                     (0.05, 2, 2, 7, 130.00, '2024-10-03 12:45:00'),  -- FINALIZADA
                                                                                                     (0.0, 1, 3, 8, 110.00, '2024-10-03 13:00:00'),   -- PENDENTE
                                                                                                     (0.15, 3, 1, 9, 75.00, '2024-10-03 14:15:00'),   -- FINALIZADA
                                                                                                     (0.0, 2, 1, 10, 140.00, '2024-10-03 15:30:00'),   -- FINALIZADA
                                                                                                     (0.10, 1, 2, 11, 200.00, '2024-10-03 16:00:00'),  -- FINALIZADA
                                                                                                     (0.0, 2, 3, 12, 190.00, '2024-10-03 17:00:00'),   -- CANCELADA
                                                                                                     (0.05, 1, 1, 13, 300.00, '2024-10-03 18:00:00'),  -- FINALIZADA
                                                                                                     (0.0, 3, 2, 14, 320.00, '2024-10-04 09:00:00'),   -- FINALIZADA
                                                                                                     (0.20, 1, 3, 15, 150.00, '2024-10-04 10:15:00'),  -- PENDENTE
                                                                                                     (0.0, 1, 1, 1, 270.00, '2024-10-04 11:30:00'),   -- FINALIZADA
                                                                                                     (0.05, 3, 2, 1, 200.00, '2024-10-04 12:45:00'),   -- FINALIZADA
                                                                                                     (0.0, 1, 3, 1, 220.00, '2024-10-04 13:00:00'),   -- FINALIZADA
                                                                                                     (0.15, 2, 1, 1, 190.00, '2024-10-04 14:15:00'),  -- CANCELADA
                                                                                                     (0.0, 2, 2, 1, 80.00, '2024-10-04 15:30:00'),    -- FINALIZADA
                                                                                                     (0.10, 1, 3, 1, 150.00, '2024-10-04 16:45:00'),  -- FINALIZADA
                                                                                                     (0.0, 2, 1, 1, 300.00, '2024-10-04 17:00:00'),   -- FINALIZADA
                                                                                                     (0.20, 3, 2, 10, 130.00, '2024-10-04 18:00:00'),  -- PENDENTE
                                                                                                     (0.0, 1, 1, 2, 170.00, '2024-10-05 09:00:00'),   -- FINALIZADA
                                                                                                     (0.05, 2, 3, 3, 140.00, '2024-10-05 10:15:00'),  -- FINALIZADA
                                                                                                     (0.0, 1, 1, 4, 90.00, '2024-10-05 11:30:00'),    -- CANCELADA
                                                                                                     (0.10, 2, 2, 10, 150.00, '2024-10-05 12:45:00'),  -- FINALIZADA
                                                                                                     (0.0, 1, 3, 1, 130.00, '2024-10-05 13:00:00'),   -- FINALIZADA
                                                                                                     (0.15, 2, 1, 2, 80.00, '2024-10-05 14:15:00'),   -- FINALIZADA
                                                                                                     (0.0, 2, 3, 8, 220.00, '2024-10-05 15:30:00'),   -- CANCELADA
                                                                                                     (0.20, 1, 2, 1, 100.00, '2024-10-05 16:00:00'),  -- FINALIZADA
                                                                                                     (0.0, 3, 1, 2, 260.00, '2024-10-05 17:00:00'),   -- FINALIZADA
                                                                                                     (0.10, 2, 1, 3, 110.00, '2024-10-05 18:00:00'),  -- FINALIZADA
                                                                                                     (0.0, 1, 2, 6, 220.00, '2024-10-06 09:00:00'),   -- FINALIZADA
                                                                                                     (0.15, 3, 3, 1, 190.00, '2024-10-06 10:15:00');   -- FINALIZADA

-- Tabela produto_venda associada às vendas
INSERT INTO produto_venda (desconto, etp_id, item_promocional, quantidade, valor_unitario, venda_id) VALUES
                                                                                                         -- Produtos da Venda 1
                                                                                                         (0.0, 1, 0, 2, 75.00, 1),  -- Produto 1 da Venda 1
                                                                                                         (0.10, 2, 1, 1, 200.00, 1), -- Produto 2 da Venda 1

                                                                                                         -- Produtos da Venda 2
                                                                                                         (0.05, 3, 0, 5, 40.00, 2),  -- Produto 1 da Venda 2

                                                                                                         -- Produtos da Venda 3
                                                                                                         (0.0, 4, 1, 3, 60.00, 3),   -- Produto 1 da Venda 3

                                                                                                         -- Produtos da Venda 4
                                                                                                         (0.0, 1, 0, 1, 150.00, 4),  -- Produto 1 da Venda 4

                                                                                                         -- Produtos da Venda 5
                                                                                                         (0.15, 2, 1, 2, 80.00, 5),   -- Produto 1 da Venda 5

                                                                                                         -- Produtos da Venda 6
                                                                                                         (0.0, 3, 0, 1, 500.00, 6),  -- Produto 1 da Venda 6

                                                                                                         -- Produtos da Venda 7
                                                                                                         (0.05, 4, 1, 4, 30.00, 7),   -- Produto 1 da Venda 7

                                                                                                         -- Produtos da Venda 8
                                                                                                         (0.0, 5, 0, 2, 100.00, 8),   -- Produto 1 da Venda 8

                                                                                                         -- Produtos da Venda 9
                                                                                                         (0.20, 6, 1, 1, 300.00, 9),   -- Produto 1 da Venda 9

                                                                                                         -- Produtos da Venda 10
                                                                                                         (0.0, 1, 0, 5, 50.00, 10),   -- Produto 1 da Venda 10

                                                                                                         -- Produtos da Venda 11
                                                                                                         (0.10, 2, 1, 3, 20.00, 11),  -- Produto 1 da Venda 11

                                                                                                         -- Produtos da Venda 12
                                                                                                         (0.0, 3, 0, 1, 90.00, 12),   -- Produto 1 da Venda 12

                                                                                                         -- Produtos da Venda 13
                                                                                                         (0.0, 4, 1, 4, 70.00, 13),   -- Produto 1 da Venda 13

                                                                                                         -- Produtos da Venda 14
                                                                                                         (0.15, 5, 0, 2, 200.00, 14),  -- Produto 1 da Venda 14

                                                                                                         -- Produtos da Venda 15
                                                                                                         (0.0, 6, 1, 2, 80.00, 15),   -- Produto 1 da Venda 15

                                                                                                         -- Produtos da Venda 16
                                                                                                         (0.10, 1, 0, 3, 150.00, 16),  -- Produto 1 da Venda 16

                                                                                                         -- Produtos da Venda 17
                                                                                                         (0.0, 2, 1, 1, 60.00, 17),    -- Produto 1 da Venda 17

                                                                                                         -- Produtos da Venda 18
                                                                                                         (0.20, 3, 0, 2, 30.00, 18),   -- Produto 1 da Venda 18

                                                                                                         -- Produtos da Venda 19
                                                                                                         (0.0, 4, 1, 5, 120.00, 19),   -- Produto 1 da Venda 19

                                                                                                         -- Produtos da Venda 20
                                                                                                         (0.15, 5, 0, 1, 250.00, 20),  -- Produto 1 da Venda 20

                                                                                                         -- Produtos da Venda 21
                                                                                                         (0.0, 1, 0, 4, 55.00, 21),  -- Produto 1 da Venda 21

                                                                                                         -- Produtos da Venda 22
                                                                                                         (0.10, 2, 1, 1, 110.00, 22), -- Produto 1 da Venda 22

                                                                                                         -- Produtos da Venda 23
                                                                                                         (0.0, 3, 0, 3, 75.00, 23),   -- Produto 1 da Venda 23

                                                                                                         -- Produtos da Venda 24 (continuado)
                                                                                                         (0.20, 4, 1, 2, 90.00, 24),  -- Produto 1 da Venda 24

                                                                                                         -- Produtos da Venda 25
                                                                                                         (0.0, 5, 0, 1, 65.00, 25),   -- Produto 1 da Venda 25

                                                                                                         -- Produtos da Venda 26
                                                                                                         (0.15, 6, 1, 5, 45.00, 26),   -- Produto 1 da Venda 26

                                                                                                         -- Produtos da Venda 27
                                                                                                         (0.0, 1, 0, 2, 150.00, 27),  -- Produto 1 da Venda 27

                                                                                                         -- Produtos da Venda 28
                                                                                                         (0.0, 2, 1, 4, 30.00, 28),    -- Produto 1 da Venda 28

                                                                                                         -- Produtos da Venda 29
                                                                                                         (0.05, 3, 0, 3, 40.00, 29),   -- Produto 1 da Venda 29

                                                                                                         -- Produtos da Venda 30
                                                                                                         (0.10, 4, 1, 1, 200.00, 30),  -- Produto 1 da Venda 30

                                                                                                         -- Produtos da Venda 31
                                                                                                         (0.0, 5, 0, 3, 80.00, 31),    -- Produto 1 da Venda 31

                                                                                                         -- Produtos da Venda 32
                                                                                                         (0.10, 6, 1, 2, 60.00, 32),   -- Produto 1 da Venda 32

                                                                                                         -- Produtos da Venda 33
                                                                                                         (0.0, 1, 0, 5, 45.00, 33),    -- Produto 1 da Venda 33

                                                                                                         -- Produtos da Venda 34
                                                                                                         (0.15, 2, 1, 3, 100.00, 34),  -- Produto 1 da Venda 34

                                                                                                         -- Produtos da Venda 35
                                                                                                         (0.0, 3, 0, 4, 120.00, 35),   -- Produto 1 da Venda 35

                                                                                                         -- Produtos da Venda 36
                                                                                                         (0.10, 4, 1, 1, 200.00, 36),  -- Produto 1 da Venda 36

                                                                                                         -- Produtos da Venda 37
                                                                                                         (0.0, 5, 0, 2, 150.00, 37),   -- Produto 1 da Venda 37

                                                                                                         -- Produtos da Venda 38
                                                                                                         (0.15, 6, 1, 3, 70.00, 38),   -- Produto 1 da Venda 38

                                                                                                         -- Produtos da Venda 39
                                                                                                         (0.0, 1, 0, 4, 100.00, 39),   -- Produto 1 da Venda 39

                                                                                                         -- Produtos da Venda 40
                                                                                                         (0.10, 2, 1, 5, 90.00, 40);   -- Produto 1 da Venda 40

INSERT INTO historico_produto (produto_venda_id, status_historico_produto_id, data_hora) VALUES
                                                                                             (1, 1, '2024-10-01 10:00:00'),  -- VENDIDO
                                                                                             (2, 2, '2024-10-01 11:30:00'),  -- DEVOLVIDO
                                                                                             (3, 1, '2024-10-01 14:15:00'),  -- VENDIDO
                                                                                             (4, 3, '2024-10-01 09:45:00'),  -- ABATIDO
                                                                                             (5, 1, '2024-10-02 15:30:00'),  -- VENDIDO
                                                                                             (6, 2, '2024-10-02 16:00:00'),  -- DEVOLVIDO
                                                                                             (7, 3, '2024-10-02 17:00:00'),  -- ABATIDO
                                                                                             (8, 1, '2024-10-03 08:00:00'),  -- VENDIDO
                                                                                             (9, 2, '2024-10-03 19:00:00'),  -- DEVOLVIDO
                                                                                             (10, 1, '2024-10-03 20:00:00'),  -- VENDIDO
                                                                                             (11, 1, '2024-10-04 10:30:00'),  -- VENDIDO
                                                                                             (12, 3, '2024-10-04 11:00:00'),  -- ABATIDO
                                                                                             (13, 2, '2024-10-04 12:00:00'),  -- DEVOLVIDO
                                                                                             (14, 1, '2024-10-05 13:30:00'),  -- VENDIDO
                                                                                             (15, 3, '2024-10-05 14:00:00'),  -- ABATIDO
                                                                                             (16, 1, '2024-10-05 15:00:00'),  -- VENDIDO
                                                                                             (17, 2, '2024-10-06 16:30:00'),  -- DEVOLVIDO
                                                                                             (18, 1, '2024-10-06 17:30:00'),  -- VENDIDO
                                                                                             (19, 3, '2024-10-06 18:00:00'),  -- ABATIDO
                                                                                             (20, 1, '2024-10-07 09:00:00'),  -- VENDIDO
                                                                                             (21, 1, '2024-10-08 10:00:00'),  -- VENDIDO
                                                                                             (22, 2, '2024-10-08 11:30:00'),  -- DEVOLVIDO
                                                                                             (23, 3, '2024-10-08 14:15:00'),  -- ABATIDO
                                                                                             (24, 1, '2024-10-08 09:45:00'),  -- VENDIDO
                                                                                             (25, 1, '2024-10-09 15:30:00'),  -- VENDIDO
                                                                                             (26, 2, '2024-10-09 16:00:00'),  -- DEVOLVIDO
                                                                                             (27, 1, '2024-10-09 17:00:00'),  -- VENDIDO
                                                                                             (28, 3, '2024-10-10 08:00:00'),  -- ABATIDO
                                                                                             (29, 1, '2024-10-10 19:00:00'),  -- VENDIDO
                                                                                             (30, 1, '2024-10-10 20:00:00');  -- VENDIDO

-- Pagamentos para Vendas "FINALIZADAS"
INSERT INTO pagamento (qtd_parcelas, tipo_pagamento_id, valor, venda_id) VALUES
                                                                             (1, 1, 150.00, 1),    -- Venda 1: pagamento integral
                                                                             (2, 1, 300.00, 3),    -- Venda 3: pagamento integral
                                                                             (1, 1, 80.00, 5),     -- Venda 5: pagamento integral
                                                                             (1, 1, 180.00, 7),    -- Venda 7: pagamento integral
                                                                             (1, 2, 350.00, 8),    -- Venda 8: pagamento integral
                                                                             (1, 1, 500.00, 10),   -- Venda 10: pagamento integral
                                                                             (1, 2, 70.00, 11),    -- Venda 11: pagamento integral
                                                                             (1, 1, 90.00, 13),    -- Venda 13: pagamento integral
                                                                             (1, 2, 150.00, 14),   -- Venda 14: pagamento integral
                                                                             (1, 1, 300.00, 16),   -- Venda 16: pagamento integral
                                                                             (2, 1, 220.00, 19),   -- Venda 19: pagamento integral
                                                                             (1, 1, 220.00, 22),   -- Venda 22: pagamento integral
                                                                             (1, 2, 90.00, 26),    -- Venda 26: pagamento integral
                                                                             (1, 1, 140.00, 27),   -- Venda 27: pagamento integral
                                                                             (2, 4, 200.00, 30),   -- Venda 30: pagamento integral
                                                                             (1, 1, 170.00, 31),   -- Venda 31: pagamento integral
                                                                             (1, 2, 140.00, 33),   -- Venda 33: pagamento integral
                                                                             (1, 1, 150.00, 35),   -- Venda 35: pagamento integral
                                                                             (1, 1, 100.00, 37),   -- Venda 37: pagamento integral
                                                                             (2, 1, 200.00, 39),   -- Venda 39: pagamento integral
                                                                             (1, 1, 90.00, 41),    -- Venda 41: pagamento integral
                                                                             (1, 1, 80.00, 43),    -- Venda 43: pagamento integral
                                                                             (2, 1, 100.00, 45),   -- Venda 45: pagamento integral
                                                                             (1, 1, 130.00, 47);   -- Venda 47: pagamento integral

-- Pagamentos Parciais para Vendas "PENDENTE" ou "CANCELADA"
INSERT INTO pagamento (qtd_parcelas, tipo_pagamento_id, valor, venda_id) VALUES
                                                                             (2, 2, 100.00, 2),    -- Venda 2: parcial
                                                                             (1, 1, 50.00, 4),     -- Venda 4: parcial
                                                                             (1, 1, 60.00, 6),     -- Venda 6: parcial
                                                                             (1, 1, 150.00, 9),    -- Venda 9: parcial
                                                                             (1, 2, 60.00, 12),    -- Venda 12: parcial
                                                                             (2, 1, 200.00, 15),   -- Venda 15: parcial
                                                                             (1, 1, 160.00, 18),   -- Venda 18: parcial
                                                                             (1, 2, 130.00, 20),   -- Venda 20: parcial
                                                                             (1, 1, 100.00, 23),   -- Venda 23: parcial
                                                                             (1, 1, 160.00, 25),   -- Venda 25: parcial
                                                                             (1, 2, 100.00, 28),   -- Venda 28: parcial
                                                                             (1, 1, 200.00, 32);   -- Venda 32: parcial
