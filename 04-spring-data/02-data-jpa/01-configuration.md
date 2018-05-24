# Configuration

## 1. Bean EntityManagerFactory

```java
@Configuration
public class PizzaAppConfig {
    
    // Bean indispensable pour faire du JPA
    @Bean
    public EntityManagerFactory entityManagerFactory(DataSource ds) {
        
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        
        // (...) configuration de l'EMF
        
        factory.afterPropertiesSet();
        return factory.getObject();
    }
}
```

## 2. Bean PlatformTransactionManager

```java
@Configuration
public class PizzaAppConfig {
    @Bean public EntityManagerFactory entityManagerFactory(DataSource ds) {...}

    @Bean // gestionnaire de transaction
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emf);
        return txManager;
    }
}
```

## 3. Scan des repositories

```java
@Configuration
// Activer	Spring	Data	JPA	et	indiquer les packages où se	trouvent les interfaces
@EnableJpaRepositories("fr.pizzeria.repos")
public class PizzaAppConfig {
    
    @Bean public EntityManagerFactory entityManagerFactory(DataSource ds) {
        //...
    }

    @Bean public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        //...
    }
}
```