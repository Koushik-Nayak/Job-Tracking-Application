# Job Application Tracking System

Stack:
- Java 17
- Spring MVC 6
- Hibernate ORM 6
- JPA / JPQL
- JSP without JSTL
- Lombok
- PostgreSQL
- HttpSession
- Tomcat 10.1+

## Important
This project intentionally uses JPQL through JPA EntityManager.
There are no Hibernate HQL queries.

## Database
Create:

CREATE DATABASE job_tracker;

Then update username/password in:
src/main/resources/META-INF/persistence.xml

## Roles
- JOB_SEEKER: register through the registration page
- ADMIN: create an admin row manually in PostgreSQL

Example admin SQL:

INSERT INTO users(name, email, password, role)
VALUES ('Admin', 'admin@gmail.com', 'admin123', 'ADMIN');

## Main workflow
1. Admin logs in.
2. Admin adds companies.
3. Admin adds jobs.
4. Job seeker registers and logs in.
5. Job seeker searches jobs.
6. Job seeker views job details and applies.
7. Application initially becomes APPLIED.
8. Admin views applications for a job.
9. Admin changes status to INTERVIEW, SELECTED, or REJECTED.
10. Job seeker sees the updated status and interview information in My Applications.

## JSP
No JSTL tags are used.
Dynamic lists are rendered using JSP scriptlets and expressions.

## JPQL examples
SELECT j FROM Job j ...
SELECT a FROM JobApplication a ...
SELECT COUNT(a) FROM JobApplication a ...

These queries use entity names and Java field names, not table/column names.


## Transaction approach
This version does NOT use `@Transactional`, `@EnableTransactionManagement`, `JpaTransactionManager`, or interceptor logic.
The `EntityManager` is configured as a Spring bean in `AppConfig`. Every DAO method explicitly calls `getTransaction().begin()`, performs its database work, calls `commit()`, and rolls back when an exception occurs.

The EntityManager bean is request-scoped so a single EntityManager is not shared between different web requests.
