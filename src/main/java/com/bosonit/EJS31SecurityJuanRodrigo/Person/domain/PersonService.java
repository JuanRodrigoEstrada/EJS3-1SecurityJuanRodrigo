package com.bosonit.EJS31SecurityJuanRodrigo.Person.domain;

import com.bosonit.EJS31SecurityJuanRodrigo.Person.infraestructure.Repositoryjpa.PersonRepository;
import com.bosonit.EJS31SecurityJuanRodrigo.Person.infraestructure.dto.input.PersonInputDTO;
import com.bosonit.EJS31SecurityJuanRodrigo.Person.infraestructure.dto.output.PersonOutputDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;


import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class PersonService implements PersonInterface{

    @Autowired
    PersonRepository personRepository;

    private final String secretPass = "MySecretPass";
    private String user;

    private String getJWToken(long id , String name, String role){
        List<GrantedAuthority> grantedAuthorities;
                if(role.equals("admin")){
                    grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");

                }
                else{
                    grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
                }
                String token = Jwts
                        .builder()
                        .setId(String.valueOf(id))
                        .setSubject(user)
                        .claim("Authorities" ,
                                grantedAuthorities.stream()
                                        .map(GrantedAuthority::getAuthority)
                                        .collect(Collectors.toList()))
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis() + 600000))
                        .signWith(SignatureAlgorithm.HS512, secretPass.getBytes())
                        .compact();

                return "Bearer " + token;


    }



    @Override
    public String login(String user, String password) {
        PersonEntity userlogin = null;
        if(personRepository.findByName(user).size() !=0){
            userlogin = personRepository.findByName(user).get(0);
            if(userlogin.getPassword().equals(password)){
                return getJWToken(userlogin.getId_person(),userlogin.getName(),userlogin.getRole());

            }
            else return "Incorrect Password , try again";



        }
        else return "User" + user + "not exist, try again";

    }

    @Override
    public List<PersonOutputDTO> getAllPersons() {
        List<PersonOutputDTO>personOutputDTOList = new ArrayList<>();
       for (PersonEntity personEntity : personRepository.findAll()){
            PersonOutputDTO personOutputDTO = new PersonOutputDTO(personEntity);
            personOutputDTOList.add(personOutputDTO);
        }
        return personOutputDTOList;
    }

    @Override
    public boolean existPerson(int id) {
        return personRepository.existsById(id);
    }

    @Override
    public PersonOutputDTO getPersonById(int id) {
        PersonEntity personEntity = personRepository.findById(id).orElse(null);
        PersonOutputDTO personOutputDTO = new PersonOutputDTO(personEntity);
        return personOutputDTO;
    }

    @Override
    public List<PersonOutputDTO> getPersonsByName(String name) {
        List<PersonOutputDTO>personOutputDTOList = new ArrayList<>();
        for (PersonEntity personEntity : personRepository.findByName(name)){
            PersonOutputDTO auxOutputDTO = new PersonOutputDTO(personEntity);
            personOutputDTOList.add(auxOutputDTO);
        }
        return personOutputDTOList;
    }

    @Override
    public PersonOutputDTO getPerson(int id, String name) {
        return null;
    }


    @Override
    public PersonOutputDTO updatePerson(int id, PersonInputDTO personInputDTO) {
       PersonEntity personEntity = personRepository.findById(id).orElse(null);
       personEntity.UpdateEntity(personInputDTO);
       personRepository.save(personEntity);
       PersonOutputDTO personOutputDTO = new PersonOutputDTO(personEntity);
       return personOutputDTO;
    }

    @Override
    public PersonOutputDTO postPerson(PersonInputDTO personInputDTO) {
        PersonEntity personEntity = new PersonEntity(personInputDTO);
        personRepository.save(personEntity);
        PersonOutputDTO personOutputDTO  = new PersonOutputDTO(personEntity);
        return personOutputDTO;
    }

    @Override
    public PersonOutputDTO deletePerson(int id) {
       PersonOutputDTO personOutputDTO = getPersonById(id);
       personRepository.deleteById(id);
        return personOutputDTO;
    }
}
