Java Microservices Assignment
Overview

This project implements two Spring Boot microservices to simulate an e-commerce inventory and order processing system.

Inventory Service manages product inventory with multiple batches and expiry dates

Order Service places orders after checking and reserving inventory

Services communicate via REST APIs

Design follows clean architecture and uses the Factory Design Pattern for extensibility

Architecture
Client → Order Service → Inventory Service


REST-based inter-service communication

DTOs for API contracts

JPA entities for persistence

Liquibase for DB schema and data loading

Technology Stack

Java 17

Spring Boot 3.x

Spring Data JPA

H2 in-memory database

Liquibase

Maven



Microservices
Inventory Service

Responsibilities

Manage inventory with multiple batches per product

Sort batches by expiry date (FEFO)

APIs

GET /inventory/{productId} – Get batches sorted by expiry date

POST /inventory/update – Update inventory after order placement

Design

Factory Pattern for extensible inventory logic

Order Service

Responsibilities

Accept orders

Check inventory availability

Reserve stock and persist order

API

POST /order – Place an order

Database & Liquibase

H2 database for both services

Liquibase runs automatically at startup

Tables and sample data loaded via CSV using <loadData>


REST endpoints covered

How to Run
cd inventory-service
mvn spring-boot:run

cd order-service
mvn spring-boot:run


H2 Console

Inventory: http://localhost:8080/h2-console

Order: http://localhost:8081/h2-console



