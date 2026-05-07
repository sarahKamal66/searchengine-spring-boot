# 🔍 Search Engine Backend System

A backend-based search engine built using Java and Spring Boot that enables efficient text search using the inverted index technique.

---

## 🚀 Features
- Build and maintain an inverted index for fast text retrieval
- Search for words and retrieve relevant documents
- RESTful APIs for indexing and searching operations
- Integrated with H2 database for data storage
- Tested using Postman

---

## 🛠️ Tech Stack
- Java
- Spring Boot
- H2 Database
- REST APIs
- Maven
- Postman

---

## 📌 API Endpoints

### 1. Index Data
**POST** `/index`
- Adds documents to the search engine

### 2. Search
**GET** `/search?word=example`
- Returns documents containing the searched word

---

## 🧠 How It Works
- The system processes input text and builds an inverted index
- Each word maps to a list of documents where it appears
- Search queries are matched against the index for fast retrieval

---

## ▶️ How to Run
1. Clone the repository
2. Open in IntelliJ / Eclipse
3. Run the Spring Boot application
4. Use Postman to test APIs

---

## 📷 Example
- Request: `/search?word=java`
- Response: List of documents containing the word "java"

---

## 📈 Future Improvements
- Ranking results based on relevance
- Support for multiple keywords search
- Pagination for results
- Deploy the application

---

## 👩‍💻 Author
Sara Kamal
