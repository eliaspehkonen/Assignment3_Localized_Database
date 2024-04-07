# Employee Management System with Localization

This project is a simple Employee Management System built using JavaFX for the user interface and MySQL for data storage. It allows users to add employee information in multiple languages (English, Japanese, and Persian) and saves the data to a localized database. When user chooses for example English language, the labels and buttons will be displayed in English. The same goes for Japanese and Persian languages. And when user saves the employee information, the data will be saved to the corresponding table in the database based on the selected language. For example, if the user selects Japanese language, the employee data will be saved to the employee_ja table.

## Features

- Add employee information such as first name, last name, and email.
- Select language from English, Japanese, and Persian.
- Localize labels and buttons according to the selected language.
- Save employee data to a MySQL database with localization support.

## Prerequisites

Before running the application, make sure you have the following installed:

- Java Development Kit (JDK) version 11 or higher
- MySQL Server
- MySQL Connector/J library (included in Maven dependencies)

## Getting Started

1. Clone the repository to your local machine:

   ```bash
   git clone https://github.com/eliaspehkonen/Assignment3_Localized_Database.git


2. Import the project into your preferred IDE (e.g., IntelliJ IDEA, Eclipse).

    Set up the MySQL database:

- Create a new database named Assignment_3_Localized_database.
- Create tables for each supported language (e.g., employee_en, employee_ja, employee_fa). You can use the provided SQL script (database_setup.sql) to create the necessary tables.
- Update the MySQL connection details in the HelloController.java file if necessary (DB_URL, DB_USER, DB_PASSWORD).

4. Build the project using Maven:

   ```bash
   mvn clean install
   
5. Run the HelloApplication.java file to start the application.

## Usage

- Launch the application.
- Select a language from the dropdown menu.
- Enter the employee's first name, last name, and email.
- Click the "Save" button to save the employee information to the database.
- You will receive a confirmation message indicating whether the employee was saved successfully or not.

## Contributing

Contributions are welcome! Please feel free to submit a pull request if you have any improvements or suggestions.