# JdbcTemplate

## Exemple d'utilisation

```java
@Repository
public class PizzaDao {
    // outil JdbcTemplate fourni par Spring JDBC
    private JdbcTemplate jdbcTemplate;

    @Autowired  // injection de la source de données
    public PizzaDao(DataSource datasource) {
        this.jdbcTemplate = new JdbcTemplate(datasource);
    }
    public Integer countPizzas() {
        String sql = "SELECT COUNT(*) FROM PIZZA";
        // exécution de la requête et récupération du résultat
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
```


## JdbcTemplate avec paramètres

```java
// requête SQL avec paramètre
String sql = "SELECT COUNT(*) FROM PIZZA WHERE PIZZA_NAME=?";

// paramètre fourni
Integer count = this.jdbcTemplate.queryForObject(sql, Integer.class, name);
```