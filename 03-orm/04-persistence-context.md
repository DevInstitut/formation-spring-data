# @PersistenceContext

```java
public class PizzaService {

    // injecter une instance d'EntityManager
    @PersistenceContext private EntityManager em;

    @Transactional
    public void create(Pizza pizza) {
        em.persist(pizza);
    }
}
```