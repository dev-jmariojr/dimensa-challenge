package br.com.jj.challenge.service;

import br.com.jj.challenge.model.Contacts;
import br.com.jj.challenge.repository.IContactsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactsService implements IContactsService {

    private final IContactsRepository contactsRepository;

    public ContactsService(IContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    @Override
    public List<Contacts> listAll(String paramName) {
        if(paramName.isEmpty())
            return contactsRepository.findAll();
        else
            return contactsRepository.findByNameContains(paramName);
    }

    @Override
    public Contacts findOne(Long id) {
        return contactsRepository.findById(id).orElse(null);
    }

    @Override
    public Contacts add(Contacts contacts) {
        return contactsRepository.save(contacts);
    }

    @Override
    public Contacts update(Contacts contacts) {
        if(contactsRepository.existsById(contacts.getId()))
            return contactsRepository.save(contacts);
        else
            return null;
    }

    @Override
    public Boolean remove(Contacts contacts) {
        try{
            contactsRepository.delete(contacts);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
