# RowMapper


Spring Jdbc simplifie le passage de données SQL à objet Java via l'utilisation de l'interface **RowMapper**.

```java
@FunctionalInterface
public interface RowMapper<T> {

    /**
	 * @param rs the ResultSet to map (pre-initialized for the current row)
	 * @param rowNum the number of the current row
     * /
	T mapRow(ResultSet rs, int rowNum) throws SQLException;

}
```

## Exemple d'implémentation RowMapper

```java
public class PizzaMapper implements RowMapper<Pizza> {

    // cette méthode est invoquée pour chaque ligne de résultat SQL
    public Pizza mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pizza p = new Pizza();
        p.setId(rs.getInt("ID"));
        p.setPizzaName(rs.getString("PIZZA_NAME"));
        return p;
    }
}
```

## RowMapper avec une expression Lambda

```java
RowMapper<Pizza> mapper = (ResultSet rs, int rowNum) -> {
    Pizza p = new Pizza();
    p.setId(rs.getInt("ID"));
    p.setPizzaName(rs.getString("PIZZA_NAME"));
    return p;
}
```

## RowMapper avec JdbcTemplate

```java
String sql = "SELECT * FROM PIZZA WHERE PIZZA_NAME=?";
Pizza pizza = jdbcTemplate.queryForObject(sql, new PizzaMapper(), name);
```