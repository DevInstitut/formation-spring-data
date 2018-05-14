# Gestion des transactions

Spring n’implémente pas de transaction manager mais permet de se connecter et de déléguer des appels aux gestionnaire transactionnels.

Plusieurs implémentations sont proposées :
* Transaction local
 * HibernateTransactionManager
 * JpaTransactionManager
 * DataSourceTransactionManager
* Transaction managée
 * JtaTransactionManager
 * WebLogicJtaTransactionManager
 * WebSphereUowTransactionManager

## Gestion des transactions

Déclaration pour JDBC::
```xml
<bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource" />
</bean>
```
Déclaration pour Hibernate::
```xml
<bean id="txManager" class="org.springframework.orm.hibernate3.HibernateTransactionManager">
    <property name="sessionFactory" ref="sessionFactory" />
</bean>
```

Déclaration pour JTA::
```xml
<tx:jta-transaction-manager/>
```
* Spring détecte le serveur d'application et applique le manager adéquat (JtaTransactionManager, WebLogicJtaTransactionManager, WebSphereUowTransactionManager, ...)

## Gestion des transactions via @

```java
// activation du support de l'annotation @Transactional
@EnableTransactionManagement
public class PizzaAppConfig {
    @Bean
    public PlatformTransactionManager txManager(DataSource datasource) {
        return new DataSourceTransactionManager(datasource);
    }
}
```

## @Transactional

Positionner sur une classe, toutes les méthodes sont impactées.

```java
@Transactional
public class PizzaService {
}
```

Positionner sur une méthode, seule la méthode est impactée::

```java
public class PizzaService {

    @Transactional
    public void create(Pizza pizza) {
    }
}
```