# Requêtes dynamiques


```java

public interface PersonRepository extends JpaRepository<Person, Long> {

    // équivalent de "select p from Person p where address=? and lastname=?"
    List<Person> findByEmailAddressAndLastname(EmailAddress emailAddress, String lastname);
    
    List<Person> findDistinctPeopleByLastnameOrFirstname(String lastname, String firstname);
    List<Person> findPeopleDistinctByLastnameOrFirstname(String lastname, String firstname);
    List<Person> findByLastnameAndFirstnameAllIgnoreCase(String lastname, String firstname);
    List<Person> findByLastnameOrderByFirstnameAsc(String lastname);
}
```