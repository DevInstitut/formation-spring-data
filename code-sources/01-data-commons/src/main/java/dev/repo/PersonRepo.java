package dev.repo;

import dev.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;

public interface PersonRepo extends JpaRepository<Person, Integer> {

    @Query(value = "select id, LASTN from PERS", nativeQuery = true)
    Stream<Person> findSQL();
}
