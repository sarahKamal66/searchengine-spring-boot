Search Engine Backend
A backend search engine built with Spring Boot that uses an inverted index to rank and retrieve web pages by keyword frequency.
Tech Stack

Java / Spring Boot
Spring Data JPA
H2 Database
REST API
Maven

Features

Indexes text files and builds an inverted index stored in H2 database
REST API endpoint that returns ranked URLs by word frequency
Clean layered architecture: Entity / Repository / Service / Controller

API Usage
GET /api/search?word=example
Returns a list of URLs ordered by how frequently the word appears.
How to Run

Clone the repository
Open with IntelliJ IDEA
Run the project
Use Postman or browser to call the API
