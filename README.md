# Lucrul Individual la Frameworkuri pentru Dezvoltarea Aplicatiilor WEB

## Descriere
Code Battle este un joc multiplayer în browser care permite testarea cunoștințelor de programare într-un mediu competitiv. Jocul conectează 3 jucători într-o sesiune unde aceștia concurează răspunzând la diverse provocări din domeniul programării.

## Autorii
  - Spînu Andrei
  - Onea Alexei
    
## Funcționalități
- Conectare simultană a 3 jucători
- Sistem de matchmaking automat
- Trei tipuri de provocări:
  - Probleme cu arrays
  - Queries SQL
  - Întrebări teoretice

- Sistem de scoring bazat pe corectitudinea răspunsului și timpul de răspuns
- Interfață responsivă cu editor de cod integrat
- Comunicare în timp real folosind WebSocket

## Tehnologii
- `Spring Boot 2.6.1`
- `Spring Security`
- `Thymeleaf`
- `MySQL`
- `Lombok`
- `Jackson`
- `SockJS & STOMP`

## Securitate
- Sesiuni de joc izolate
- Validare server-side pentru răspunsuri
- Protecție împotriva accesului neautorizat la sesiunile de joc
- Gestionare sigură a stării jocului

## Design
- Interfață modernă și responsivă
- Editor de cod cu highlight sintactic
- Sistem de notificare în timp real
- Design adaptat pentru experiență de joc fluidă

## Rulare
- Clonează repository-ul
- Configurează MySQL și actualizează application.properties
- Rulează mvn clean install
- Pornește aplicația folosind mvn spring-boot:run
- Accesează http://localhost:8080

## Endpoint-uri
- GET / - Pagina de start
- GET /game - Interfața jocului
- GET /waiting - Pagina de așteptare
- WebSocket /game-websocket - Endpoint pentru comunicare în timp real

## Bază de date
Entități principale:
  - GameSession - Stocarea sesiunilor de joc
  - Player - Informații despre jucători
  - Challenge - Provocările din joc
  - Submission - Răspunsurile jucătorilor

## Screenshots:

![image](https://github.com/user-attachments/assets/bdd6080b-77c2-4b3a-80e8-8de48d209744)

![image](https://github.com/user-attachments/assets/720cca3b-b4de-47b5-8e34-ac55831066d9)

![image](https://github.com/user-attachments/assets/2e66c887-58ca-41e5-8b83-4a31ed74de9c)

![image](https://github.com/user-attachments/assets/291c8204-9efb-422a-820c-0970a7fa470f)





