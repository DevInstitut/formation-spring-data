# ResultSetExtractor

```java
@Repository
public class PizzaDao {
    ...
    public PizzaResponse findAllPizza() {
        String sql = "SELECT * FROM PIZZA";
        return this.jdbcTemplate.query(sql, new PizzaResponseExtractor());
    }
    }
    public class PizzaResponseExtractor implements ResultSetExtractor<Pizza> {
        public PizzaResponse extractData(ResultSet rs) throws SQLException {
            // code d'extraction
        }
    }
```