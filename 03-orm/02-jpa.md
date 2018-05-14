# Spring ORM & JPA

3 options de configuration JPA :

* `LocalEntityManagerFactoryBean` : utilisé pour une application stand-alone ou pour des tests d'intégration.

* Obtenir une instance d'`EntityManagerFactory` depuis JNDI (Java Naming and Directory Interface) : utilisé pour une application déployée dans un serveur Java EE Full Profile.

* `LocalContainerEntityManagerFactoryBean` : utilisé pour des conteneurs légers.


## LocalEntityManagerFactoryBean
   
Exemple de configuration (version XML)

```xml
<beans>
   <bean id="emf" class="org.springframework.orm.jpa.LocalEntityManagerFactoryBean">
       <property name="persistenceUnitName" value=“pizzeria-pu" />
   </bean>
</beans>
```
   
Ce bean permet d'obtenir une instance d'EntityManagerFactory :
* Utilise en interne l'autodétection JPA pour Java SE (`Persistence.createEntityManagerFactory`).
