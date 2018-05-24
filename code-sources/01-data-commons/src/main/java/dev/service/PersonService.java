package dev.service;

import dev.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PersonService {

    @PersistenceContext
    EntityManager em;

    @Autowired
    JdbcTemplate jdbcTemplate;



    public Stream<Person> findAll() {
        return em.createQuery("select p from Person p", Person.class).getResultStream();
    }

    public List<Person> findAllSQL() {
        return jdbcTemplate.query("select LASTN, firstname from PERS", (rs, i) -> {
            return new Person(rs.getString("LASTN"), rs.getString("firstname"));
        });
    }

}
