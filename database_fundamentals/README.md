# Database Fundamentals Exercises (Spring 2024)

This directory contain SQL scripts, entity-relationship diagram and relational schema completed as part of the **Database Fundamentals** course of **Tampere University**. The exercises focus on designing, populating, and querying a relational database using SQL.

The database represents a **group exercise management system**, including information about exercise categories, group exercise classes, instructors, rooms, and timetables.

The SQL scripts are divided into three main parts:

---

## Database Creation

The creation scripts define the database structure and relationships between the entities.

The database contains the following main tables:

* **kategoria** – stores exercise categories.
* **rltunti** – stores group exercise classes, including their name, description, duration, difficulty level, and category.
* **ohjaaja** – stores instructor information.
* **voi_ohjata** – represents which instructors are allowed to teach which group exercise classes.
* **sali** – stores room information.
* **ljtunti** – stores scheduled timetable entries for group exercise classes, including time, instructor, and room.

The database uses primary keys and foreign keys to maintain relationships and ensure data consistency. Foreign key constraint checking is enabled in SQLite.

---

## Data Insertion

The insertion scripts populate the database with example data.

The inserted data includes:

* Exercise categories (such as muscle training, body maintenance, and spinning).
* Group exercise classes with different durations and difficulty levels.
* Instructors and their available classes.
* Rooms and their capacities.
* Timetable entries for scheduled classes.

These scripts provide the sample data needed to test the SQL queries.

---

## Queries

The query scripts retrieve information from the database using SQL operations such as:

* `JOIN` operations to combine information from multiple tables.
* Aggregate functions (`COUNT`, `MIN`, `MAX`) to calculate statistics.
* Filtering using conditions and subqueries.
* Set operations such as `INTERSECT` and `EXCEPT`.
* Grouping and ordering of results.

The queries include tasks such as:

* Finding classes belonging to a specific category.
* Retrieving classes based on duration or difficulty level.
* Finding instructors with specific teaching abilities.
* Calculating statistics for each exercise category.
* Identifying instructors who teach all classes they are qualified to teach.

---

## Running the Scripts

The scripts are written for **SQLite**.

To create and populate the database, execute the creation and insertion scripts first:

```bash
sqlite3 database_name.db < create.sql
sqlite3 database_name.db < insert.sql
```

After the database has been created and populated, the query scripts can be executed:

```bash
sqlite3 database_name.db < query.sql
```

---

## Technologies Used

* SQL
* SQLite
* Relational database design
* Database queries and constraints
