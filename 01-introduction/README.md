# Accès aux données

## Spring Framework & Données
Spring offre une intégration avec des outils de persistance "classique" de l'écosystème Java::
* JDBC
* Hibernate
* JPA
* ...

Spring s'adapte à chaque technologie et masque les détails technique au développeur.

Spring Framework encapsule les exceptions spécifiques des outils de persistance.

Les transactions sont configurables par XML ou annotations et déléguées automatiquement au gestionnaire de transactions.


## Hiérarchie des exceptions

L'objectif est de pouvoir s'abstraire des exceptions spécifiques aux implémentations (par exemple SQLException ou HibernateException).

Spring fournit la hiérarchie **DataAccessException** :
* Type `Runtime` unchecked.
* Encapsule les types d'exceptions quelque soit la technologie.

Quelques exceptions::
* CleanupFailureDataAccessException : Une exception a été levée pendant la libération de ressources (exemple méthode close() sur une connexion).
* DataRetrievalFailureException : Une erreur s'est produite lors d'une requête de sélection.
* DeadlockLoserDataAccessException : Problème d'accès concurrents, processus bloqué par un lock.

## Spring Data

Le projet Spring Data (non inclu dans Spring Framework) vise à simplifier encore plus l'accès aux données.


