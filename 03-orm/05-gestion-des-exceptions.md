# Gestion des exceptions

```java
@Transactional(rollbackFor=Throwable.class, noRollbackFor=PizzaAccessException.class)
public void create(Pizza pizza) {
    
}
```