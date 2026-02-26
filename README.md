# Recipe Data Collection and REST API

This project reads a JSON file that contains recipe data, stores it in a PostgreSQL database, and provides REST APIs to create and retrieve recipes.

When the application starts, it automatically loads the JSON data into the database. You can also fetch the top-rated recipes or add new ones using the API.

---

# Submitted By

- HARIRAJAN S
- Register No: 111723203033
- Department: Information Technology
- RMK Engineering College

## Tech Stack

- Java 17
- Spring Boot
- PostgreSQL
- Maven

---

## Steps to Run the Application

**1. Clone the repository:**
```bash
git clone https://github.com/your-username/your-repo-name.git
cd your-repo-name
```

**2. Install PostgreSQL** and open `src/main/resources/application.properties` to add your database username and password.

**3. Create a database** named `recipe` in PostgreSQL:
```sql
CREATE DATABASE recipe;
```

**4. Run the application** from the project root:
```bash
mvnw.cmd spring-boot:run
```

**5.** The application will start at `http://localhost:8080`

> The JSON file is loaded into the database automatically when the app starts.

---

## Database Schema

The following table is used to store recipe data:
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

> `total_time` is calculated automatically as `prep_time + cook_time`.

---

## API Testing

You can test the endpoints using [Postman](https://www.postman.com/). There are 2 endpoints.

---

### 1. GET – Top Rated Recipes
```
GET http://localhost:8080/recipes/top?limit=5
```

Returns the top-rated recipes sorted by rating in descending order. The `limit` parameter controls how many results come back — default is 5.

**Example:**
```
GET http://localhost:8080/recipes/top?limit=3
```

**Success Response – 200 OK:**
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

### 2. POST – Create a New Recipe
```
POST http://localhost:8080/recipes
```

Send a JSON body in this format:
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

**Success Response – 200 OK:**
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

**Validation Error – 400 Bad Request:**

If `title`, `cuisine`, `prep_time`, or `cook_time` is missing or empty, the API returns:
```json
{
  "status": 400,
  "error": "Bad Request",
  "message": "Required fields must not be null or empty"
}
```

---

## What This Submission Includes

- Complete source code — Controller, Service, Repository, and Model
- JSON parsing and automatic data loading on startup
- PostgreSQL integration with the schema above
- REST API with validation
- Full instructions to run and test locally
