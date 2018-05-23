# Multiple MyBatis Sample

Just a sample project that uses Spring Boot + MyBatis3 with multiple data sources (MySQL + PostgreSQL).

## Usage

```bash
# Setup DBs
$ docker-compose up -d

# Run
$ ./gradlew bootRun

# Create table, Insert items, Select all by Cursor for DB1 (MySQL)
$ curl localhost:8080/db1

# Create table, Insert items, Select all by Cursor for DB2 (PostgreSQL)
$ curl localhost:8080/db2

# Truncate both tables
$ curl localhost:8080/clear
```