package com.bosonit.EJS31SecurityJuanRodrigo.Person.domain;

import com.bosonit.EJS31SecurityJuanRodrigo.Person.infraestructure.dto.input.PersonInputDTO;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class PersonEntity {

    @Id
    @GeneratedValue
    int id_person;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String password;
    @Column(nullable = false)
    String role;

    public PersonEntity(){

    }

    public PersonEntity(PersonInputDTO personInputDTO){
        if(personInputDTO == null)return;

        name = personInputDTO.getName();
        password = personInputDTO.getPassword();
        role = personInputDTO.getRole();



    }
    public void updateEntity(PersonInputDTO personInputDTO){
        setName(personInputDTO.getName());
        setPassword(personInputDTO.getPassword());
        setRole(personInputDTO.getRole());
    }

    public void UpdateEntity(PersonInputDTO personInputDTO) {
    }
}
