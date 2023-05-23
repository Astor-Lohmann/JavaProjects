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

<br>

### Backend

- Java
- Spring
- PostgreSQL (para construção do banco de dados)

<br><br>

## Escopo do projeto

O LAb Pharmacy fui elaborado como o projeto final das Turmas Curso de desenvolvimento Full-Stack, oferecido pelo DevinHouse em parceria com a Clamed.

Trata-se de uma aplicação web, com o objetivo de facilitar o gerenciamento de informações de vendas de determinados produtos, com foco no mercado farmacêutico, com as funcionalidades de cadastrar, pesquisar, editar e deletar informações. A aplicação possui sistema de login com dois tipos de Usuários distintos: Administrador e vendedor, cada um com distintas permissões dentro das funcionalidades dos sistema.

O Projeto conta com três endpoints: Usuário, Produto, Venda.

**Usuário administrador**

Este Usuário possui acesso a todas as funcionalidades do sistema, podendo cadastrar outros usuários no sistema, bem tem acesso às funcionalidades de cadastro, pesquisar, editar e deletar as informações relacionadas a vendas e aos produtos. 

**Usuário vendedor**

O Usuário vendedor é responsável exclusivamente por administrar os dados de vendas de produtos, portanto possui um acesso mais restrito à aplicação. 

As funcionalidades relacionadas ao vendedor são: criação, edição, pesquisa e deleção  e pesquisa de produtos; e criação, edição, pesquisa e deleção de informações de vendas. P


<br>

### Backend

- **1. EndPoint usuário**

![image](https://user-images.githubusercontent.com/101530871/233210560-a589feb6-51af-4392-9623-a1e7d0fdf41a.png)

- **2. EndPoint Produto**

![image](https://user-images.githubusercontent.com/101530871/233210451-0ebc44fd-6190-41a4-9b27-cd82b1221c17.png)
<br>

- **3. EndPoint Vendas**

![image](https://user-images.githubusercontent.com/101530871/233210505-a11db1ba-6f2d-4b96-86b2-8d47373dd93c.png)


<br>

- **4. Login**

    Todos os usuários (administradores ou vendedores) devem se logar pelo mesmo endpoint. Mediante submição do email e a senha correta. 

<br><br>


### Frontend

Abaixo estão as telas necessárias para a aplicação. O usuário deve fornecer as informações a ser solicitadas em cada fluxo.

<br>

- **1. Tela de Login do Administrador**

    Um usuário ouvinte tem que fornecer o email, e uma senha para logar no sistema.

<br>

- **2. Tela Sidebar Administrador**

   É o ponto inicial do administrador com o site, apresentando as rotas nas quais o administrador possui acesso dentro do sistema.
   
   A tela exibe as seguintes opções de rotas: Registro de Usuários,  Registro de Produtos, Registro de Vendas.

<br>

- **3. Tela de cadastro de usuários**

    Aqui o administrador pode cadastrar novos usuários no sistema, assim como filtrar, editar e deletar usuários existentes. 

<br>

- **4. Tela de Registro de Produtos**

    Nesta tela o usuário pode cadastrar novos produtos, realizar a consulta dos produtos no sistema, além de mostrar ao usuário todos os produtos cadastrados em uma tabela.

<br>

- **5. Tela de Vendas**

    Nesta tela há um fomulário para o usuário cadastrar os produtos vendidos, também mostra uma tabela com todos os produtos vendidos.
    

<br><br>

## Como rodar o projeto back-end
### Pré-requisitos

* PostgreSQL Instalado
* Java 17
* Inteli J (ou sua IDE preferida)

### Setup do projeto

* Crie um banco de dados PostgreSQL com o nome lab_pharmacy

* faça o clone do projeto, abra seu terminal na pasta em que deseja instalar o clone do projeto e digite o seguinte comando: 
```
git clone https://github.com/DEVin-Clamed/M3P2-LABPharmacy-BackEnd-Squad1
```

* abra o arquivo application properties e altere o usuario e a senha para suas credenciais locais

![image](https://user-images.githubusercontent.com/101530871/233208918-76b51763-4b30-4c16-a2c3-1cafb1bd3ac4.png)

* rode a PhamarcyApplication (shift + f10 no inteliJ)

![image](https://user-images.githubusercontent.com/101530871/233208995-d5682b80-0072-48d7-a967-fd6373646a15.png)

* seu terminal deve ficar da seguinte maneira 
![image](https://user-images.githubusercontent.com/101530871/233208809-d9a8f0ed-6469-4ad7-a13d-3dc7c12e4bfc.png)

* para acessar a documentação swagger acesse o [link](http://localhost:8001/swagger-ui/index.html) (http://localhost:8001/swagger-ui/index.html), caso a porta 8001 tenha sido alterada na application properties é necessario mudar a porta do localhost

* ao acessar o swagger você deve encontrar a seguinte página
![image](https://user-images.githubusercontent.com/101530871/233210320-b4c5d649-7308-4fbd-8ba7-6069159e6eb7.png)


