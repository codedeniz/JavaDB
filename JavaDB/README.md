# JavaDB
 
Currency Exchange Application - Database Setup
This guide provides instructions to set up the MySQL database required for the Currency Exchange Application. The database stores currency exchange rates and the application uses JavaFX to display this data in a bar chart.

Prerequisites
Before setting up the database, ensure that you have the following installed:

MySQL Server
MySQL Connector/J (for Java database connectivity)
Java Development Kit (JDK), version 22 or higher

Step 1: Create the Database

1.1 Connect to MySQL

Open a terminal or command prompt and log into your MySQL server:

mysql -u your_username -p

After entering your password, you’ll be logged into the MySQL shell.

1.2 Create a New Database

Create a new database for the application:


CREATE DATABASE currency_exchange;

Then switch to the currency_exchange database:

USE currency_exchange;

1.3 Create the exchange_rates Table

Now, create the table that will store the currency exchange data:

CREATE TABLE exchange_rates (
    id INT PRIMARY KEY AUTO_INCREMENT,
    currency_code VARCHAR(10) NOT NULL,
    exchange_rate DECIMAL(10, 2) NOT NULL
);

1.4 Insert Sample Data

Insert some initial exchange rates into the exchange_rates table:

INSERT INTO exchange_rates (currency_code, exchange_rate) VALUES
('USD', 1.00),
('EUR', 0.85),
('JPY', 110.50),
('GBP', 0.75),
('CAD', 1.20);
This will provide sample data that the application can retrieve and display.

Step 2: Configure Database Connection in the Application

The application uses a config.properties file to store database connection information. Ensure you update this file with your MySQL credentials:

2.1 Open config.properties

Locate the config.properties file in the project directory and open it. Update the following properties:


db.url=jdbc:mysql://localhost:3306/currency_exchange
db.username=your_username
db.password=your_password

Replace your_username and your_password with your MySQL credentials.

Step 3: Running the Application

Once the database is set up and populated with data:

Ensure MySQL is running.
Open your JavaFX project in your preferred IDE (such as IntelliJ or Eclipse).
Run the application. The bar chart will display the currency exchange rates fetched from the database.

Troubleshooting

If the application fails to connect to the database, check the following:
The MySQL server is running.
The database credentials in config.properties are correct.
The currency_exchange database and exchange_rates table exist.
Ensure that MySQL Connector/J is properly configured in your project dependencies.

Additional Notes

Security: Avoid committing sensitive information such as database credentials (username/password) to GitHub. Add the config.properties file to .gitignore.
SQL Script: Optionally, you can provide an SQL script (setup_database.sql) in the project to automate the database and table creation.
