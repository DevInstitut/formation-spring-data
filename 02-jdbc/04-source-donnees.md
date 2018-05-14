# Source de données

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

> Elle utilisée surtout dans le cadre de tests car elle ne fournie pas un pool de connexion.

```java
@Configuration
public class Config {

    @Bean
    public DataSource datasource() {
       DriverManagerDataSource dataSource = new	DriverManagerDataSource();
       dataSource.setDriverClassName("com.mysql.jdbc.Driver");
       dataSource.setUrl("jdbc:mysql://localhost:3306/pizzeria?useSSL=false");
       dataSource.setUsername("root");
       dataSource.setPassword("");
       return dataSource;
    }
}
```
