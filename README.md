# Empresa recrutadora: Rersouce It

### Para baixar o MySQL
https://dev.mysql.com/downloads/

### Para baixar o Redis: 
https://redis.io/download

### Caso use o sistema operacional Windows:
https://github.com/MicrosoftArchive/redis/releases

na pasta raiz do Redis executar o comando: redis-server redis.windows.conf


#### Mysql
    Persistencia da compras realizadas
	
#### Redis
	Armazenamento de estrutura de dados de chave-valor de código aberto e na memória.
	
	*Justificativa:* Evitam atrasos de tempo de busca e podem acessar dados com algoritmos mais simples que usam menos instruções de CPU
	
#### Server config
	Centralizar os arquivos de configuração dos microserviços
	
#### Eureka
    É um serviço desevolvolvido pela Netflix para **service discovery**.

    *Justificativa:* O uso do Zuul, para criar os cluster

#### Zuul
    É um proxy reverso também desevolvolvido pela Netflix que atua também como um **load balance**, trabalha junto com o Eureka para descoberta e agreção dos nós. Dando a possibilidad de criar um cluster
    com facilidade.
    
    *Justificativa:* O sistema terá um volume de 100k de request/seg, dessa forma o sistema terá que está em cluster com um loadbalance.


### Para executar os projetos:
mvn clean install

mvn spring-boot:run

Subir os projetos na seguinte ordem:

1 - spring-cloud-config-server

2 - netflix-eureka-naming-service

3 - netflix-zuul-api-gateway-server

4 - category-service

5 - expense-service

### Endpoints

Para inserir uma nova categoria

![01-inserir-categoria](img/01-inserir-categoria.PNG)

Para buscar categorias sugeridas pelo nome

![02-buscar-categorias](img/02-buscar-categorias.PNG)

Para inserir gasto informando a categoria

![03-inserir-gasto-com-categoria](img/03-inserir-gasto-com-categoria.PNG)

Para inserir gasto sem informar categoria e receber automaticamente conforme existir relação com outra já inserida anteriormente

![04-inserir-gasto-sem-categoria-categorizando-automaticamente](img/04-inserir-gasto-sem-categoria-categorizando-automaticamente.PNG)

Para inserir gasto sem informar categoria

![05-inserindo-gasto-sem-categoria](img/05-inserindo-gasto-sem-categoria.PNG)

Para atualizar gasto já cadastrado, informado uma categoria

![06-atualizando-gasto-inserindo-categoria](img/06-atualizando-gasto-inserindo-categoria.PNG)

Para buscar gasto por seu identificador

![07-buscar-gasto-por-codigo](img/07-buscar-gasto-por-codigo.PNG)

Para filtrar gastos de um usuário em determinada data

![08-filtrando-gasto-por-data](img/08-filtrando-gasto-por-data.PNG)

Para listar gastos de determinado usuário nos últimos 5 segundos

![09-listar-gastos-do-usuario-nos-ultimos-cinco-segundos](img/09-listar-gastos-do-usuario-nos-ultimos-cinco-segundos.PNG)
