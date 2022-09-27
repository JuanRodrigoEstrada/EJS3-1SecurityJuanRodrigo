package com.bosonit.EJS31SecurityJuanRodrigo.Person.infraestructure.Repositoryjpa;

import com.bosonit.EJS31SecurityJuanRodrigo.Person.domain.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PersonRepository extends CrudRepository<PersonEntity, Integer> {
    List<PersonEntity>findByName(String name);
    boolean existsByName(String name);
}
