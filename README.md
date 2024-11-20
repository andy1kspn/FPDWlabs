# Task Management System - Framework pentru Dezvoltarea Aplicatiilor WEB LAB 3

## Descriere
Un sistem de management al sarcinilor (tasks) implementat în Spring Boot, care permite organizarea task-urilor în categorii și etichetarea acestora cu tag-uri.

## Funcționalități

### 1. Gestiunea Categoriilor
- Vizualizarea listei de categorii
- Adăugarea unei categorii noi
- Editarea unei categorii existente
- Ștergerea unei categorii
- Vizualizarea numărului de task-uri asociate fiecărei categorii

### 2. Gestiunea Tag-urilor
- Vizualizarea listei de tag-uri
- Adăugarea unui tag nou
- Editarea unui tag existent
- Ștergerea unui tag
- Vizualizarea numărului de task-uri asociate fiecărui tag

### 3. Gestiunea Task-urilor
- Vizualizarea listei de task-uri
- Adăugarea unui task nou cu:
  - Titlu și descriere
  - Asocierea cu o categorie
  - Asocierea cu multiple tag-uri
- Editarea unui task existent
- Ștergerea unui task

## Structură Tehnică

### Entități
1. Category
```java
- id (Long)
- name (String)
- description (String)
- tasks (Set)
- createdAt (LocalDateTime)
- updatedAt (LocalDateTime)
```

2. Tag
```java
- id (Long)
- name (String)
- tasks (Set)
- createdAt (LocalDateTime)
- updatedAt (LocalDateTime)
```

3. Task
```java
- id (Long)
- title (String)
- description (String)
- category (Category)
- tags (Set)
- createdAt (LocalDateTime)
- updatedAt (LocalDateTime)
```

### Arhitectură
- **Controller Layer**: Gestionează cererile HTTP și rutarea
- **Service Layer**: Conține logica de business
- **Repository Layer**: Gestionează accesul la baza de date
- **DTO Layer**: Obiecte pentru transfer de date
- **Entity Layer**: Modelele de bază ale aplicației

### Tehnologii Utilizate
- Spring Boot 2.6.1
- Spring Data JPA
- Thymeleaf
- MySQL/MariaDB
- Bootstrap 5.1.3
- Lombok

### Relații între Entități
- Category -> Task: One-to-Many
- Tag <-> Task: Many-to-Many
- Task -> Category: Many-to-One

## Instalare și Rulare

### Cerințe preliminare
- Java JDK 17 sau superior
- Maven
- MySQL/MariaDB

### Configurare Bază de Date
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/todo_app
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### Pașii de Instalare
1. Clonați repository-ul
2. Configurați baza de date în `application.properties`
3. Rulați `mvn clean install`
4. Porniți aplicația cu `mvn spring-boot:run`

### Accesare
- Aplicația rulează pe `http://localhost:8080`
- Endpoint-uri disponibile:
  - `/categories` - Gestiunea categoriilor
  - `/tags` - Gestiunea tag-urilor
  - `/tasks` - Gestiunea task-urilor

## Caracteristici Suplimentare
- Validări pentru câmpurile obligatorii
- Timestamp-uri automate pentru createdAt și updatedAt
- Interfață responsive folosind Bootstrap
- Confirmări pentru operațiile de ștergere
- Modals pentru operațiile de editare


![image](https://github.com/user-attachments/assets/dbe3449a-33e2-4396-b60c-12e8419c593b)

![image](https://github.com/user-attachments/assets/258ae67e-1d87-4cb3-be14-f46280ce6ecc)

![image](https://github.com/user-attachments/assets/58171d69-c03d-4748-bb67-f396fc1ec784)

![image](https://github.com/user-attachments/assets/db532084-3c54-41ae-bf04-4a607f3e51d1)
