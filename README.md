# LostAndFound

A simple Spring Boot web application for managing lost and found items. Users can register, log in, report lost or found items, and receive email notifications when potential matches are detected.

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- MySQL
- Gmail SMTP (for email notifications)

---

## ⚙️ Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/LostAndFound.git
   cd LostAndFound
   ```

2. **Create MySQL Database**
   - Database name: `lost_and_found_item_db`

3. **Configure `application.properties`**
   - File: `src/main/resources/application.properties`
   - Update DB and email credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/lost_and_found_item_db
     spring.datasource.username=root
     spring.datasource.password=your_password

     spring.mail.username=your_email@gmail.com
     spring.mail.password=your_app_password
     ```

4. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

---

## 🔐 Authentication

- Uses **Basic Authentication**
- Pass `Authorization: Basic <base64encoded(username:password)>` in headers

---

## 📌 API Endpoints

### 🔑 User APIs

| Method | Endpoint               | Description               |
|--------|------------------------|---------------------------|
| GET    | `/api/users`           | Get all users             |
| GET    | `/api/users/{id}`      | Get user by ID            |
| POST   | `/api/users/register`  | Register a new user       |
| GET    | `/api/users/me`        | Get current logged-in user|

---

### 📦 Lost Item APIs

| Method | Endpoint                           | Description                        |
|--------|------------------------------------|------------------------------------|
| POST   | `/api/lost-items`                  | Add a new lost item                |
| GET    | `/api/lost-items`                  | Get all lost items                 |
| GET    | `/api/lost-items/user/{userId}`    | Get lost items by user ID          |
| GET    | `/api/lost-items/{id}`             | Get lost item by ID                |
| DELETE | `/api/lost-items/{id}`             | Delete lost item by ID             |

---

### 📦 Found Item APIs

| Method | Endpoint                           | Description                        |
|--------|------------------------------------|------------------------------------|
| POST   | `/api/found-items`                 | Add a new found item               |
| GET    | `/api/found-items`                 | Get all found items                |
| GET    | `/api/found-items/user/{userId}`   | Get found items by user ID         |
| GET    | `/api/found-items/{id}`            | Get found item by ID               |
| DELETE | `/api/found-items/{id}`            | Delete found item by ID            |

---

### 📧 Email Notifications

- Automatically triggered when a **new item matches** an existing lost/found entry based on:
  - Name
  - Location
  - Date

---

## 📝 Notes

- `application.properties` is ignored via `.gitignore` for security.
- To send emails, use a Gmail **App Password** and enable **2FA** in your Google account.

---

## 📬 Contact

For any issues or contributions, feel free to reach out or open a pull request.
