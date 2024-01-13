# How to setup local
This Project is to save Customer Details with few validations using Java Spring boot boot and MySQL for Database.
To do Basic CRUD operation we need to include Spring Data JpaRepository Dependencies.
Add the necessary dependency for Database setup In this project I used MySql Dependencies to connect Database.

After adding dependency try to reload maven once to include all added dependencies.
Once Added Start implementing code including controller and basic implementation and Try to start the server default port will be 8080.
Once Server Started then implement the business logic.
Created Database joinapp and in DataBase Add customer_details table which we using and all constraints we required. 
In this Project I included lombok to make use of few required Annotations like setter and getter and others.

Create a payload which include necessary variable we required to process this implementation.
Test the coding with all different payload to verify logic.
Once every logic works try to cover it ith Junit test cases.
In this project I used CustomRunTimeException for handle different error and sending in response with Internal Server error code.
