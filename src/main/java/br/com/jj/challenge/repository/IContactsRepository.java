package br.com.jj.challenge.repository;

import br.com.jj.challenge.model.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IContactsRepository extends JpaRepository<Contacts, Long> {

    List<Contacts> findByNameContains(String name);

}
