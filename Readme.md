# Golden Raspberry Awards Api
Foi desenvolvida uma api para o prêmio de pior filme Golden Raspberry Awards, com o intuito de ler um arquivo CSV, 
inseri-lo na base de dados, e retornar ao client, quando solicitado, os produtores com maior e menor intervalo entre os prêmios.

A api pode ser acessada pelo link [API](http://165.227.3.54:8089/swagger-ui.html)

A Api foi desenvolvida utilizando a linguagem de programação JAVA. A base de dados utilizada, coforme o solicitado, foi 
feita utilizando o H2.

# Base de dados
Como a idéia é termos um banco de dados em memória, criado e manipulado apenas em tempo de execução, então, o banco de 
dados H2 foi escolhido.

# Documentação
O código esta todo documentando utilizando o padrão de documentação Java Doc, e a api possui a documentação 
do tipo Swagger, que posse ser [acessada em Swagger API](http://165.227.3.54:8089/swagger-ui.html)

# Docker
O serviço encontra-se também disponível no docker hub, pode ser acessado clicando [Aqui](https://hub.docker.com/repository/docker/wallacehenriquee/golden-raspberry-awards-api),
e também, pode ser executado com o seguinte comando.

    docker run --network=host 
    -itd wallacehenriquee/golden-raspberry-awards-api:latest

# Dependências do projeto
- SpringBoot 2.1.6.RELEASE
- Lombok
- Spring Jpa
- Springfox
- H2 Database
- Spring Web
- JavaPow


## Leitura de CSV
A leitura do arquivo CSV foi feita utilizando uma biblioteca desenvolvida por mim, chamada JavaPow, que tem o intuito
de facilitar a leitura e escrita de arquivos xls, xlsx, csv, entre outros. Para utiliza-la, basta importar a biblioteca, 
e utiliza-la onde necessário por meio de annotations, semelhante ao uso do Hibernate.
A biblioteca encontra-se em meu github, e pode ser acessada pelo link [JavaPow](https://github.com/wallacehenriquesilva/Java-Pow).


## Possiveis melhorias
- Criar novos endpoints para listagem e gerenciamento dos demais dados.
- Adicionar autenticação utilizando JWT (Caso necessário).
- Geração de código nativo linux utilizando GraalVM.
- Integra-la ao outros serviços como um micro serviço utilizando as ferramentas do spring cloud.