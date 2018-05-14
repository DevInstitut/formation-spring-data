# Spring JDBC


## Bases de données embarquées

Spring Jdbc supporte les bases de données embarquées : H2, HSQL, DERBY.

```java
@Configuration
public class Config {

    @Bean
    public DataSource datasource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .addScript("schema.sql")
            .addScript("test-data.sql")
            .build();
    }
}
```


## Source de données relationnelles
La classe DriverManagerDataSource fourni par Spring permet de créer une source de données JDBC.
Elle utilisée surtout dans le cadre de tests car elle ne fournie pas un pool de connexion.

```java
@Configuration
public class Config {

    @Bean
    public DataSource datasource() {
       DriverManagerDataSource	dataSource	=	new	DriverManagerDataSource();
       dataSource.setDriverClassName("com.mysql.jdbc.Driver");
       dataSource.setUrl("jdbc:mysql://localhost:3306/pizzeria?useSSL=false");
       dataSource.setUsername("root");
       dataSource.setPassword("");
       return dataSource;
    }
}
```



## JdbcTemplate

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



## RowMapper

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



## JdbcTemplate (query)

```java
String sql = "SELECT * FROM PIZZA";
List<Pizza> pizzas = jdbcTemplate.query(sql, new PizzaMapper());
```



## RowCallBackHandler

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



## ResultSetExtractor

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



## Lequel utiliser ?

L'interface « RowMapper »::
* Adapté lorsque chaque tuple est associé à un objet métier.

L'interface « RowCallbackHandler »::
* Adapté lorsque aucun résultat ne doit être retourné pour chaque tuple.

L'interface « ResultSetExtractor »::
* Adapté lorsqu'un ensemble de tuples correspond à un seul objet métier.


## Modifier des données

```java
// mise à jour de données
String sqlUpdate = "UPDATE PIZZA SET PIZZA_NAME = ? WHERE ID = ? ";
jdbcTemplate.update(sqlUpdate,p.getPizzaName(), p.getId());

// insertion
String sql = "INSERT INTO PIZZA (ID,PIZZA_NAME) VALUES(?,?)";
jdbcTemplate.update(sql, p.getId(), p.getPizzaName());
```
