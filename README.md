# Stocking_Goods_Re
Stocking Goods Limited Automation Project - Backend
This repository contains the backend implementation of the Stocking Goods Limited Automation Project. The project aims to automate the stock purchasing and reporting side of the business for a wholesaler of general goods.

Features
The backend implementation includes the following features:

Authentication: User login, logout, change password, and reset password functionalities are implemented using Spring Security. JWT (JSON Web Token) is used for token-based authentication with a token timeout set to 10 minutes before renewal.

Data Upload and Visualization: The API server supports uploading the Manufacturer data file (JSON) and provides visualization of the data.

Invoice Processing: The API server includes logic for invoice processing to fulfill customer orders.

Data Storage: MySQL database server is used to store the data.

Report Generation: The API server generates reports based on the manufacturer data.

Technologies Used
Java
Spring Boot
Spring Security
JWT (JSON Web Token)
MySQL
Hibernate
