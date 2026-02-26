# Recipe Data Collection and REST API

This project reads a JSON file that contains recipe data, stores it in a PostgreSQL database, and provides REST APIs to create and retrieve recipes.

When the application starts, it automatically loads the JSON data into the database. You can also fetch the top-rated recipes or add new ones using the API.

---

## Submitted By

Name: HARIRAJAN S  
Register No: 111723203033  
Department: Information Technology  
College: RMK Engineering College

---

## Tech Stack

- Java 17
- Spring Boot
- PostgreSQL
- Maven

---

## How to Run

**Step 1 – Clone the repository**
```bash
git clone [https://github.com/your-username/your-repo-name.git](https://github.com/HARIRAJAN07/recipeApp.git)
cd your-repo-name
```

**Step 2 – Setup PostgreSQL**

Install PostgreSQL and open `src/main/resources/application.properties`, then update your database username and password.

**Step 3 – Create the database**
```sql
CREATE DATABASE recipe;
```

**Step 4 – Start the application**
```bash
mvnw.cmd spring-boot:run
```

The app runs at `http://localhost:8080`. The JSON data loads into the database automatically on startup.

---

## Database Schema
```sql
CREATE TABLE recipe (
    id          BIGSERIAL PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    cuisine     VARCHAR(255) NOT NULL,
    rating      REAL,
    prep_time   INTEGER NOT NULL,
    cook_time   INTEGER NOT NULL,
    total_time  INTEGER,
    description TEXT,
    nutrients   JSONB,
    serves      VARCHAR(255)
);
```

Note: `total_time` is automatically set as `prep_time + cook_time`.

---

## API Endpoints

Use [Postman](https://www.postman.com/) to test the endpoints.

---

### GET /recipes/top

Fetches top-rated recipes sorted by rating. Default limit is 5.
```
GET http://localhost:8080/recipes/top?limit=5
```

Sample response:
```json
[
  {
    "id": 1,
    "title": "Butter Chicken",
    "cuisine": "Indian",
    "rating": 4.9,
    "prep_time": 30,
    "cook_time": 40,
    "total_time": 70,
    "description": "A rich and creamy tomato-based curry.",
    "serves": "4"
  }
]
```

---

### POST /recipes

Creates a new recipe. Send the data as a JSON body.
```
POST http://localhost:8080/recipes
```

Request body:
```json
{
  "title": "Dosa",
  "cuisine": "South Indian",
  "prep_time": 100,
  "cook_time": 20,
  "rating": 4.0,
  "description": "Crispy rice crepe served with chutney"
}
```

On success:
```json
{
  "id": 42,
  "title": "Dosa",
  "cuisine": "South Indian",
  "rating": 4.0,
  "prep_time": 100,
  "cook_time": 20,
  "total_time": 120,
  "description": "Crispy rice crepe served with chutney",
  "serves": null
}
```

If `title`, `cuisine`, `prep_time`, or `cook_time` is missing, you will get a 400 error:
```json
{
  "status": 400,
  "error": "Bad Request",
  "message": "Required fields must not be null or empty"
}
```

---

## What's Included

- Source code for Controller, Service, Repository and Model
- JSON parsing and data loading on startup
- PostgreSQL setup with schema
- REST API with basic validation
- Steps to run and test locally
