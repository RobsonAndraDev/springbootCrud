## O que o projeto tem
Esse projeto é baseado no projeto [Spring Boot](http://projects.spring.io/spring-boot/) e usa esses pacotes:
- Maven
- Spring Core
- Spring Data (Hibernate & MySQL)
- Spring MVC (Tomcat)
- [Thymleaf](https://thymeleaf.org)

## Instalação
### Maven
O projeto é criado com Maven, então você só precisa importar para sua IDE e executar o build do projeto para resolver as dependencias.
### Docker Compose
Porem pra evitar qualquer falha por questões relacionadas a versão do banco, java ou qualquer outra dependencia, esse projeto foi dockerizado,
e tem o docker-compose como orquestrador.
Para utilizalo basta ter instalado em sua maquina o Docker ([link de instalação](https://docs.docker.com/install/#supported-platforms)) e o docker-compose([link de instalação](https://docs.docker.com/compose/install/)). Uma vez que esses 2 softwares estiverem instalados fica muito simples já que com 1 unico comando o docker-compose ira subir o banco de dados, executar o `restore` da base e por fim subir a aplicação.
**Comando para subir a aplicação usando docker-compose**:
```bash
docker-compose up --build
```

**Comando para parar a aplicação com docker-compose**:
```bash
docker-compose down
```

## Configuração do banco de dados
Caso esteja usando docker-compose toda essa etapa pode ser desconsidereda.
Caso contrario, será necessario, criar o banco de dados MySQL usando o script de criação `./database/script.sql` e atualizar o arquivo `./src/main/resources/application.properties`.  
A configuração padrão é:

```bash
#logging.level.org.h2.server: DEBUG
# Database
spring.datasource.driverClassName=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://172.17.0.1:3306/springbootdb?useSSL=false
spring.datasource.username=crud
spring.datasource.password=82X4I7IOcb5r

spring.jpa.hibernate.ddl-auto=create

spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
hibernate.show_sql=true
hibernate.format_sql=true
```

No caso de não estar usando docker o endereço do banco deve ser alterado de 
`spring.datasource.url=jdbc:mysql://172.17.0.1:3306/springbootdb?useSSL=false`
para `spring.datasource.url=jdbc:mysql://127.0.0.1:3306/springbootdb?useSSL=false`

## Modo de usar 
Execute o projeto e acesse no navegador [http://localhost:8080](http://localhost:8080)

Ou 
Execute por linha de comando:
```bash
mvn spring-boot:run
```
