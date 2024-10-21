# Lucrare de laborator nr. 2.

## Condiții
În cadrul laboratorului de la disciplina "Framework-uri pentru dezvoltarea Web", am avut ca obiectiv dezvoltarea unei aplicații web folosind Spring si Thymeleaf. Am lucrat cu cererile HTTP, gestionarea rutelor și șablonizarea interfețelor. Aplicația a fost proiectată pentru a permite utilizatorilor să interacționeze cu datele printr-o interfață prietenoasă, facilitând astfel procesul de rezervare a locurilor în autobuzele internaționale.

## Pași
1. **Am scris codul pentru gestionarea cererilor HTTP**  
   Acest cod permite aplicației să primească și să proceseze cereri de la utilizatori, asigurând comunicarea între client și server.
   `
@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public String index(Model model) {
        List<TaskModel> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "task-list";
    }

    @GetMapping("/create")
    public String create() {
        return "task-create";
    }

    @PostMapping
    public String store(@RequestParam String name) {
        TaskModel task = new TaskModel(name);
        taskRepository.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        TaskModel task = taskRepository.findById(id).orElse(null);
        model.addAttribute("task", task);
        return "task-show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        TaskModel task = taskRepository.findById(id).orElse(null);
        model.addAttribute("task", task);
        return "task-edit";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @RequestParam String name) {
        TaskModel task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setName(name);
            taskRepository.save(task);
        }
        return "redirect:/tasks";
    }

    @PostMapping("/{id}/delete")
    public String destroy(@PathVariable("id") Long id) {
        taskRepository.deleteById(id);
        return "redirect:/tasks";
    }
}
`

3. **Am implementat rutele în Spring**  
   Am configurat rutele pentru a permite utilizatorilor să acceseze diferite secțiuni ale aplicației, facilitând navigarea între pagini.

4. **Am creat șabloane utilizând Tymeleaf**  
   Am dezvoltat șabloane pentru interfețele utilizatorului, ceea ce face ca aplicația să fie mai organizată și ușor de întreținut. Aceste șabloane includ formulare pentru înregistrare și rezervare.

5. **Am integrat baza de date**  
   Am realizat migrarea bazei de date pentru a stoca informațiile utilizatorilor și rezervărilor. Acest pas este esențial pentru gestionarea eficientă a datelor.

6. **Am scris codul pentru a manipula datele din baza de date**  
   Acest cod permite aplicației să salveze, să actualizeze și să șteargă înregistrările, asigurând o gestionare corectă a informațiilor.
   `
spring.application.name=FDWproject2
spring.datasource.url=jdbc:mysql://localhost:3306/lab2?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
`
