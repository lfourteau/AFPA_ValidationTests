***MediaValidationTest***
===================
Projet de validation des modules Web au cours de la formation AFPA "dévellopeur Logiciel" à Morlaix

# Languages utilisés :
* HTML/CSS
* Javascript (AJAX, manipulation du DOM)
* PHP/Symfony 3 (Twig, Doctrine)
* Mysql

#Installation
## Pré-requis : 
* SGBD
* Serveur local (ex: apache 2)

## A faire
* Importer la base de données MediaValidationTest.sql
* Modifier les paramètres de connexion de connexion à la bdd dans le fichier app/config/parameters.yml
* Installer les dépendances en ligne de commande depuis le dossier du projet:
curl -s https://getcomposer.org/installer | php
php composer.phar install
* Lancer le serveur local avec la commande : php bin/console server:start


