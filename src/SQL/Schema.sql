CREATE DATABASE project_db;

USE project_db;

CREATE TABLE Admins (
  id INT PRIMARY KEY,
  username VARCHAR(255),
  hashed_password VARCHAR(255)
);

CREATE TABLE Employees (
  id INT PRIMARY KEY,
  username VARCHAR(255),
  hashed_password VARCHAR(255),
  type VARCHAR(255)
);

CREATE TABLE Marketing_Reports (
  id INT PRIMARY KEY,
  employee_id INT,
  product_id INT,
  report_text TEXT,
  FOREIGN KEY (employee_id) REFERENCES Employees(id),
  FOREIGN KEY (product_id) REFERENCES Inventory_Products(id)
);

CREATE TABLE Marketing_Offers (
  id INT PRIMARY KEY,
  employee_id INT,
  product_id INT,
  offer_text TEXT,
  FOREIGN KEY (employee_id) REFERENCES Employees(id),
  FOREIGN KEY (product_id) REFERENCES Inventory_Products(id)
);

CREATE TABLE Inventory_Products (
  id INT PRIMARY KEY,
  name VARCHAR(255),
  description TEXT,
  quantity INT,
  price DECIMAL(10, 2),
  expiry_date DATE
);

CREATE TABLE Sales_Orders (
  id INT PRIMARY KEY,
  user_id INT,
  product_id INT,
  quantity INT,
  order_date DATETIME,
  status VARCHAR(255),
  FOREIGN KEY (user_id) REFERENCES Users(id),
  FOREIGN KEY (product_id) REFERENCES Inventory_Products(id)
);

CREATE TABLE Users (
  id INT PRIMARY KEY,
  username VARCHAR(255),
  hashed_password VARCHAR(255),
  email VARCHAR(255),
  actions TEXT

);