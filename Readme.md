# Softplan Challenge
Foi desenvolvida uma api para o prêmio de pior filme Golden Raspberry Awards, com o intuito de ler um arquivo CSV, 
inseri-lo na base de dados, e retornar ao client, quando solicitado, os produtores com maior e menor intervalo entre os prêmios.


A Api foi desenvolvida utilizando a linguagem de programação JAVA. A base de dados utilizada, coforme o solicitado, foi 
feita utilizando o H2.

# Base de dados
Como a idéia é termos um banco de dados em memória, criado e manipulado apenas em tempo de execução, então, o banco de 
dados H2 foi escolhido.

# Docker
Todos os micro serviços geram automaticamente ao serem compilados imagens docker, e estão hospedados no docker hub, bem 
como o front end e podem ser baixados pelos comandos abaixo.

### Front
[Link Docker Hub](https://hub.docker.com/r/wallacehenriquee/softplan-challenge-front)

    docker run --network=host 
    -itd wallacehenriquee/softplan-challenge-front:0.0.1-SNAPSHOT

### Service Discovery
[Link Docker Hub](https://hub.docker.com/repository/docker/wallacehenriquee/service-discovery)

    docker run --network=host 
    -itd wallacehenriquee/service-discovery:0.0.1-SNAPSHOT

### Gateway
[Link Docker Hub](https://hub.docker.com/repository/docker/wallacehenriquee/softplan-challenge-gateway)

    docker run --network=host 
    -itd wallacehenriquee/softplan-challenge-gateway:0.0.1-SNAPSHOT

### Data Service
[Link Docker Hub](https://hub.docker.com/repository/docker/wallacehenriquee/softplan-challenge-data-service)

    docker run --network=host 
    -itd wallacehenriquee/softplan-challenge-data-service:0.0.1-SNAPSHOT

# Execução

Para executar os containers, basta fazer o pull dos mesmos do docker hub, ou seguir os comandos acima
para que sejam baixados e executados automaticamente. Os micro serviços serão executados de maneira automática, e caso necessário subir 
mais instâncias do data service por exemplo, é só escalar o container que ele automaticamente entrará no projeto
e será chamado de acordo com as regras de load balance.

# Documentação

A documentação do código foi toda feita utilizando o Javadoc, todos os métodos, funções, classes, etc. Possuem comentários.
A documentação das APIs foi toda feita utilizando o Swagger, que pode ser acessado pelo [Link](http://165.227.3.54:2207/swagger-ui.html).

Mesmo se tratando de micro serviços, o swagger da aplicação esta centralizado, podemos navegar entre os micro serviços 
pela aba **Select a spec**, no canto superior direito da página de documentação swagger.


# Telas do sistema
## Tela de login
![Tela de login](/imagens/login.png)

Na imagem acima, podemos ver a tela de login onde o usuário pode escolher em 
se cadastrar, logar com uma conta já existente, ou então, logar utilizando uma rede social
utilizando o padrão OAuth2.

## Tela de cadastro de usuário
![Tela de cadastro de novo usuário](/imagens/cadastro.png)

Caso o usuário opte por se cadastrar, ele precisará preencher o formulário
com os dados necessários, sendo eles, username, email e senha.


## Dashboard
![Dashboard](/imagens/dashboard.png)

Logo após realizar o login, o usuário é encaminhado a dashboard, onde são listadas algumas informações
bem como o top 5 de usuários cadastrados. E a lista de usuários cadastrados dizendo se estão online ou não
no chat **O chat estará disponível na próxima versão**.

## Lista de pessoas
![Lista de pessoas](/imagens/lista_pessoas.png)

Tela de listagem de pessoas, onde é possível fazer operações como filtros
edição e excluir as pessoas já existentes.


## Cadastro de pessoa
![Cadastro de pessoa](/imagens/cadastro_nova_pessoa.png)

A imagem acima apresenta o formulário de cadastro de pessoas.

# Dependências do projeto
- SpringBoot 2.1.6.RELEASE
- Lombok
- Spring Jpa
- Springfox
- H2 Database
- Spring Web


## Possiveis melhorias
- Criar novos endpoints para listagem e gerenciamento dos demais dados.
- Adicionar autenticação utilizando JWT (Caso necessário).
- Geração de código nativo linux utilizando GraalVM.
- Integra-la ao outros serviços como um micro serviço utilizando as ferramentas do spring cloud.