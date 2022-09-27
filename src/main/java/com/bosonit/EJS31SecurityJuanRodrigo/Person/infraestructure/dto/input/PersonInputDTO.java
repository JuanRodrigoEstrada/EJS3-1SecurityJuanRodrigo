package com.bosonit.EJS31SecurityJuanRodrigo.Person.infraestructure.dto.input;

import lombok.Data;

@Data
public class PersonInputDTO {
    private String name;
    private String password;
    private String role = "user";

}
