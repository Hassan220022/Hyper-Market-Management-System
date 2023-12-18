# Hyper Market Management System

![Hyper Market Management System](shopping-cart.ico)

**Revolutionize the way you manage your market with our Hyper Market Management System!**

Our system can be installed in large markets to manage sales and purchases with ease. With different user types and roles, you can ensure that your employees have the tools they need to succeed.

## Modules
Our system contains the following modules:

### Administrative Module
- Admin has a username and password and can alter them.
- Admin manages employees (Add - set unique id, password, and type to - new employee, Delete, update, List all Employees, search).

### Marketing Module
- Marketing employees can make reports about products (make queries).
- Marketing employees make special offers and send them to inventory management.

### Inventory Management Module
- Inventory employees (Add, Delete, Update, List, Search) Products.
- The system must send a notification when the amount of product reduces at a special range -set by inventory employees- OR when the expiry date of the product gets close.
- Manage the Damages items and sales Return.

### Sales Module
- Seller can (search for product, list all products, make and cancel order).

### User Module
- All Users can login and logout.
- Users can update their information except ID.
- Users can see all previous actions -optional-.

## Implementation Details
Our system is built with Java for an OOP university course at AAST. The GUI is built with the NetBeans editor. The code was written using dependencies like jBCrypt for password management and FlatLaf for design. The database is on MySQL.

## Installation
An executable installer is available in the [releases](https://github.com/OmarAhmed-A/Hyper-Market-Manegment-Semester6/releases) section of this repository.

Packaged using jpackage like so:
```bash
jpackage --name HyperMarket --input lib --main-jar Hyper-Market-Manegment-Semester6.jar --main-class Main.Main --icon ./shopping-cart.ico --type exe --win-menu --win-shortcut
```

Join the revolution and try our Hyper Market Management System today!