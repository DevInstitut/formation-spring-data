# Insérer, mettre à jour, supprimer

La méthode `update` permet de mettre à jour les données en base.

```java
// mise à jour de données
String sqlUpdate = "UPDATE PIZZA SET PIZZA_NAME = ? WHERE ID = ? ";
jdbcTemplate.update(sqlUpdate,p.getPizzaName(), p.getId());

// insertion
String sql = "INSERT INTO PIZZA (ID,PIZZA_NAME) VALUES(?,?)";
jdbcTemplate.update(sql, p.getId(), p.getPizzaName());
```