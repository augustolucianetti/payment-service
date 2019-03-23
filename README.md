# payment-service

1. Salvar: 
  URL: http://localhost:8080/payment/save
  Método: POST
  Exemplo de request: 
  {
    	"type" : "cartao",
    	"transactionId" : "1",
    	"cardNumber" : 123456,
    	"validateDateCard" : "1",
    	"flag" : "elo"
    }

2. Buscar por id:
  URL: http://localhost:8080/payment/findById/{transactionId}
  Método: GET

3. Atualizar dados:
  URL: http://localhost:8080/payment/update/{transactionId}
  Método: PUT
  Exemplo de request: 
  {
    	"type" : "cartao4",
    	"transactionId" : "1",
    	"cardNumber" : 123456,
    	"validateDateCard" : "1",
    	"flag" : "elo"
    }

4. Excluir registro:
  URL: http://localhost:8080/payment/delete/{transactionId}
  Método: DELETE
