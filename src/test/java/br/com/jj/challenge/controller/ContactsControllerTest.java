package br.com.jj.challenge.controller;

import br.com.jj.challenge.model.Contacts;
import br.com.jj.challenge.service.ContactsService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ContactsControllerTest {

    @InjectMocks
    private ContactsController controller;

    @Mock
    private ContactsService service;

    @Test
    public void findAll_ReturnNoContent(){
        List<Contacts> contactsList = new ArrayList<>();
        Mockito.when(service.listAll("")).thenReturn(contactsList);
        Assertions.assertEquals(controller.findAll(Optional.of("")).getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void findOne_ReturnNotFound(){
        Long id = 1L;
        Mockito.when(service.findOne(id)).thenReturn(null);
        Assertions.assertEquals(controller.findOne(id).getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void remove_ReturnNotFound(){
        Long id = 999L;
        Mockito.when(service.findOne(id)).thenReturn(null);
        Assertions.assertEquals(controller.remove(id).getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void remove_ReturnOK(){
        Long id = 888L;
        Contacts contacts = Contacts.builder()
                .id(id)
                .name("JOSE")
                .email("jose@email.com")
                .phoneNumber("98999887766")
                .birthDate(LocalDate.of(2001,1,1))
                .build();

        Mockito.when(service.findOne(id)).thenReturn(contacts);
        Assertions.assertEquals(controller.remove(id).getStatusCode(), HttpStatus.OK);
    }

}