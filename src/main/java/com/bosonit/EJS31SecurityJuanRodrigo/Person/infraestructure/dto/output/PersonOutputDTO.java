package com.bosonit.EJS31SecurityJuanRodrigo.Person.infraestructure.dto.output;

import com.bosonit.EJS31SecurityJuanRodrigo.Person.domain.PersonEntity;
import lombok.Data;

@Data
public class PersonOutputDTO {
    private int id;
    private String name;
    private String role;

    public PersonOutputDTO(PersonEntity personEntity){
        if (personEntity == null) return;
        id = personEntity.getId_person();
        name = personEntity.getName();
        role= personEntity.getRole();

    }




}
