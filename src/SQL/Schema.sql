CREATE DATABASE project_db;

USE project_db;


CREATE TABLE Users (
  id INT PRIMARY KEY AUTO_INCREMENT NOT NULL UNIQUE,
  username VARCHAR(255),
  hashed_password VARCHAR(255),
  class VARCHAR(255),
  actions JSON DEFAULT NULL CHECK (JSON_VALID(actions)) 

);

CREATE TABLE Inventory_Products (
  id INT PRIMARY KEY,
  name VARCHAR(255),
  description TEXT, -- haya mesh description text el mafrood malhash lazma sah wala 2a  
  quantity INT,
  price DECIMAL(10, 2),
  expiry_date DATE ,-- make it String and I will handle it in the code ashan teb2a ashal
  Damaged INT
);


CREATE TABLE Marketing_Reports (
  id INT PRIMARY KEY,
  employee_id INT,
  product_id INT,
  report_text TEXT,
  FOREIGN KEY (employee_id) REFERENCES Users(id),
  FOREIGN KEY (product_id) REFERENCES Inventory_Products(id)
);

CREATE TABLE Marketing_Offers (
  id INT PRIMARY KEY,
  employee_id INT,
  product_id INT,
  offer_text TEXT,
  FOREIGN KEY (employee_id) REFERENCES Users(id),
  FOREIGN KEY (product_id) REFERENCES Inventory_Products(id)
);

CREATE TABLE Sales_Orders (
  id INT PRIMARY KEY,
  user_id INT,   --user_id --> customer_id
  product_id INT,
  quantity INT,  --> of each product
  price DECIMAL(10, 2), -- add price --> each order have total price of all products which is price
  order_date DATETIME, -- maloosh lazma 
  status VARCHAR(255), -- status --> live, cancelled
  FOREIGN KEY (user_id) REFERENCES Users(id),
  FOREIGN KEY (product_id) REFERENCES Inventory_Products(id)
);

