Projeto de estudos de backend para um sistema de streams onde utilizo a netflix como exemplo


O que já está feito:

- Criação de usuários (simples)
- Vídeos no banco de dados são transmitiveis
- Stream de vídeos (pode ser utilizado AWS futuramente)
- Kafka para processamento dos pagamentos
- Projeto está ligado a um banco de dados em PostgreSQL
- Autenticação de usuários com criptografia de dados sensiveis


Futuramente:
- Melhorar a parte de registro de usuários, encriptando senhas e informações sensiveis
- Fazer sistema de autenticação real onde só poderá assistir ao estar logado
- Só poder assistir ao ser um usuário assinante (atualmente todos podem assistir)
- Salvar cartões de créditos de uma forma segura

POST: /api/login:
Parâmetros: usuário e senha

GET: /api/users:
Retorna uma lista de todos os usuários


Post: /api/payments/
Parâmetros: {
"amount":,
"status": "",
"paymentMethod": "",
"transactionId": "",
"user": {
"id":
}
Adiciona um pagamento a fila

GET: api/video/{ID}
Retorna o video pelo ID

POST: api/users/register
{
"email": "",
"username":"",
"name": "",
"password": "",
"role":"",
"hasPaid": ,
"accessExpirationDate":
}
Criação de usuários

GET: api/payments/{ID}
Retorna o pagamento de acordo com o ID