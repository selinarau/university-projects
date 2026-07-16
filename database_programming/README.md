# Database Programming Project (Spring 2025)

This project was done with two other students. My main responsibilities were the SQL database modeling and creation and order-connected code.

---

## Project Overview
This web application simulates a **Centralized Second-Hand Bookstore System (Keskusdivari)**. The system enables multiple individual second-hand bookstores (*divarit*) to manage their collections, handle inventory, and process remote online sales through a shared central system. 

The application architecture supports both unified centralized hosting and distinct independent bookstore databases communicating with the central hub.

---

## Technical Stack
* **Database Management System:** PostgreSQL
* **Programming Backend Options:** PHP or Node.js
* **Database Platform:** `tie-tkannat.it.tuni.fi` server
* **Modeling Tools Used:** Draw.io

---

## System Architecture & Database Schema
To accurately reflect the assignment requirements, the system models independent operating environments via isolated ER diagrams and distinct database schemas that do not directly intersect:
1. **Central Database (Keskusdivari):** Manages the core customer registry, shared functionalities, and hosts a full mirrored copy of Bookstore D1's data. It also directly hosts all data for Bookstore D2.
2. **Bookstore D1 (Lassen lehti):** Operates on an independent local schema, which synchronizes with the central system.
3. **Bookstore D2 (Galleinn Galle):** Uses the central database schema directly for all operations.
4. **Bookstore D3:** A standalone schema mirroring D1's architecture, specifically implemented to test automated trigger behaviors.

> **Design Note:** The schema strictly differentiates between a **Work/Title (Teos)** (e.g., general attributes like ISBN, Title, Author, Type, Class) and a specific **Book Copy (Nide/Kappale)** available for sale (storing specific purchase prices, status, weight, etc.).

---

## Implemented Core Functionalities

### 1. User Transactions & Simulation
* **T1: Registration & Authentication:** User sign-up and login workflows.
* **T2 & T3: Inventory Management:** Functionality for adding completely new work entries, or new physical copies of existing works, distributed across D1 (local + central mirroring) and D2 (central only).
* **T4 & T5: Order Workflow & Shipping Calculations:** Simulates order reservation transitions ("Free" $\rightarrow$ "Reserved" $\rightarrow$ "Sold"). It calculates cumulative shipping costs based on weight thresholds:
  * $\le$ 50 g : €2.50
  * $\le$ 250 g : €5.00
  * $\le$ 1000 g : €10.00
  * $\le$ 2000 g : €15.00
  * $> 2000 \text{ g}$ : Automated split-consignment handling logic.

### 2. Advanced Synchronization & Data Ingestion
* **T6: Automated Mirroring Trigger:** A PostgreSQL trigger that instantly updates the central database whenever a new copy is added to a local store schema (simulated via D3).
* **T7: Batch Synchronization:** A routine that identifies unsynced records in local bookstore environments and migrates updates cleanly to the central repository.
* **T8: XML Data Ingestion (Bookstore D4 Integration):** An integration tool that reads external bookstore data formatted via standard XML (validated against the specified DTD file) and imports works and copies automatically into the central system.

---

## Reports & Queries

* **R1: Broad Search Functionality:** Supports partial/substring matching against fields like title, author, book type (e.g., novel, comic), and genre class.
* **R2: Category Analytics:** Formats items by genre classes, computing the total listed retail value and average price per category.
* **R3: Annual Customer Export:** Generates an Excel-compatible, semicolon-delimited CSV containing a comprehensive customer index alongside dynamic counts of books purchased during the previous calendar year.
* **R4: Relevancy-Ranked Search:** Evaluates text relevance algorithms by prioritizing full search phrase matches across titles before falling back to partial-word matching metrics.

---

## Repository Structure & Project Milestones

The project deliverables were completed across two core development phases:

### Phase 1: Database Design & Structure
* Comprehensive entity-relationship mapping (PDF format).
* Detailed logical relational schemas.
* Documented relational transitions, attribute domain boundaries, NULL value rules, and raw SQL creation scripts (`.sql`).

### Phase 2: Application Implementation & Review
* Web interface integration and concurrent multi-user stability tests.
* Core data sanitization (mitigating SQL Injection risks) and role-based access configurations.
* Full integration data tests utilizing target testing profiles (including historical works by authors like Mika Waltari, Madeleine Brent, etc.).
* Documented administrative guidelines, project reflection reports, and environment configuration scripts.