package br.com.jj.challenge.service;

import br.com.jj.challenge.model.Contacts;
import br.com.jj.challenge.repository.IContactsRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ContactsServiceTest {

    @InjectMocks
    private ContactsService service;

    @Mock
    private IContactsRepository repository;

    @Test
    public void listAll_ReturnIsEmpty() {
        List<Contacts> contacts = Arrays.asList();
        Mockito.when(repository.findAll()).thenReturn(contacts);
        Assertions.assertEquals(service.listAll(""), contacts);
    }

    @Test
    public void findOne_ReturnNotNull() {
        Contacts contacts = Contacts.builder()
                .id(1L)
                .name("JOSE")
                .phoneNumber("98988776655")
                .email("jose@email.com")
                .birthDate(LocalDate.of(2001,1,1))
                .build();

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(contacts));
        Assertions.assertNotEquals(service.findOne(1L), null);
    }

    @Test
    public void add_ReturnNotNull() {
        Contacts value = Contacts.builder()
                .name("JOSE MARIO")
                .phoneNumber("98988776655")
                .email("josemario@email.com")
                .birthDate(LocalDate.of(2001,1,1))
                .build();

        Contacts result = Contacts.builder()
                .id(1L)
                .name("JOSE MARIO")
                .phoneNumber("98988776655")
                .email("josemario@email.com")
                .birthDate(LocalDate.of(2001,1,1))
                .build();

        Mockito.when(repository.save(value)).thenReturn(result);
        Assertions.assertEquals(service.add(value), result);
    }

    @Test
    public void update_ReturnNull(){
        Contacts value = Contacts.builder()
                .id(999L)
                .build();

        Mockito.when(repository.existsById(value.getId())).thenReturn(false);
        Assertions.assertEquals(service.update(value), null);
    }

    @Test
    public void remove_ReturnTrue(){
        Contacts value = Contacts.builder()
                .id(999L)
                .name("JOSE MARIO JUNIOR")
                .phoneNumber("98988776655")
                .email("junior@email.com")
                .birthDate(LocalDate.of(2001,1,1))
                .build();

        Assertions.assertEquals(service.remove(value), true);
    }

}