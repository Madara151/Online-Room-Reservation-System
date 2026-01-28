# Online Room Reservation System


Welcome to the **Online Room Reservation System** – a web-based solution designed to manage hotel room reservations, billing, and reporting efficiently. This system demonstrates modern full-stack development using Spring Boot, JWT authentication, front-end JavaScript, and a relational database.


---


## 🔗 Live Repository


**GitHub Repository:**
https://github.com/Madara151/Online-Room-Reservation-System


This repository is public and contains all source files, configuration, documentation, and test cases.


---


## 📋 Features


- **User Authentication** using JWT tokens
- **Create/View Reservations** with validation
- **Generate Billing** based on nights stayed
- **Value-added Reports** (monthly counts, totals)
- **Exception Handling** (global error format)
- **Automated Unit Tests** using JUnit & Mockito
- **Clean Frontend UI** (HTML, CSS, JavaScript)


---


## 🛠 Technology Stack


| Layer | Technologies |
|-------|--------------|
| Frontend | HTML, CSS, JavaScript (Fetch API) |
| Backend | Java, Spring Boot, Spring Security, JWT, JPA/Hibernate |
| Testing | JUnit, Mockito |
| Build | Maven |
| Database | MySQL / H2 |


---


## 🚀 How to Run Locally


### 1. Clone the repo
```bash
git clone https://github.com/Madara151/Online-Room-Reservation-System.git
cd Online-Room-Reservation-System

Backend Setup

Configure application.properties for your database (MySQL or H2)

Run with Maven:

mvn spring-boot:run

By default, the backend runs on:

http://localhost:8080
3. Frontend

Open the HTML files in a browser (e.g., index.html)

Ensure the API base is set to http://localhost:8080 in JavaScript
