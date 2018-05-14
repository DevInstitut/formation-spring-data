# JdbcTemplate (query)

```java
String sql = "SELECT * FROM PIZZA";
List<Pizza> pizzas = jdbcTemplate.query(sql, new PizzaMapper());
```