# Propagation des transactions


|Attribut|Si une transaction existe|Si pas de transaction
|:-:|:-:|:-:|
|REQUIRED|Utiliser la transaction existante|Démarrer une nouvelle transaction|
|REQUIRES_NEW|Suspendre la transaction en cours. Démarrer une nouvelle transaction.|Démarrer une nouvelle transaction.|
|MANDATORY|Utiliser la transaction existante|Lancer une exception|
|NEVER|Lancer une exception|Traiter sans transaction|
|NOT_SUPPORTED|Suspendre la transaction en cours.|Traiter sans transaction|
|SUPPORTS|Utiliser la transaction existante|Traiter sans transaction|
|NESTED|Crée une transaction imbriquée qui peut être indépendante|Démarrer une nouvelle transaction|
