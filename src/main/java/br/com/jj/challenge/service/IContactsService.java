package br.com.jj.challenge.service;

import br.com.jj.challenge.model.Contacts;

import java.util.List;

public interface IContactsService {

    List<Contacts> listAll(String paramName);

    Contacts find(Long id);

    Contacts add(Contacts contacts);

    Contacts update(Contacts contacts);

    Boolean remove(Contacts contacts);

}
