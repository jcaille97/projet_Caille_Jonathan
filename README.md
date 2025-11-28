# 📘 SimpleCash – Backend Spring Boot

SimpleCash est une application backend permettant de gérer des **conseillers bancaires**, leurs **clients**, et deux types de comptes : **compte courant** (avec découvert autorisé) et **compte épargne** (sans découvert).

Le projet suit une **architecture en couches**, utilise des **DTO**, **MapStruct**, **Spring Data JPA**, et expose une **API REST** documentée via un fichier OpenAPI.

---

## 🚀 Fonctionnalités principales

### 👤 Conseillers

* Création d’un conseiller
* Suppression (si aucun client associé)
* Liste des clients d’un conseiller

### 🧍 Clients

* Création d’un client (avec création automatique de ses comptes)
* Modification partielle (PATCH)
* Consultation d’un client
* Suppression (possible uniquement si les deux comptes sont soldés)

### 💰 Comptes bancaires

* Consultation du solde
* Crédit & débit

  * **Compte courant** : découvert autorisé jusqu’à **–1000 €**
  * **Compte épargne** : solde jamais négatif
* Virement entre deux comptes courants

---

## 📄 Documentation API

Une documentation complète de l’API est disponible dans :

👉 **`simplecash-openapi.yaml`**

Vous pouvez l’ouvrir dans :

* Swagger Editor : [https://editor.swagger.io](https://editor.swagger.io)
* Postman (Import → OpenAPI)
* Insomnia (Import → From File)

---

## 🏗️ Architecture

```
src/main/java/org/formation/simplecash
├─ controller/        → REST Controllers
├─ service/           → Interfaces + impl. métier
├─ repository/        → Spring Data JPA
├─ entity/            → Modèle JPA
├─ dto/               → Records DTO
└─ mapper/            → MapStruct mappers
```

---

## 📚 Documents fournis

* **Document de conception (2).pdf**
  (Contexte, objectifs, UML, user stories)

* **Bilan.pdf**
  (Fonctionnalités implémentées + axes d’amélioration)

---

## ▶️ Lancer le projet

### 🛠️ Prérequis

* Java 17+
* Maven

### ▶️ Commandes

```bash
mvn spring-boot:run
```

L’API sera disponible sur :
**[http://localhost:8080](http://localhost:8080)**

---

## 🔧 Améliorations possibles (TODO)

* Ajouter les endpoints d’audit des comptes
* Unifier proprement les réponses 400/404
* Ajouter des tests unitaires & d’intégration
* Ajouter Spring Security
* Historique des opérations & logs métier
* Activer Swagger UI via `springdoc-openapi`

---

## 📝 Licence

Projet scolaire — libre d’utilisation pour démonstration et apprentissage.

