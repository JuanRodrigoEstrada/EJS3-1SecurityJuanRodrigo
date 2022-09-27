package com.bosonit.EJS31SecurityJuanRodrigo.Person.infraestructure.controller;

import com.bosonit.EJS31SecurityJuanRodrigo.Person.domain.PersonService;
import com.bosonit.EJS31SecurityJuanRodrigo.Person.infraestructure.dto.input.PersonInputDTO;
import com.bosonit.EJS31SecurityJuanRodrigo.Person.infraestructure.dto.output.PersonOutputDTO;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class controller {
    @Autowired
    PersonService personService;

    @PostMapping("/login")
    public String loginWay(@RequestBody ObjectNode request){
        return personService.login(request.get("user").asText(), request.get("password").asText());
    }
    @GetMapping
    public List<PersonOutputDTO>getPersonsRoute(){
        List<PersonOutputDTO>personOutputDTOList = personService.getAllPersons();
        return personOutputDTOList;

    }
    @GetMapping("/name/{name}")
    public List<PersonOutputDTO>getPersonsByNameRoute(@PathVariable String name){
        return personService.getPersonsByName(name);

    }
    @GetMapping("/{id}")
    public ResponseEntity<?>getPersonsByIdRoute(@PathVariable int id){
        if(!personService.existPerson(id)){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        PersonOutputDTO personOutputDTO = personService.getPersonById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/signup")
    public PersonOutputDTO postPersonRoute(@RequestBody PersonInputDTO personInputDTO){
        return personService.postPerson(personInputDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePersonRoute(@PathVariable int id , @RequestBody PersonInputDTO personInputDTO ){
        if(!personService.existPerson(id)){
            return  new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);

        }
        PersonOutputDTO personOutputDTO = personService.updatePerson(id , personInputDTO);
        return new ResponseEntity<>(personOutputDTO, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePersonByIdRoute(@PathVariable int id){
        if(!personService.existPerson(id)){
            return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
        }
        PersonOutputDTO personOutputDTO = personService.deletePerson(id);
        return new ResponseEntity<>(personOutputDTO,HttpStatus.ACCEPTED);
    }






}
