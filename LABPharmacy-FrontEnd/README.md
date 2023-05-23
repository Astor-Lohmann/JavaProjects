# FullStack - Projeto Final: LAB Pharmacy


## Principais linguagens, tecnologias e ferramentas utilizadas

<br>

### Frontend

- HTML
- CSS
- Javascript
- React (principal biblioteca para construção do site)
- Styled-components
- Formulários com validação
- Testes unitários
- Responsividade e adaptação de aplicação web para front.

<br><br>

## Escopo do projeto

O LAb Pharmacy fui elaborado como o projeto final das Turmas Curso de desenvolvimento Full-Stack, oferecido pelo DevinHouse em parceria com a Clamed.

Trata-se de uma aplicação web, com o objetivo de facilitar o gerenciamento de informações de vendas de determinados produtos, com foco no mercado farmacêutico, com as funcionalidades de cadastrar, pesquisar, editar e deletar informações. 
Na aplicação FrontEnd, o usuário deve realizar a um login, os usuários cadastrados no sistema são podem ter dois tipos de perfis diferentes: Administrador e vendedor, cada um com distintas permissões dentro das funcionalidades dos sistema.

Ao todo, a aplicação Frontend possui 5 telas de exibição: Tela de Login do Administrador, Tela Sidebar Administrador, Tela de cadastro de usuários, Tela de Registro de Produtos, Tela de Vendas


**Usuário administrador**

Este Usuário possui acesso a todas as funcionalidades do sistema, podendo cadastrar outros usuários no sistema, bem tem acesso às funcionalidades de cadastro, pesquisar, editar e deletar as informações relacionadas a vendas e aos produtos. 

**Usuário vendedor**

O Usuário vendedor é responsável exclusivamente por administrar os dados de vendas de produtos, portanto possui um acesso mais restrito à aplicação. 

As funcionalidades relacionadas ao vendedor são: criação, edição, pesquisa e deleção de produtos; e criação, edição, pesquisa e deleção de informações de vendas.


<br>


### Frontend

Abaixo estão as telas necessárias para a aplicação. O usuário deve fornecer as informações a ser solicitadas em cada fluxo.

<br>

- **1. Tela de Login do Administrador**

    É a tela inicial da aplicação, onde há um formulário de validação com dois campos, requerendo um email, e uma senha para logar no sistema, além de um botão de login.
    
    O usuário deve fornecer os seus dados e realizar o login no sistema.
    Há ainda a opção de trocar as cores do formulário entre cores claras e escuras, através de um botão no topo da tela.
    
    ![login](https://user-images.githubusercontent.com/64928219/233791730-3b13c63e-754c-4965-a4b7-d4c85ce2f995.png)


<br>

- **2. Tela Sidebar Administrador**

   O usuário é redirecionado para esta tela imediatamente após realizar o login, sendo o primeiro ponto de interação do usuário com o sistema interno da aplicação.
   
   No canto direto da tela, há uma sidebar, onde são apresentadas as rotas  do sistema, exibindo as seguintes opções de rotas: Registro de Usuários,  Registro de Produtos, Registro de Vendas, no topo da sidebar há o logo do aplicativo, que ao clicado, redireciona o usuário a tela de home(Sidebar Administrador).

   No centro da tela, o usuário é cumprimentado com uma mensagem dinâmica, alternando entre {bom dia}, {boa tarde}, e {boa noite}, conforme a hora do dia, seguido do nome do usuário logado, além de uma frase motivacional escolhida aleatóriamente.
   
   ![inicial](https://user-images.githubusercontent.com/64928219/233791991-b967f969-275a-42c7-a33d-71117c838532.png)



<br>

- **3. Tela de cadastro de usuários**

    Aqui o usuário pode cadastrar novos usuários no sistema através de um formulário, há um total de 14 campos divididos em duas secões: Dados do usuário e dados do endereço.
    
    Na seção dados do usuário, há 7 campos requerendo as seguintes informações: Nome Completo, CPF, Data De Nascimento, Celular, E-mail, Escolha uma Senha para o Usuário, Tipo do Usuário.    
    Na seção dados do endereço, há 7 campos requerendo as seguintes informações: CEP, Estado, Cidade, Bairro, Logradouro, Número, Complemento.    
    O formulário conta com dois botões: um para a limpeza dos campos e outro para o cadastramento das informações solicitadas.

    Abaixo do formulário há uma listagem de usuários, contendo também uma barra de pesquisa, que irá filtrar a listagem de usuários segundo os caractéres digitados pelo usuário.
    
    Na listagem de usuários, cada usuário cadastrado é exibido em um card individual, mostrando as seguintes informações: ID do usuário, Nome Completo, CPF, Data de Nascimento, E-mail, Celular, Tipo de Usuário.
    Cada card de informações possui ainda um botão para a deleção do registro e outro para a edição de informações, que, ao clicado, ativa um modal, que traz todos dos campos do formulário, já completos com as informações do card que foi clicado, cabendo ao usuário alterar, o modal possui ainda um botão para a atualização de informações, e outro para limpeza de campos.


![usuarios](https://user-images.githubusercontent.com/64928219/233792014-ec5aaf68-f7db-4447-b298-d6d99915eafa.png)


<br>

- **4. Tela de Registro de Produtos**

    Aqui o usuário pode cadastrar novos produtos no sistema através de um formulário, há um total de 8 campos requerendo as seguintes informações: Nome do Medicamento, Laboratório, Dosagem, Digite a URL da imagem, Tipo do Medicamento, Preço Unitário, Quantidade, Descrição.
        
    O formulário conta com dois botões: um para a limpeza dos campos e outro para o cadastramento das informações solicitadas.

    Abaixo do formulário há uma listagem de produtos, contendo também uma barra de pesquisa, que irá filtrar a listagem de produtos segundo os caractéres digitados pelo usuário.
    
    Na listagem de produtos, cada produtos cadastrado é exibido em um card individual, mostrando as seguintes informações: ID do produto, Medicamento, Tipo, Preço Unitário, E-mail, Descrição, Quantidade.
    Cada card de informações possui ainda um botão para a deleção do registro e outro para a edição de informações, que, ao clicado, ativa um modal, que traz todos dos campos do formulário, já completos com as informações do card que foi clicado, cabendo ao usuário alterar, o modal possui ainda um botão para a atualização de informações, e outro para limpeza de campos.
    
    
![produtos](https://user-images.githubusercontent.com/64928219/233792022-fcffdbaf-475f-4264-ab4a-79d0c2d09bc2.png)



<br>

- **5. Tela de Vendas**

    Aqui o usuário pode cadastrar novas vendas no sistema através de um formulário, há um total de 6 campos requerendo as seguintes informações: Comprador, Vendedor, Produto, Preço Unitário, Quantidade, Preço Unitário, Forma de Pagamento, e mais um campo que exibe o valor total segundo informações do usuário.
        
    O formulário conta com dois botões: um para a limpeza dos campos e outro para o cadastramento das informações solicitadas.

    Abaixo do formulário há uma listagem de vendas, contendo também uma barra de pesquisa, que irá filtrar a listagem de produtos segundo os caractéres digitados pelo usuário.
    
    Na listagem de produtos, cada venda cadastrada é exibido em um card individual, mostrando as seguintes informações: ID da venda, Vendedor, Produto, Preço Unitário, Quantidade, Forma de Pagamento, Valor Total.
    Cada card de informações possui ainda um botão para a deleção do registro e outro para a edição de informações, que, ao clicado, ativa um modal, que traz todos dos campos do formulário, já completos com as informações do card que foi clicado, cabendo ao usuário alterar, o modal possui ainda um botão para a atualização de informações, e outro para limpeza de campos.
    
    
![vendas](https://user-images.githubusercontent.com/64928219/233792027-285bc725-622a-4ec4-b015-e10c0857ba18.png)


<br><br>

## Como rodar a aplicação

No terminal, clone o projeto:
```
git clone https://github.com/DEVin-Clamed/M3P2-LABPharmacy-FrontEnd-Squad1.git
```

Entre na pasta do projeto:
```
cd DEVin-Clamed/M3P2-LABPharmacy-FrontEnd-Squad1
```

Instale as dependências:
```
npm install
```

Execute a aplicação:
```
npm start 
```

<br>
