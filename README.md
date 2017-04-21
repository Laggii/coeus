# Coeus - educational social network for students and teachers

Why "Coeus" ?

> Coeus /ˈsiːəs/ was a Titan god of intelligence and farsight, meaning that, due to his inquisitive mind and desire to learn, he was with gained knowledge and understanding able to see beyond the obvious.

> He was also identified as a god of wisdom and heavenly oracles.

Since my project is related to study, students and teachers, I use this naming.


## Technologies:
- JSP, Servlets, JDBC
- Custom JSTL tags
- Bootstrap for UI and quick start(1) (and a few plugins: DataTables(2), BootstrapFormHelper)
- jBCrypt for password hashing(3) (BCrypt implementation on Java)
- Tomcat as web server and servlet container
- MariaDB as Database
- JUnit for testing
- Log4j for logging
- Gradle

## Patterns:
- MVC
- DAO and Connection Pool
- Builder ("must have" in model)
- FrontController (only for /main servlet)
- Strategy and FactoryMethod (Action package)

## Features
- Homegrown Login and Registration with password encryption
- Homegrown Roles (Student/Teacher/Admin)
- User Profiles and Friends (add friend/remove friend)
- Courses information (join/leave/create/edit)
- Localization (ru/eng)
- Full user input validation
- Error Handler (any application exceptions or http errors)
- CSRF protection (token)

## TODO
- Messages
- Avatars
- Translation of all JSP's
- Pagination (4)
- Caching
- Deploy demo with Docker and AWS

## Screenshots
<details>
  <summary> Welcome page </summary>
![Alt text](/src/main/resources/screenshots/index.jpg?raw=true "Welcome page")
</details>
<details>
  <summary> Login/Registration page </summary>
![Alt text](/src/main/resources/screenshots/login.jpg?raw=true "Login/Registration page")
</details>
<details>
  <summary> Profiles </summary>
![Alt text](/src/main/resources/screenshots/profile.jpg?raw=true "Profiles")
</details>
<details>
  <summary> Courses </summary>
![Alt text](/src/main/resources/screenshots/courses.jpg?raw=true "Courses")
</details>
<details>
  <summary> Course information </summary>
![Alt text](/src/main/resources/screenshots/course.jpg?raw=true "Course information")
</details>
<details>
  <summary> Friends </summary>
![Alt text](/src/main/resources/screenshots/friends.jpg?raw=true "Friends")
</details>
<details>
  <summary> Users </summary>
![Alt text](/src/main/resources/screenshots/users.jpg?raw=true "Users")
</details>
<details>
  <summary> Settings </summary>
![Alt text](/src/main/resources/screenshots/settings.jpg?raw=true "Settings")
</details>
<details>
  <summary> More features </summary>
[Alt text](/src/main/resources/screenshots/features.jpg?raw=true "Features")
</details>

## Notes
- (1) Well, it wasn't really a quick start, I spent hours to get elements working and looking good.
- (2) DataTables is an amazing, really amazing jQuery plugin with Bootstrap support. Provides new functionality for tables
- (3) All I know it's not md5 (which is old and bad) and passwords are encrypted with salt.
- (4) Well, yeah, I have search, sorting and pagination provided by DataTables but I get the whole table from db. This is not really good in production but fine for a small project.
