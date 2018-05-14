# RowCallBackHandler
  
```java
  @Repository
  public class PizzaDao {
      ...
      public void exporter() {
          String sql = "SELECT * FROM PIZZA";
          this.jdbcTemplate.query(sql, new PizzaTxtExporter());
      }
  }
  public class PizzaTxtExporter implements RowCallBackHandler {
      public void processRow(ResultSet rs) throws SQLException {
          // code d'export
      }
  }
  ```