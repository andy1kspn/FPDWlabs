# **Lucrare de laborator nr. 1. Bazele HTTP**  
## Sarcina 1:
- Ce metodă HTTP a fost utilizată pentru a trimite cererea?
- Metoda HTTP POST a fost utilizata pentru a trimite cerere data.
  
![image](https://github.com/user-attachments/assets/9d945ecd-a82e-4cd0-aa2e-829d0723b1b4)

- Ce anteturi au fost trimise în cerere?
- accept, accept-encoding, accept-language, connection, keep-alive, content-length, content-type, host, origin, referer, user-agent, x-requested-with
  
![image](https://github.com/user-attachments/assets/b59de548-70a3-4388-b6eb-629226f894ea)

- Ce parametri au fost trimiși în cerere?
- Username si password care sunt egale in cazul meu cu "student"
  
![image](https://github.com/user-attachments/assets/39545f6b-1233-4393-b4a6-a02d55465cd9)

- Ce cod de stare a fost returnat de server?
- 401
  
![image](https://github.com/user-attachments/assets/c8f9566a-25c1-4e0a-adb4-620d674fb138)

- Ce anteturi au fost trimise în răspuns?
- connection, content-type, date, server, transfer-encoding
  
![image](https://github.com/user-attachments/assets/30d33f74-1763-46fc-949d-f8b70e94bbf4)

### Am repetat pasii folosind date corecte pentru autentificare

- Ce metodă HTTP a fost utilizată pentru a trimite cererea?
- Metoda HTTP POST a fost utilizata pentru a trimite cerere data.
  
![image](https://github.com/user-attachments/assets/449dd19b-b201-427b-8f62-c86181e9cef5)

- Ce anteturi au fost trimise în cerere?
- accept, accept-encoding, accept-language, connection, keep-alive, content-length, content-type, host, origin, referer, user-agent, x-requested-with
  
![image](https://github.com/user-attachments/assets/b59de548-70a3-4388-b6eb-629226f894ea)

- Ce parametri au fost trimiși în cerere?
- Username = "admin" si password = "password"
  
![image](https://github.com/user-attachments/assets/67b96664-20c5-42e1-aa4e-188f59b7f4f3)

- Ce cod de stare a fost returnat de server?
- 200
  
![image](https://github.com/user-attachments/assets/d1c5a20f-f7bf-43ae-b834-0f8d5ee4c689)

- Ce anteturi au fost trimise în răspuns?
- connection, content-type, date, server, transfer-encoding
  
![image](https://github.com/user-attachments/assets/30d33f74-1763-46fc-949d-f8b70e94bbf4)

## Sarcina 2:
### Pentru urmatoarea sarcina si pentru a transmite urmatoarele requesturi a fost utilizat POSTMAN
a)
![image](https://github.com/user-attachments/assets/d3203466-b68a-41a6-83ea-5a455634e3f8)
![image](https://github.com/user-attachments/assets/22752c4e-fc76-4cb5-ad24-e414a3206c7c)

b)  Pentru a scrie un POST request cu datele respective, putem utiliza mai multe instrumente: curl, POSTMAN, etc.
Exemplu CURL: ` curl -L -X POST http://sandbox.com/cars -H "Content-Type: text/plain" -d "make=Toyota&model=Corolla&year=2020" `
Exemplu POSTMAN:
![image](https://github.com/user-attachments/assets/c04eda5b-69fb-4d01-b137-d423055e04b6)
Acest tip de request va transmite datele respective catre serverul BACKEND pentru a fi salvate sau prelucrare.

c) Pentru a scrie un PUT request cu datele despective, putem utiliza comanda CURlL: 
`curl -L -X PUT http://sandbox.com/cars/1 \
-H "User-Agent: Spînu Andrei" \
-H "Content-Type: application/json" \
-d '{"make": "Toyota", "model": "Corolla", "year": 2021}'`
Sau fie prin POSTMAN. Acest tip de request va transmite datele respective pentru a actualiza datele automobilului cu id-ul 1

d) Un posibil response la cererea aterioara ar putea fi:
`
HTTP/1.1 201 Created
Content-Type: application/json
Location: http://sandbox.com/cars/1
Date: Mon, 11 Sep 2024 12:34:56 GMT

{
    "id": 1,
    "make": "Toyota",
    "model": "Corolla",
    "year": 2020,
    "status": "Updated successfully"
}
`

e) Pentru a transmite un request de DELETE folosind CURL putem folosi urmatoarea comanda care va sterge automobilul cu ID-ul 1
` DELETE /cars/1 HTTP/1.1
Host: sandbox.com
User-Agent: Spînu Andrei
`


## Sarcina 3:
### Pentru sarcina data, voi utiliza POSTMAN
![image](https://github.com/user-attachments/assets/964ecfde-bf80-45cd-8575-9155b3e03962)

In urma request-ului am primit un token pentru a continua
![image](https://github.com/user-attachments/assets/68beba27-1007-4eb3-9a2c-ec154760258e)

Dupa ce am trimit codul de autorizare catre http://sandbox.usm.md/quest/login folosind POST, primind instructiunile pentru urmatorul pas

![image](https://github.com/user-attachments/assets/51a97c35-0c1e-477a-82e9-aed20eeaad56)

Dupa ce am trimit ultimul GET request am obtinut urmatorul rezultat:

![image](https://github.com/user-attachments/assets/5f85bb0c-d03a-4162-afa5-a37e734bfeda)






