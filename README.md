# Modificări Aduse Codului din Laboratorul 3

## 1. Adăugare Mesaje Flash

Template-uri actualizate (tasks.html, categories.html, tags.html):
```html
<div th:if="${successMessage}" class="alert alert-success alert-dismissible fade show">
   <span th:text="${successMessage}"></span>
   <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
</div>
```
Script auto-închidere mesaje:
```javascript
document.addEventListener('DOMContentLoaded', function() {
   const alerts = document.querySelectorAll('.alert');
   alerts.forEach(alert => {
       setTimeout(() => alert.querySelector('.btn-close')?.click(), 5000);
   });
});
```
## 2. Modificări în Controllere
TaskController, CategoryController și TagController:
```java
@PostMapping
public String createCategory(..., RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("successMessage", "Categoria a fost creată cu succes!");
    return "redirect:/categories";
}
```
## 3. Securitate
Configurare CSRF și restricții IP:
```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
            .antMatchers("/**").hasIpAddress("localhost")
            .anyRequest().authenticated()
        .and()
            .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
}
```

## Screenshots:

![image](https://github.com/user-attachments/assets/42d8dbb8-bd5c-4521-8b95-11fcc2be64da)

![image](https://github.com/user-attachments/assets/8b15b0d4-5e00-490a-be77-1634f74df352)

![image](https://github.com/user-attachments/assets/afcf0588-0335-4fb2-98a3-224352af2e16)

![image](https://github.com/user-attachments/assets/cc6dfa75-8287-468f-beb3-d9f74e24621a)

