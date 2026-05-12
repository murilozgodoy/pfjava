# PF Java - API de Usuários e Cursos

API REST desenvolvida em Spring Boot para gerenciamento de usuários e cursos.

## Observação importante para testes

Este projeto utiliza banco de dados **H2 em memória**.  
Por isso, sempre que a aplicação for reiniciada, os dados do banco são apagados.

Antes de testar as rotas protegidas (`POST`, `PUT` e `DELETE`), é necessário criar manualmente um usuário administrador no H2.

As rotas protegidas exigem o header:

```txt
X-USER-ID: 1
```

## Como acessar o H2 Console

Com a aplicação rodando, acesse:

```txt
http://localhost:8080/h2-console
```

Use as seguintes configurações:

```txt
JDBC URL: jdbc:h2:mem:pf_db
User Name: sa
Password:
```

O campo `Password` deve ficar vazio.

## Comando para criar o usuário ADMIN

No campo SQL do H2 Console, execute:

```sql
INSERT INTO users (nome, cpf, papel) VALUES ('Admin', '12345678900', 'ADMIN');
```

Depois, para conferir se o usuário foi criado corretamente, execute:

```sql
SELECT * FROM users;
```

O resultado esperado é um usuário com:

```txt
ID: 1
NOME: Admin
CPF: 12345678900
PAPEL: ADMIN
```

## Como testar as rotas protegidas

Após criar o ADMIN, utilize o seguinte header nas requisições `POST`, `PUT` e `DELETE`:

```txt
X-USER-ID: 1
```

As rotas `GET` são públicas e não precisam desse header.

## Collection Postman

A collection Postman acompanha o projeto e já está configurada para usar:

```txt
baseUrl: http://localhost:8080
adminId: 1
userId: 2
cursoId: 1
```

Antes de executar os testes da collection, crie o ADMIN no H2 usando o comando SQL informado acima.
