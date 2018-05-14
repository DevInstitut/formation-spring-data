package dev;

import dev.entities.Person;
import dev.repo.PersonRepo;
import dev.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class AppStartup {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    PersonService personService;

    @Autowired
    PersonRepo personRepo;

    @EventListener(ContextRefreshedEvent.class)
    @Transactional
    public void init() {
        em.persist(new Person("Oddet", "Rossi"));
        em.persist(new Person("Lescaut", "Julie"));
        //personService.findAll().forEach(System.out::println);
        em.flush();
        em.clear();
       // personRepo.findSQL().forEach(System.out::println);
        personService.findAllSQL().forEach(System.out::println);

    }

}
