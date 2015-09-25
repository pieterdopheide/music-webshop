# MusicWebshop Learning Project

This is a simple webshop selling music instruments made as a learning project for the Java Traineeship of YoungCapital.

#### Technologies/frameworks used
- MySQL
- Hibernate
- Spring
- AngularJS
- Twitter Bootstrap

#### Install/Running the project
- Change MySQL connection details in spring4.xml and application.properties in src/main/resources
- Run maven clean and maven build on the project
- Start the project
- In the MySQL database, drop the table products_orders
- Create the table products_orders using misc/products_orders.sql
- Load instruments into the db using misc/products_test_data.sql

Everything should be working.

#### Known issues/bugs
- After logging in, a refresh has to be done before products can be added to the shoppingcart
- You need to log out multiple times
