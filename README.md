# ☕ MyCoffee

**MyCoffee** is a full-stack web application that simplifies the coffee ordering experience for users and manages coffee-related data on the backend. The application is built using **React (frontend)**, **Spring Boot (backend)**, and **MySQL (database)**, and is deployed using **AWS EC2** and **S3** services.

---

## 🌐 Live Demo

**Frontend (S3-hosted)**: [http://mycoffeems.s3-website-us-west-1.amazonaws.com/](http://mycoffeems.s3-website-us-west-1.amazonaws.com/)

---

## 🧩 Tech Stack

- **Frontend**: React.js, Axios, Tailwind CSS
- **Backend**: Spring Boot, Java, RESTful APIs
- **Database**: MySQL (AWS RDS)
- **Deployment**: AWS EC2 (backend), AWS S3 (frontend)
- **Build Tool**: Maven

---

## 🛠️ Features

- 🛍️ **Customer Portal**: Select drinks, view items, and place orders
- 📋 **Order Management**: Backend handles CRUD operations for items, customers, and orders
- 🔗 **REST API**: Well-structured endpoints for frontend-backend communication
- 🧾 **Customer of the Month**: Aggregated feature based on order history
- 📦 **Cloud-Ready Deployment**: Seamless deployment across AWS EC2 and S3
- 🔐 **CORS Configured**: Cross-origin communication handled between frontend and backend

---

## 📁 Directory Structure

```
MyCoffee/
├── customer-frontend/       # React frontend
│   ├── public/
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   └── App.js
│   └── package.json
├── src/                     # Spring Boot backend (Java)
│   └── main/
│       └── java/
│           └── com/mycoffee/
│               ├── controller/
│               ├── entity/
│               ├── repository/
│               └── service/
├── pom.xml                  # Maven config
├── mvnw / mvnw.cmd          # Maven wrapper
└── README.md                # Project documentation
```

---

## 🔧 Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/mehakvirdy/MyCoffee.git
cd MyCoffee
```

---

### 2. Setup the Backend

#### Prerequisites:
- Java 17+
- Maven
- MySQL database (e.g., AWS RDS or local)

#### Configuration:
Set your database credentials in `application.properties` (or via environment variables):

```
spring.datasource.url=jdbc:mysql://<YOUR_DB_HOST>:3306/mycoffee
spring.datasource.username=yourUsername
spring.datasource.password=yourPassword
spring.jpa.hibernate.ddl-auto=update
```

#### Build & Run:

```bash
cd MyCoffee
./mvnw spring-boot:run
```

---

### 3. Setup the Frontend

```bash
cd customer-frontend
npm install
npm start
```

If deploying:
```bash
npm run build
# Upload 'build/' to AWS S3 bucket configured for static hosting
```

---

## 📦 API Endpoints

Examples:

```
GET    /items                    # List all items
POST   /orders                   # Place a new order
GET    /customers                # List all customers
DELETE /customers/{id}          # Delete a customer
GET    /customer-of-month       # Get featured customer
```

---

## 🧪 Sample Environment Setup

Add a `.env` file in `customer-frontend/` with:

```
REACT_APP_BASE_URL=http://<YOUR_BACKEND_EC2_PUBLIC_IP>:8080
```

---

## 🧠 Lessons Learned

- Integrated full-stack deployment across EC2, RDS, and S3
- Handled CORS issues and environment variable management
- Designed a REST API from scratch with real-world entities

---

## 👤 Author

**Mehak Virdy**  
[GitHub Profile](https://github.com/mehakvirdy)

---

## 📄 License

This project is open-source and available under the MIT License.

---

## 💬 Feedback

Feel free to open an issue or reach out with suggestions for improvement!
