package br.com.registercontact.service;

import br.com.registercontact.entity.Contact;
import br.com.registercontact.repository.ContactRepository;
import br.com.registercontact.web.request.RegisterContactRequest;
import br.com.registercontact.web.response.RegisterContactResponse;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public RegisterContactResponse registerContactResponse(RegisterContactRequest request) {
        var response = this.contactRepository.save(mapper.map(request, Contact.class));
        return this.mapper.map(response, RegisterContactResponse.class);
    }

    public List<Contact> selectContactResponse() {
        return this.contactRepository.findAll();
    }

    public ResponseEntity<Void> deleteContact(Long id) {
        var check = this.contactRepository.findById(id);
        if (check.equals(null)) {
            return ResponseEntity.notFound().build();
        }
        this.contactRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}