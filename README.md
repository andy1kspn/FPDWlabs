# Frameworkuri pentru Dezvoltarea Aplicatiilor WEB - EXAMEN

## Descriere
Aplicatie web pentru managementul meniurilor unui restaurant

## Arhitectura aplicatiei
### Models, views, controllers
* **Models**: 
 * MenuItem
 * MenuCategory
 * Order
 * User
* **Views**: 
 * Implementate cu Thymeleaf
 * Layout-uri separate pentru admin si client
* **Controllers**: 
 * WebController pentru gestionarea rutelor
 * OrderController pentru API endpoints

## Schema bazei de date
### Tabele principale
* `users`
* `menu_categories`
* `menu_items`
* `orders`

### Relatii
* `OneToMany` intre MenuCategory si MenuItem
* `ManyToOne` intre MenuItem si MenuCategory
* `OneToMany` intre User si Order

## Stack tehnologic
* Spring Boot
* Spring MVC
* Spring Data JPA
* Thymeleaf
* MySQL
* Bootstrap 
* Hibernate ORM

## Pasi pentru crearea proiectului
1. Initializare proiect Spring Boot
2. Configurare dependente `pom.xml`
3. Setup baza de date in `application.properties`
4. Definire modele si repository-uri
5. Implementare controllere si servicii
6. Creare views Thymeleaf
7. Stilizare CSS

## Metode din controller
```java
@GetMapping("/menu")
public String showMenu(Model model)

@PostMapping("/admin/menu/add")
public String addMenuItem(@ModelAttribute MenuItem menuItem)

@PostMapping("/admin/menu/delete/{id}")
public String deleteMenuItem(@PathVariable Long id)
```

## Validare date
```java
@NotBlank(message = "Name is required")
private String name;

@NotNull(message = "Price is required")
@DecimalMin("0.0")
private BigDecimal price;
```

## Validare date
```java
@NotBlank(message = "Name is required")
private String name;

@NotNull(message = "Price is required")
@DecimalMin("0.0")
private BigDecimal price;
```

## Views (Thymeleaf)
```html
<!-- admin/menu-management.html -->
<form th:action="@{/admin/menu/add}" method="post">
    <input type="text" name="name" required>
    <input type="number" name="price" required>
    <button type="submit">Add Item</button>
</form>
```

## Modele exemplu
```java
@Entity
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean available;
}

@Entity
public class MenuCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
}
```
## Migratii
```sql
CREATE TABLE menu_items (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    category_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    available BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (category_id) REFERENCES menu_categories(id)
);
```

### Rute
```java
@RequestMapping("/")
public class WebController {
    @GetMapping("/menu")
    @GetMapping("/admin/menu-management")
    @GetMapping("/admin/dashboard")
    @PostMapping("/admin/menu/add")
    @PostMapping("/admin/menu/delete/{id}")
}
```

## Nota importanta
```txt
Aplicatia a fost proiectata sa includa gestionarea rolurilor (admin/client), insa din cauza constrangerilor de timp,
aceasta functionalitate nu a fost complet implementata. Structura bazei de date si modelele suporta aceasta functionalitate,
dar logica de autentificare si autorizare necesita dezvoltare ulterioara.
```

## Screnshot-uri pentru demostrarea functionaliatii:

- Accesam aplicaia la ruta /
![image](https://github.com/user-attachments/assets/cac0ca91-cd9b-4f68-a06b-6c856aab8308)

- Accesam lista de meniuri
![image](https://github.com/user-attachments/assets/1d7eb767-2f5e-4d5e-9913-470d8287bbed)

- Observam ca nu sunt meniuri disponibile. Accesam aplicatia la ruta /admin/menu-management
![image](https://github.com/user-attachments/assets/42ea843d-f7d1-495e-a2fd-4fb65df1c299)

- Adaugam un produs in lista
![image](https://github.com/user-attachments/assets/37ce40d5-3b84-403c-93c5-ad51f033bb30)

![image](https://github.com/user-attachments/assets/15553b8c-be69-4ec9-a15a-7a976f509d3c)

- Accesam din nou lista de meniuri la ruta /menu
![image](https://github.com/user-attachments/assets/ded0b5b6-ed97-483d-ba81-ee2878e4d641)

- Accesam din nou pagina pentru administrare si v-om elimina produsul dat
![image](https://github.com/user-attachments/assets/02d7fd34-e817-48e6-9ee7-5648c2896b54)

- Si produsul a fost sters
![image](https://github.com/user-attachments/assets/a025cb49-cb0f-4ad1-b475-d64edfd03597)


