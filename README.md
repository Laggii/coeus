# Coeus - educational social network for students and teachers

Why "Coeus" ?

> Coeus /ˈsiːəs/ was a Titan god of intelligence and farsight, meaning that, due to his inquisitive mind and desire to learn, he was with gained knowledge and understanding able to see beyond the obvious.

> He was also identified as a god of wisdom and heavenly oracles.

Since my project is related to study, students and teachers, I use this naming.


## Technologies:
- JSP, Servlets, JDBC
- Bootstrap for UI and quick start(1) (and a few plugins: DataTables(2), BootstrapFormHelper)
- jBCrypt for password hashing(3) (BCrypt implementation on Java)
- Tomcat as web server and servlet container
- JUnit for testing
- Log4j for logging
- Gradle

## Patterns:
- MVC
- DAO and Connection Pool
- Builder ("must have" in model)
- FrontController (only for /main servlet)
- Strategy and FactoryMethod (4)

## Features
- Homegrown Login and Registration with password encryption
- Homegrown Roles system (Student/Teacher/Admin)
- User profiles and Friends
- Courses information (5)
- Localization (ru/eng)
- Full User input validation
- Error Handler (any application exceptions or http errors)
- CSRF protection (token)

## Cons
- Messages are not implemented yet
- getAll() methods return full collections from database (no pagination, see notes) (6)
- I query the database the whole time and the best choice is to use caching

## Notes
- (1) Well, it wasn't really a quick start, I spent hours to get elements working and looking good.
- (2) DataTables is an amazing, really amazing jQuery plugin with Bootstrap support. Provides new functionality for tables
- (3) All I know it's not md5 (which is old and bad) and passwords are encrypted with salt.
- (4) See Commands and CommandFactory. Yes, its named Command but it's still a Strategy pattern (should be renamed to Action mb)
- (5) Courses commands will be finished soon
- (6) Well, yeah, I have search, sorting and pagination provided by DataTables but I get the whole table from db. This is not really good in production but fine for a small project.