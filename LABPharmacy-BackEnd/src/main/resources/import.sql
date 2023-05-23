INSERT INTO address_tb (cep, street, number, neighborhood, city, state, complement, latitude, longitude) VALUES ('01001000', 'Av. Paulista', '1000', 'Bela Vista', 'São Paulo', 'SP', 'Apto 1001', -23.5677, -46.6483);
INSERT INTO address_tb (cep, street, number, neighborhood, city, state, complement, latitude, longitude) VALUES ('01002000', 'Av. Brigadeiro Faria Lima', '1500', 'Itaim Bibi', 'São Paulo', 'SP', 'Apto 1501',-23.5677, -46.6483);
INSERT INTO address_tb (cep, street, number, neighborhood, city, state, complement, latitude, longitude) VALUES ('01003001', 'Rua José Paulino', '500', 'Vila Mariana', 'São Paulo', 'SP', 'Apto 500', -23.5677, -46.6483);
INSERT INTO address_tb (cep, street, number, neighborhood, city, state, complement, latitude, longitude) VALUES ('01003001', 'Rua José Paulino', '500', 'Vila Mariana', 'São Paulo', 'SP', 'Apto 500', -23.5677, -46.6483);


INSERT INTO user_tb (full_name, cpf, date, email, contact_number, type, password, id_address, is_active) VALUES ('João da Silva', '111.111.111-11', '01-01-1994', 'joao.silva@gmail.com', '(11) 1111-1111', 'ADMIN', '123', 1, true);
INSERT INTO user_tb (full_name, cpf, date, email, contact_number, type, password, id_address, is_active) VALUES ('Maria Souza', '222.222.222-22', '01-01-1994', 'maria.souza@gmail.com', '(11) 2222-2222', 'SELLER', '123', 2, true);
INSERT INTO user_tb (full_name, cpf, date, email, contact_number, type, password, id_address, is_active) VALUES ('Antonio Pereira', '333.333.333-33', '01-01-1994', 'antonio.pereira@gmail.com', '(11) 3333-3333', 'BUYER', '123', 3, true);

INSERT INTO product_tb (product_name, laboratory_name, product_image, dosage, product_description, price, product_type, stock_quantity, product_register_date, id_user, is_active) VALUES  ('Product A', 'Laboratory A', 'https://example.com/productB.jpg', '230mg', 'Description for Product A', 29.99, 'Type A', 5, '03-02-2023', 1, true);
INSERT INTO product_tb (product_name, laboratory_name, product_image, dosage, product_description, price, product_type, stock_quantity, product_register_date, id_user, is_active) VALUES  ('Product B', 'Laboratory B', 'https://example.com/productB.jpg', '210mg', 'Description for Product B', 629.99, 'Type B', 4300, '03-02-2023', 2, true);
INSERT INTO product_tb (product_name, laboratory_name, product_image, dosage, product_description, price, product_type, stock_quantity, product_register_date, id_user, is_active) VALUES  ('Product C', 'Laboratory C', 'https://example.com/productB.jpg', '220mg', 'Description for Product C', 529.99, 'Type C', 3300, '03-02-2023', 3, true);
INSERT INTO product_tb (product_name, laboratory_name, product_image, dosage, product_description, price, product_type, stock_quantity, product_register_date, id_user, is_active) VALUES  ('Product D', 'Laboratory D', 'https://example.com/productB.jpg', '240mg', 'Description for Product D', 429.99, 'Type D', 2300, '03-02-2023', 3, true);

INSERT INTO sale_tb ( id_seller,id_buyer, id_product, price, quantity,  sale_date, total_price, payment_method) VALUES (1,3, 1, 10.0,  2, '04-04-2023', 20.0, 'CREDIT_CARD');
INSERT INTO sale_tb ( id_seller,id_buyer, id_product, price, quantity,  sale_date, total_price, payment_method) VALUES (1,3, 2, 10.0,  2, '04-04-2023', 20.0, 'PIX');
INSERT INTO sale_tb ( id_seller,id_buyer, id_product, price, quantity, sale_date, total_price, payment_method) VALUES (1,3, 3, 10.0,  2, '04-04-2023', 20.0, 'DEBIT_CARD');
INSERT INTO sale_tb ( id_seller,id_buyer, id_product, price, quantity,  sale_date, total_price, payment_method) VALUES (1,3, 4, 10.0,  2, '04-04-2023', 20.0, 'TICKET');
INSERT INTO sale_tb ( id_seller,id_buyer, id_product, price, quantity,  sale_date, total_price, payment_method) VALUES (1,3, 1, 10.0,  2, '04-04-2023', 20.0, 'BANK_TRANSFER');




