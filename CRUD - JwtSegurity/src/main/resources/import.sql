-- Arquivo que contêm inserts para carga inicial de dados para a aplicação

-- Insert na tabela de roles
INSERT INTO public.role(nome_role) VALUES ('ROLE_ADMINISTRADOR');
INSERT INTO public.role(nome_role) VALUES ('ROLE_CADASTRADOR');
INSERT INTO public.role(nome_role) VALUES ('ROLE_COLABORADOR');

-- Insert na tabela de usuários
INSERT INTO public.usuario(login, nome, senha) VALUES ('fulano1', 'Fulano um', '$2a$10$pFrtqxDwOYw0rFq1VQtEx.pBgN6A8tUlVzfPnCs3Aior5NXmMes1m');
INSERT INTO public.usuario(login, nome, senha) VALUES ('fulano2', 'Fulano dois', '$2a$10$1/tzkPLEbpU/FQWKcp9C5u19nARxtc.3Zge8LPWkatj/ub922CAaa');
INSERT INTO public.usuario(login, nome, senha) VALUES ('fulano3', 'Fulano três', '$2a$10$1/tzkPLEbpU/FQWKcp9C5u19nARxtc.3Zge8LPWkatj/ub922CAaa');

-- Insert na tabela de medicamentos
INSERT INTO public.medicamento(nome, laboratorio, dosagem, descricao, descricaoop, preco, tipo) VALUES ('medicamento1', 'medicamento1', 'medicamento1', 'medicamento1', 'medicamento1', '10', 'medicamento1' );
INSERT INTO public.medicamento(nome, laboratorio, dosagem, descricao, descricaoop, preco, tipo) VALUES ('medicamento2', 'medicamento2', 'medicamento2', 'medicamento2', 'medicamento2', '20', 'medicamento2' );
INSERT INTO public.medicamento(nome, laboratorio, dosagem, descricao, descricaoop, preco, tipo) VALUES ('medicamento3', 'medicamento3', 'medicamento3', 'medicamento3', 'medicamento3', '30', 'medicamento3' );

-- Insert na tabela de farmacias
INSERT INTO public.farmacia(razaoSocial, cnpj, nome, email, telefone, celular, idEndereco) VALUES ('farmacia1', 'farmacia1', 'farmacia1', 'farmacia1', '111111111', '111111111', '1' );
INSERT INTO public.farmacia(razaoSocial, cnpj, nome, email, telefone, celular, idEndereco) VALUES ('farmacia2', 'farmacia2', 'farmacia2', 'farmacia2', '222222222', '222222222', '2' );
INSERT INTO public.farmacia(razaoSocial, cnpj, nome, email, telefone, celular, idEndereco) VALUES ('farmacia3', 'farmacia3', 'farmacia3', 'farmacia3', '333333333', '333333333', '3' );


-- Insert na tabela de endereços
INSERT INTO public.endereco(cep, logradouro, numero, bairro, cidade, estado, complemento, latitude, longitude) VALUES ('11111111', 'endereco1', '1', 'endereco1', 'endereco1', 'endereco1', 'endereco1', 'endereco1', 'endereco1');
INSERT INTO public.endereco(cep, logradouro, numero, bairro, cidade, estado, complemento, latitude, longitude) VALUES ('22222222', 'endereco2', '2', 'endereco2', 'endereco2', 'endereco2', 'endereco2', 'endereco2', 'endereco2');
INSERT INTO public.endereco(cep, logradouro, numero, bairro, cidade, estado, complemento, latitude, longitude) VALUES ('33333333', 'endereco3', '3', 'endereco3', 'endereco3', 'endereco3', 'endereco3', 'endereco3', 'endereco3');


-- Insert na tabela de usuarios_role (associa o(s) usuário(s) com suas permissões)
INSERT INTO public.usuarios_role(usuario_id, role_id) VALUES (1, 1);
INSERT INTO public.usuarios_role(usuario_id, role_id) VALUES (1, 2);
INSERT INTO public.usuarios_role(usuario_id, role_id) VALUES (2, 2);
INSERT INTO public.usuarios_role(usuario_id, role_id) VALUES (3, 3);
