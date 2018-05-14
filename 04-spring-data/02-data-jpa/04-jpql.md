# JPQL via @Query

```java
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("select u from Person u where u.firstname = :firstname or u.lastname = :lastname")
    Person findByLastnameOrFirstname(@Param("lastname") String lastname, @Param("firstname") String firstname);
}
```