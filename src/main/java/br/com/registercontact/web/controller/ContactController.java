package br.com.registercontact.web.controller;

import br.com.registercontact.entity.Contact;
import br.com.registercontact.service.ContactService;
import br.com.registercontact.web.request.RegisterContactRequest;
import br.com.registercontact.web.response.RegisterContactResponse;
import br.com.registercontact.web.response.SelectContactResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public RegisterContactResponse registerContactResponse(@RequestBody RegisterContactRequest request) {
        return contactService.registerContactResponse(request);
    }

    @GetMapping
    public List<Contact> selectContactResponse() {
        return this.contactService.selectContactResponse();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable(value = "id") Long id){

        return this.contactService.deleteContact(id);
    }
}
