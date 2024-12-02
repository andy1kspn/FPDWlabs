# Lucrarea de Laborator 5 la Frameworkuri pentru Dezvoltarea Aplicatiilor WEB

## Descriere
Implementarea unui sistem de autentificare și autorizare folosind Spring Security și Thymeleaf.

## Funcționalități
- Înregistrare utilizatori cu criptare multi-nivel:
  - Înlocuirea vocalelor
  - Criptare BCrypt
- Autentificare bazată pe roluri (USER/ADMIN)
- Dashboard personalizat cu:
  - Monitorizarea tentativelor de conectare
  - Management utilizatori (pentru admin)
  - Logs cu timestamp și IP

## Tehnologii
- `Spring Boot 2.6.1`
- `Spring Security`
- `Thymeleaf`
- `MySQL`
- `Lombok`
- `BCrypt`

## Securitate
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //... configurare pentru rutare și autorizare
}
```

## Design
- Interfață cu stil neumorphic
- Panouri interactive pentru management
- Validare în timp real
- Responsive design

## Rulare
```bash
mvn spring-boot:run
```

## Endpoint-uri
- `/register` - Înregistrare utilizator nou
- `/login` - Autentificare
- `/dashboard` - Panou admin
- `/home` - Pagină utilizator

## Bază de date
```sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);
```

## Screenshots:

![image](https://github.com/user-attachments/assets/64607ee3-f80a-40d5-825c-98d830379243)

![image](https://github.com/user-attachments/assets/d4437ad7-d967-4fd3-ac6a-06b9504e6a8d)

![image](https://github.com/user-attachments/assets/690c20dc-54fc-4008-ba15-3dd2e5e0c49f)

![image](https://github.com/user-attachments/assets/abd3516e-9dd2-4fd5-9287-ebc2056bd64c)

![image](https://github.com/user-attachments/assets/08f49cb5-79c4-4862-9e3f-1a3539cd01ad)




