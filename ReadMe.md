
# Auth Application

---

## Prerequisites
- Java 11+ installed
- Maven installed
- Docker and Docker Compose installed and running

---

## Build and Run Instructions

1. **Build the project:**

   ```bash
   mvn clean install
   ```

2. **Stop and remove existing Docker containers, volumes, and images (optional but recommended on first run):**

   ```bash
   docker-compose down --volumes --rmi all
   ```

3. **Start the application:**

   ```bash
   docker-compose up -d
   ```

   This will start all necessary services in detached mode.

---

## API Usage

### 4. User Registration

- **Endpoint:**

  ```
  POST http://localhost:8080/auth/register
  ```

- **Request Body (JSON):**

  ```json
  {
      "username": "new_user",
      "password": "password123",
      "email": "new@example.com",
      "firstName": "New",
      "lastName": "User",
      "role": "USER"
  }
  ```

---

### 5. Login

- **Endpoint:**

  ```
  POST http://localhost:8080/auth/login
  ```

- **Request Body (JSON):**

  ```json
  {
      "username": "new_user",
      "password": "password123"
  }
  ```

- **Response:**
  - On successful authentication, a JWT token is returned.
  - Use this token in the `Authorization` header for subsequent requests:

    ```
    Authorization: Bearer <your_token>
    ```

---

### 6. Get All Users

- **Endpoint:**

  ```
  GET http://localhost:8080/users
  ```

- **Headers:**

  ```
  Authorization: Bearer <your_token>
  ```

- **Access Control:**

  - Requires **ADMIN** role.

---

### 7. Create User

- **Endpoint:**

  ```
  POST http://localhost:8080/users
  ```

- **Request Body (JSON):**

  ```json
  {
      "username": "new_user",
      "password": "$2a$10$5zLAAnaiquOUNhiKOxIjjeq5d.PYNuBQzwQV1S8Lra00JIdFC4zUu",
      "email": "new@example.com",
      "firstName": "New",
      "lastName": "User",
      "role": "USER"
  }
  ```

- **Notes:**
  - The password must be **hashed** before sending. The example uses a BCrypt hash for `"password123"`.
  - You can generate the hash using tools like [bcrypt-generator.com](https://bcrypt-generator.com/) or programmatically.


- **Headers:**

  ```
  Authorization: Bearer <your_token>
  ```

- **Access Control:**

  - Requires **ADMIN** role.
  - The password here can be in plain text; the system will handle hashing.

---

### 8. Update User

- **Endpoint:**

  ```
  PUT http://localhost:8080/users/{id}
  ```

- **Headers:**

  ```
  Authorization: Bearer <your_token>
  ```

- **Access Control:**

  - **ADMIN** can update any user.
  - **USER** role can only update their own data.

- **Request Body:**  
  - Should include the fields to update (e.g., `email`, `firstName`, `lastName`, etc.).
  - Password updates should be handled carefully — either hash before sending or let the backend handle hashing if plain text is sent.

---

### 9. Delete User

- **Endpoint:**

  ```
  DELETE http://localhost:8080/users/{id}
  ```

- **Headers:**

  ```
  Authorization: Bearer <your_token>
  ```

- **Access Control:**

  - **ADMIN** can delete any user.
  - **USER** can only delete their own account.

---