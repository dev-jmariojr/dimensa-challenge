package br.com.jj.challenge.controller;

import br.com.jj.challenge.model.Contacts;
import br.com.jj.challenge.service.ContactsService;
import br.com.jj.challenge.service.IContactsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/contacts")
public class ContactsController {

    private final IContactsService contactsService;

    @Value("${app.name}")
    private String APP_NAME;

    public ContactsController(ContactsService contactsService) {
        this.contactsService = contactsService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/info")
    public String infoApp(){
        StringBuilder builder = new StringBuilder();
        return builder
                .append(APP_NAME)
                .append(" - ")
                .append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")))
                .toString();
    }

    @GetMapping
    public ResponseEntity<List<Contacts>> findAll(@RequestParam(required = false) Optional<String> name){
        List<Contacts> list = contactsService.listAll(name.orElse(""));
        if(list.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contacts> findOne(@PathVariable Long id){
        Contacts contacts = contactsService.findOne(id);
        if(contacts == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(contacts);
    }

    @PostMapping
    public ResponseEntity<Contacts> add(@RequestBody Contacts value){
        return ResponseEntity.ok(contactsService.add(value));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contacts> update(@PathVariable Long id, @RequestBody Contacts value){
        Contacts contacts = contactsService.findOne(id);
        if(contacts ==null)
            return ResponseEntity.notFound().build();
        else{
            contacts.setName(value.getName());
            contacts.setEmail(value.getEmail());
            contacts.setBirthDate(value.getBirthDate());

            return ResponseEntity.ok(contactsService.update(contacts));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable Long id){
        Contacts contacts = contactsService.findOne(id);
        if(contacts == null)
            return ResponseEntity.notFound().build();
        else{
            return ResponseEntity.ok(contactsService.remove(contacts));
        }
    }
}
