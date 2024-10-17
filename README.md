Library Management Application for interview

Library management application that is built using Spring Boot technology for application development. PostgreSQL was used to connect to the database where all the data needed for the application was stored. I also used pgAdmin 4 to manage that database. Spring Security and JWT (JSON Web Token) were used for registration and authentication on the application. Also, for easier coding, I added Lombok as a dependency, but only later in the project, so in some parts they have Getters and Setters and in some parts they don't, so you don't get confused. The code is versioned on GitHub (obviously). I hope you will like the application, I know it needs a lot of work, but I think it represents my progress well since I first started working on projects with Spring Boot, which was about 8 days ago. Enjoy this application and if I can help you with additional explanations, feel free to contact me by email.

The starting procedure and various details follow

Starting procedure

To begin with. Download the project from GitHub. After that, extract the .zip file. After that, import the ApplicationLibrary folder into your IDE as a Maven project. After that you will be able to notice that under /src/main/resources/application.properties.yml the dev profile is active. I would like to ask you to go to /src/main/resources/application-dev.yml and replace my data with yours in the datasource to connect to the database. Also do the same in the application.yml file. In there please change username and password to your username and password of postgreSQL. Later I will explain what must be in the database. You must also have JDK 17 and PostgreSQL 17 installed.

When you go to pgAdmin (I will say for that because that is what i used.) go to PostgreSQL 17. Firstly Login in with your password. Then go to Login/Group Roles, Right click ->Create -> Login/Group Roles. Then type name, go to Definition, type password. Then go to priviliges and select Can login? and then Save. That username and password must match the username and password above.

After that right click on Databases and Create -> Database -> type Database name and select owner as the user you made in step before.

  datasource:
    url: jdbc:postgresql://localhost:5432/usertable
    username: user
    password: user

Part of your yml should lool like this. If you are wondering how i got this 5432 just go right click on PostgreSQL 17 in pgAdmin and properties and then Connection and you will see your port too but 5432 is default.


After that go to your IDE and go to src/main/java/com/example/ApplicationLibrary then right clock on ApplicationLibraryApplication and then run. Tables in your database should be created automatically. That is because of ddl-auto: update

I recommend using Postman for testing (that is what i used)

LINK : https://www.postman.com/downloads/

/////////   TESTING    ////////


HOME PAGE

GET http://localhost:8080/

//////////   ACCOUNT part   /////////

POST http://localhost:8080/account/register 

-> BODY -> raw -> JSON -> 
{
    "username":"user",
    "password":"useruser",
    "email":"user@gmail.com",
    "role":"USER"
}

TOKEN JWT example: eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJCb29rIFN0b3JlIEFwaSIsInN1YiI6InVzZXIiLCJleHAiOjE3MjkyNzcxODIsImlhdCI6MTcyOTE5MDc4MiwidXNlcklkIjoxLCJyb2xlcyI6WyJVU0VSIl19.6QJGroElp2SOQyBZCkP8nu0Gb-Uv9t0527YJcOhWnKk

{
    "username":"admin",
    "password":"adminadmin",
    "email":"admin@gmail.com",
    "role":"ADMIN"
}

TOKEN JWT example:
eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJCb29rIFN0b3JlIEFwaSIsInN1YiI6ImFkbWluIiwiZXhwIjoxNzI5Mjc3MjcyLCJpYXQiOjE3MjkxOTA4NzIsInVzZXJJZCI6Miwicm9sZXMiOlsiQURNSU4iXX0.Mp_7qUkDMryoMzgIwvbqeKAfowAN_TlBEGMtgghvHhs


POST http://localhost:8080/account/login

-> BODY -> raw -> JSON -> 
{
    "username":"admin",
    "password":"adminadmin"
}

OUTPUT here is

{
    "user": {
        "id": 2,
        "username": "admin",
        "password": "$2a$10$yTPP8rfUKAvY50mSlblR1us/Ren7ZY9.0eYHek0GavU2/mt.5Sa6S",
        "email": "admin@gmail.com",
        "role": "ADMIN"
    },
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJCb29rIFN0b3JlIEFwaSIsInN1YiI6ImFkbWluIiwiZXhwIjoxNzI5Mjc3MzA1LCJpYXQiOjE3MjkxOTA5MDUsInVzZXJJZCI6Miwicm9sZXMiOlsiQURNSU4iXX0.7H4rVSVq0ni1HxvRimFqoYShLZIGz-yH_4CkhrbrPk4"
}


YOU must copy that token and use it for Authentication as Bearer token. (IF you are using Postman)

You can access profile details by doing:

GET http://localhost:8080/account/profile


You can delete accounts if you are ADMIN with

DELETE http://localhost:8080/account/delete/3 for example but if you are not ADMIN you will get error 403.

So get your Token with ADMIN role, go to Auth and select Bearer Token ,paste your token and then go to that link and click SEND. Then you will get text : User with ID 3 has been deleted successfully.


/////////   ADMIN PART    ////////

So get your Token with ADMIN role, go to Auth and select Bearer Token ,paste your token and then you can access /admin

For displaying all users and their details. This path is created for future use if you want to add additional features for admin.

GET http://localhost:8080/admin/users


//////////  Books part    ///////////


To display every book in library, AVAILABLE and CHECKED_OUT (USER and ADMIN can do this) do this

GET http://localhost:8080/books

You must Authorize yourself because you won't be able to see books listed

To get certain book use ({id} is bookId

GET http://localhost:8080/books/{id}

This will be empty in the start to please proceed

CRUD operations for books are only available for users with ADMIN role

To add books to library which i must ask you to do before testing other things

POST http://localhost:8080/books

YOU must copy token and use it for Authentication as Bearer token. (IF you are using Postman)

-> BODY -> raw -> JSON -> example (I will put JSON for all books at the bottom) here is first 5
{
        "title": "1984",
        "author": "George Orwell",
        "publication_year": 1949,
        "isbn": "978-0451524935"
}
please go one by one
{
        "title": "To Kill a Mockingbird",
        "author": "Harper Lee",
        "publication_year": 1960,
        "isbn": "978-0060935467"
}

{
        "title": "Brave New World",
        "author": "Aldous Huxley",
        "publication_year": 1932,
        "isbn": "978-0060850524"
}

{
        "title": "The Catcher in the Rye",
        "author": "J.D. Salinger",
        "publication_year": 1951,
        "isbn": "978-0316769488"
}

{
        "title": "The Great Gatsby",
        "author": "F. Scott Fitzgerald",
        "publication_year": 1925,
        "isbn": "978-0743273565"
}

status for books is AVAILABLE at start.


For changing data for specific book

PUT http://localhost:8080/books/{id}

example of change

{
        "title": "The Great Gatsby",
        "author": "F. Scott Fitzgerald",
        "publication_year": 1926,
        "isbn": "978-0743273565"
}

FOR deleting certain book use ({id} is bookId

DELETE http://localhost:8080/books/{id}

/////////// Cattegory PART /////////// I will get back to book related stuff later but firstly we need to asign categories to books and also insert categories in database.


For this part you must use user with ADMIN role

To get list of available categories in db please use

GET http://localhost:8080/categories

To get certain category by its id use

GET http://localhost:8080/categories/{id}

On the start that will be empty so please proceed

CRUD operations on categories

POST http://localhost:8080/categories

YOU must copy token and use it for Authentication as Bearer token. (IF you are using Postman)

-> BODY -> raw -> JSON -> example here is first few for testing purposes

{
        "name":"Fiction"
}

please go one by one

{
        "name":"Dystopian"
}

{
        "name":"Classic"
}


{
        "name":"Science Fiction"
}


For changing data in certain category with id please use
PUT http://localhost:8080/categories/{id}

-> BODY -> raw -> JSON -> here write 
{
        "name":"something else"
}

For deleting certain category use

DELETE http://localhost:8080/categories/{id}


///////// BOOK PART for second time //////////


To add certain category to certain book process is little complicated because I want to do everything with ids so for adding category to book, you need to know bookId and categoryId and for that you can follow all steps above and just use my data to insert everything.

FOR this please use ADMIN authorization

POST http://localhost:8080/books/1/categories/1

Adding Fiction to "1984" movie


POST http://localhost:8080/books/1/categories/2

Adding Dystopian to "1984" movie


POST http://localhost:8080/books/2/categories/1

POST http://localhost:8080/books/2/categories/3

POST http://localhost:8080/books/3/categories/1

POST http://localhost:8080/books/3/categories/4

POST http://localhost:8080/books/4/categories/1

POST http://localhost:8080/books/4/categories/3

POST http://localhost:8080/books/5/categories/1

POST http://localhost:8080/books/5/categories/3


At next part i will use some examples

Users with role USER can access this

Filtering books by status

GET http://localhost:8080/books/status?status=AVAILABLE

Filtering books by category

GET http://localhost:8080/books/category?categoryName=Fiction

Filtering books by status and category

GET http://localhost:8080/books/filter?status=AVAILABLE&categoryName=Fiction

Searching through books by title, it is not case sensitive 

GET http://localhost:8080/books/search?title=the


Next part is for users with role USER

Borrowing and Returning books

For borrowing

PUT http://localhost:8080/books/{id}/borrow

For returning

PUT http://localhost:8080/books/{id}/return

You can see all transactions if you go to your database and select query tool and write simple SQL command

SELECT * from transactions;

You will see that every transaction is in database in table transactions.




