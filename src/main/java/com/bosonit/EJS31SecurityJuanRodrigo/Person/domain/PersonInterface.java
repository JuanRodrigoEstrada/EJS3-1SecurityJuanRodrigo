package com.bosonit.EJS31SecurityJuanRodrigo.Person.domain;

import com.bosonit.EJS31SecurityJuanRodrigo.Person.infraestructure.dto.input.PersonInputDTO;
import com.bosonit.EJS31SecurityJuanRodrigo.Person.infraestructure.dto.output.PersonOutputDTO;

import java.util.List;

public interface PersonInterface {

    String login (String user , String password);
    List<PersonOutputDTO>getAllPersons();
    boolean existPerson(int id);
    PersonOutputDTO getPersonById(int id);
    List<PersonOutputDTO>getPersonsByName(String name);
    PersonOutputDTO getPerson(int id , String name);
    PersonOutputDTO updatePerson (int id , PersonInputDTO personInputDTO);
    PersonOutputDTO postPerson(PersonInputDTO personInputDTO);
    PersonOutputDTO deletePerson(int id);





}
